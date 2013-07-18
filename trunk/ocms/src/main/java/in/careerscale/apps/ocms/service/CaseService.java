package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.NotificationRepository;
import in.careerscale.apps.ocms.dao.model.Address;
import in.careerscale.apps.ocms.dao.model.CaseApprovalHistory;
import in.careerscale.apps.ocms.dao.model.CaseArtifact;
import in.careerscale.apps.ocms.dao.model.CaseDiscussion;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseStatus;
import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.CaseTypeApprover;
import in.careerscale.apps.ocms.dao.model.City;
import in.careerscale.apps.ocms.dao.model.DocumentType;
import in.careerscale.apps.ocms.dao.model.Fund;
import in.careerscale.apps.ocms.dao.model.FundStatus;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.Notification;
import in.careerscale.apps.ocms.dao.model.NotificationRecipient;
import in.careerscale.apps.ocms.dao.model.NotificationStatus;
import in.careerscale.apps.ocms.dao.model.NotificationTemplate;
import in.careerscale.apps.ocms.dao.model.Organization;
import in.careerscale.apps.ocms.pdf.PDFGenerator;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.cases.model.CaseArtifacts;
import in.careerscale.apps.ocms.web.cases.model.CaseDiscussionBO;
import in.careerscale.apps.ocms.web.cases.model.CaseHistory;
import in.careerscale.apps.ocms.web.cases.model.Document;
import in.careerscale.apps.ocms.web.cases.model.FundBO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("caseService")
public class CaseService extends AbstractService
{

	private static final String ONLINE_TRANSFER = "Online Transfer";

	Log log = LogFactory.getLog(CaseService.class);

	@Autowired
	private CaseRepository caseRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private PDFGenerator pdfGenerator;

	public void setCaseRepository(CaseRepository caseRepository)
	{
		this.caseRepository = caseRepository;
	}

	public void setMasterDataRepository(MasterDataRepository masterDataRepository)
	{
		this.masterDataRepository = masterDataRepository;
	}

	public void setNotificationRepository(NotificationRepository notificationRepository)
	{
		this.notificationRepository = notificationRepository;
	}

	public CaseMaster registerCase(Case bean) throws ApplicationException
	{

		CaseMaster caseMaster;

		try
		{

			caseMaster = new CaseMaster(bean.getCreatedDate(), bean.getUpdatedDate(), bean.getPersonName(),
					bean.getEmailId(), bean.getDateOfBirth(), bean.getCaseDescription(), bean.getContact1(),
					bean.getContact2(), bean.getCaseSource());
			// TODO set DB flag as well.
			caseMaster.setCaseStatusMaster((CaseStatusMaster) masterDataRepository.getById(CaseStatusMaster.class,
					new Integer(2)));
			// TODO replace with status enum or something like that. Here 2
			// means pending in db.
			if (bean.getCaseType() > 0)
				caseMaster.setCaseType((CaseType) masterDataRepository.getCaseType(bean.getCaseType()));
			if (bean.getHelpType() > 0)
				caseMaster.setHelpCategoryType((HelpCategoryType) masterDataRepository.getHelpCategoryType(bean
						.getHelpType()));
			caseMaster.setContactNumber1(bean.getContact1());
			caseMaster.setContactNumber2(bean.getContact2());
			caseMaster.setEmailId(bean.getEmailId());
			caseMaster.setCreatedOn(Calendar.getInstance().getTime());
			caseMaster.setUpdatedOn(Calendar.getInstance().getTime());

			LoginMaster loggedInUser = getLoggedInUser();

			caseMaster.setCreatedBy(loggedInUser);
			caseMaster.setUpdatedBy(loggedInUser);

			City city = (City) caseRepository.getById(City.class, bean.getCityId());

			Address address = new Address(city, bean.getAddressLine1(), bean.getAddressLine2(), bean.getZipcode());
			caseRepository.save(address);
			caseMaster.setAddress(address);
			// TODO get organization dynamically based on the context.
			caseMaster.setOrganization((Organization) caseRepository.getById(Organization.class, 1));
			caseRepository.registerCase(caseMaster);
			bean.setId(caseMaster.getId());

			NotificationRecipient recipient = null;
			NotificationStatus status = (NotificationStatus) notificationRepository
					.getById(NotificationStatus.class, 1);
			NotificationTemplate template = (NotificationTemplate) notificationRepository.getById(
					NotificationTemplate.class, 1);
			Notification notification = new Notification("this is recipient", Calendar.getInstance().getTime(),
					Calendar.getInstance().getTime(), loggedInUser, loggedInUser, caseMaster, recipient, status,
					template);
			notificationRepository.save(notification);

		}
		catch (PersistenceException pe)
		{
			throw new ApplicationException(pe.getMessage());
		}
		return caseMaster;

	}

	public List<in.careerscale.apps.ocms.web.cases.model.DocumentType> getDocumentTypes(Integer caseTypeId)
	{
		List<in.careerscale.apps.ocms.web.cases.model.DocumentType> docTypeList = new ArrayList<in.careerscale.apps.ocms.web.cases.model.DocumentType>();
		List<DocumentType> dbDocTypes = caseRepository.getDocumentTypes(caseTypeId);
		for (DocumentType documentType : dbDocTypes)
		{
			docTypeList.add(new in.careerscale.apps.ocms.web.cases.model.DocumentType(documentType.getId(),
					documentType.getName(), documentType.getSupportedFormats(), documentType.getIsMandatory(),
					documentType.getMaxSize()));
		}

		return docTypeList;

	}

	public void saveCaseAtrifacts(CaseArtifacts bean)
	{
		List<Document> documents = bean.getCaseDocuments();
		LoginMaster loginMaster = getLoggedInUser();
		for (Document document : documents)
		{
			try
			{
				CaseArtifact artifact = new CaseArtifact();
				// artifact.setArtifactType("test");
				artifact.setArtifact(document.getFile().getBytes());
				artifact.setCaseMaster((CaseMaster) caseRepository.getById(CaseMaster.class, bean.getCaseId()));
				artifact.setLoginMaster(loginMaster);
				String fileName = document.getFile().getOriginalFilename();
				artifact.setFileExtension(fileName.substring(fileName.lastIndexOf(".")));
				caseRepository.save(artifact);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * @return List of acted upon cases
	 */
	public List<in.careerscale.apps.ocms.web.cases.model.Case> getCasesToBeActedUpon()
	{
		Integer userId = getLoggedInUserId();
		List<in.careerscale.apps.ocms.web.cases.model.Case> myCasesList = new ArrayList<in.careerscale.apps.ocms.web.cases.model.Case>();
		List<CaseMaster> lstCases = caseRepository.getCasesToBeActedUpon(userId);
		for (CaseMaster mycase : lstCases)
		{
			myCasesList.add(new in.careerscale.apps.ocms.web.cases.model.Case(mycase.getId(), mycase.getPersonName(),
					mycase.getCaseDescription(), mycase.getSource(), mycase.getDateOfBirth(), mycase.getCreatedOn(),
					mycase.getUpdatedOn(), mycase.getContactNumber1(), mycase.getContactNumber2(), mycase
							.getCaseStatusMaster().getCaseStatusName(), mycase.getHelpCategoryType().getCategoryName(),
					mycase.getCaseType().getName(), mycase.getCreatedBy().getFirstName(), mycase.getUpdatedBy()
							.getFirstName()));
		}

		return myCasesList;

	}

	/**
	 * @return List of my cases
	 */
	public List<in.careerscale.apps.ocms.web.cases.model.Case> getMyCases()
	{
		Integer userId = getLoggedInUserId();
		List<in.careerscale.apps.ocms.web.cases.model.Case> myCasesList = new ArrayList<in.careerscale.apps.ocms.web.cases.model.Case>();
		List<CaseMaster> lstCases = caseRepository.getMyCases(userId);
		for (CaseMaster mycase : lstCases)
		{
			myCasesList.add(new in.careerscale.apps.ocms.web.cases.model.Case(mycase.getId(), mycase.getPersonName(),
					mycase.getCaseDescription(), mycase.getSource(), mycase.getDateOfBirth(), mycase.getCreatedOn(),
					mycase.getUpdatedOn(), mycase.getContactNumber1(), mycase.getContactNumber2(), mycase
							.getCaseStatusMaster().getCaseStatusName(), mycase.getHelpCategoryType().getCategoryName(),
					mycase.getCaseType().getName(), mycase.getCreatedBy().getFirstName(), mycase.getUpdatedBy()
							.getFirstName()));
		}

		return myCasesList;

	}

	/**
	 * @return List of my interested cases
	 */
	public List<in.careerscale.apps.ocms.web.cases.model.Case> getInterestedCases()
	{
		Integer userId = getLoggedInUserId();
		List<in.careerscale.apps.ocms.web.cases.model.Case> myCasesList = new ArrayList<in.careerscale.apps.ocms.web.cases.model.Case>();
		List<CaseMaster> lstCases = caseRepository.getInterestedCases(userId);
		for (CaseMaster mycase : lstCases)
		{
			myCasesList.add(new in.careerscale.apps.ocms.web.cases.model.Case(mycase.getId(), mycase.getPersonName(),
					mycase.getCaseDescription(), mycase.getSource(), mycase.getDateOfBirth(), mycase.getCreatedOn(),
					mycase.getUpdatedOn(), mycase.getContactNumber1(), mycase.getContactNumber2(), mycase
							.getCaseStatusMaster().getCaseStatusName(), mycase.getHelpCategoryType().getCategoryName(),
					mycase.getCaseType().getName(), mycase.getCreatedBy().getFirstName(), mycase.getUpdatedBy()
							.getFirstName()));
		}

		return myCasesList;

	}

	public void getCaseDetails(Integer id, Case bean)
	{

		CaseMaster caseMaster = caseRepository.getCase(id);

		if (bean == null)
		{
			bean = new Case();
		}
		bean.setId(caseMaster.getId());
		bean.setPersonName(caseMaster.getPersonName());
		bean.setEmailId(caseMaster.getEmailId());
		bean.setDateOfBirth(caseMaster.getDateOfBirth());
		bean.setproperties(caseMaster.getId(), caseMaster.getPersonName(), caseMaster.getEmailId(), caseMaster
				.getSource(), caseMaster.getDateOfBirth(), caseMaster.getCreatedOn(), caseMaster.getUpdatedOn(),
				caseMaster.getContactNumber1(), caseMaster.getContactNumber2(), caseMaster.getCaseType().getName(),
				caseMaster.getHelpCategoryType().getCategoryName(), caseMaster.getCaseStatusMaster()
						.getCaseStatusName(), caseMaster.getCreatedBy().getFirstName(), caseMaster.getUpdatedBy()
						.getFirstName());
		if (null != caseMaster.getAddress())
		{
			Address address = caseMaster.getAddress();
			bean.setAddressProperties(address.getAddressLine1(), address.getAddressLine2(), address.getZipCode(),
					address.getCity().getCityName(), address.getCity().getId());
		}

	}

	public CaseArtifacts getCaseArtifacts(Integer caseId)
	{
		List<CaseArtifact> caseArtifacts = caseRepository.getCaseArtifacts(caseId);

		CaseArtifacts caseArtifactBean = new CaseArtifacts();

		for (CaseArtifact caseArtifact : caseArtifacts)
		{

			caseArtifactBean.addCaseArtifact(caseArtifact.getDocumentType() == null ? "unknown" : caseArtifact
					.getDocumentType().getName(), caseArtifact.getId(), caseArtifact.getFileExtension(), caseArtifact
					.getDocumentType() == null ? "unknown" : caseArtifact.getDocumentType().getName());

		}

		return caseArtifactBean;
	}

	/**
	 * Let us update database with the approval action taken by the user. Also we need to validate if this makes any
	 * status change on the case status itself.
	 * 
	 * @param history
	 */
	public void updateApproverAction(CaseHistory history)
	{

		LoginMaster loginMaster = getLoggedInUser();
		CaseMaster caseMaster = caseRepository.getCase(history.getCaseId());

		CaseStatus status = CaseStatus.fromString(history.getStatus());

		CaseApprovalHistory caseAprovalHistory = new CaseApprovalHistory(caseMaster, loginMaster);
		caseAprovalHistory.setCaseStatusMaster((CaseStatusMaster) caseRepository.getById(CaseStatusMaster.class,
				status.getId()));

		caseAprovalHistory.setReason(history.getReason());

		caseRepository.save(caseAprovalHistory);

		// let us check if all the approvers for this case have approved this case.
		// Let us
		Organization org = (Organization) caseRepository.getById(Organization.class, 1);

		List<CaseTypeApprover> caseTypeApprovers = caseRepository.getCaseApproverList(caseMaster.getCaseType().getId(),
				org);

		// List<CaseHistory> caseHistoryList = getCaseApprovalHistory(history.getCaseId());
		List<CaseApprovalHistory> caseApprovalHistories = caseRepository.getCaseApprovalHistory(history.getCaseId());

		Integer actualApprovedCount = 0;

		Iterator<CaseTypeApprover> itCTA = caseTypeApprovers.iterator();

		// for(CaseTypeApprover caseTypeApprover:caseTypeApprovers)
		while (itCTA.hasNext())
		{
			CaseTypeApprover caseTypeApprover = itCTA.next();
			// for(CaseApprovalHistory caseHistory: caseApprovalHistories){
			Iterator<CaseApprovalHistory> itCAH = caseApprovalHistories.iterator();
			while (itCAH.hasNext())
			{
				CaseApprovalHistory caseHistory = itCAH.next();
				if (caseHistory.getLoginMaster().getId() == caseTypeApprover.getLoginMaster().getId())
				{
					if (caseHistory.getCaseStatusMaster().getCaseStatusName().equalsIgnoreCase("Approved"))
					{
						actualApprovedCount++;
					}
					break;
				}
			}
		}

		// if (caseRepository.getApprovedCount() > caseTypeApprovers.size() / 2)
		if (actualApprovedCount > caseTypeApprovers.size() / 2)
		{
			caseMaster.setCaseStatusMaster((CaseStatusMaster) caseRepository.getById(CaseStatusMaster.class,
					CaseStatus.ACTIVE.getId()));
			caseRepository.update(caseMaster);

		}
		// caseMaster.setUpdatedBy(loginMaster);
		// let us do the logic of verifying.

	}

	/**
	 * Let us try getting the histories.
	 * 
	 * @param caseId
	 * @return
	 */
	public List<CaseHistory> getCaseApprovalHistory(Integer caseId)
	{
		List<CaseHistory> histories = new ArrayList<CaseHistory>();
		List<CaseApprovalHistory> caseApprovalHistories = caseRepository.getCaseApprovalHistory(caseId);
		for (CaseApprovalHistory caseApprovalHistory : caseApprovalHistories)
		{
			histories.add(new CaseHistory(caseApprovalHistory.getCaseMaster().getId(), caseApprovalHistory.getReason(),
					caseApprovalHistory.getCaseStatusMaster().getCaseStatusName(), caseApprovalHistory.getLoginMaster()
							.getFirstName(), caseApprovalHistory.getApprovalDate(), caseApprovalHistory
							.getLoginMaster().getEmailId(), "NA")

			);

		}

		return histories;

	}

	public void saveCaseDiscussion(CaseDiscussionBO caseDiscussionBO)
	{
		CaseDiscussion caseDiscussion = new CaseDiscussion();
		LoginMaster loginMaster = getLoggedInUser();
		caseDiscussion.setLoginMaster(loginMaster);
		caseDiscussion.setComments(caseDiscussionBO.getComments());
		caseDiscussion.setSubject(caseDiscussionBO.getSubject());
		caseDiscussion.setCreatedOn(new Date());
		caseDiscussion
				.setCaseMaster((CaseMaster) caseRepository.getById(CaseMaster.class, caseDiscussionBO.getCaseId()));
		if (caseDiscussionBO.getParentDiscussionId() != null)
		{
			CaseDiscussion cd = (CaseDiscussion) caseRepository.getById(CaseDiscussion.class,
					caseDiscussionBO.getParentDiscussionId());
			caseDiscussion.setCaseDiscussion(cd);
			caseDiscussion.setSubject(cd.getSubject());
		}
		caseRepository.saveCaseDiscussion(caseDiscussion);

	}

	public List<CaseDiscussionBO> getCasesDiscussions(Integer caseId)
	{
		List<CaseDiscussionBO> caseDiscussions = new ArrayList<CaseDiscussionBO>();
		List<CaseDiscussion> lstCaseDiscussions = caseRepository.getCaseDiscussions(caseId);

		for (CaseDiscussion caseDiscussion : lstCaseDiscussions)
		{
			caseDiscussions
					.add(new CaseDiscussionBO(caseDiscussion.getId(), caseDiscussion.getCaseMaster().getId(),
							caseDiscussion.getLoginMaster().getFirstName(), caseDiscussion.getCreatedOn(),
							caseDiscussion.getComments(), caseDiscussion.getSubject(),

							caseDiscussion.getCaseDiscussion() != null ? caseDiscussion.getCaseDiscussion()
									.getSubject() : null, caseDiscussion.getCaseDiscussion() != null ? caseDiscussion
									.getCaseDiscussion().getLoginMaster().getFirstName() : null, caseDiscussion
									.getCaseDiscussion() != null ? caseDiscussion.getCaseDiscussion().getId() : null));
		}
		return caseDiscussions;

	}

	public List<FundBO> getFundsHistory(Integer caseId)
	{
		List<FundBO> fundsList = new ArrayList<FundBO>();
		List<Fund> lstFunds = caseRepository.getFundsHistory(caseId);

		for (Fund fund : lstFunds)
		{
			fundsList.add(new FundBO(fund.getId(), fund.getCaseMaster().getId(), fund.getDonor(), fund.getFundStatus()
					.getName(), fund.getPromisedDate(), fund.getConfirmedDate(), fund.getPurpose(), fund
					.getCreditAmount()));
		}
		return fundsList;
	}

	public void saveFund(FundBO fundBO)
	{
		Fund fund = new Fund();
		LoginMaster loginMaster = getLoggedInUser();
		fund.setLoginMaster(loginMaster);
		fund.setCreditAmount(fundBO.getAmount());
		fund.setPromisedDate(fundBO.getPromisedDate());
		fund.setCaseMaster((CaseMaster) caseRepository.getById(CaseMaster.class, fundBO.getCaseId()));
		fund.setDonor(fundBO.getDonor());
		fund.setPromisedDate(new Date());
		fund.setPurpose(fundBO.getPurpose());
		fund.setFundStatus((FundStatus) caseRepository.getById(FundStatus.class, 1));
		caseRepository.saveFund(fund);
	}

	public void commitFund(FundBO fundBO)
	{
		Fund fund = (Fund) caseRepository.getById(Fund.class, fundBO.getId());
		LoginMaster loginMaster = getLoggedInUser();

		// Need to change this to confirm user
		fund.setConfirmationComments(fundBO.getPurpose());
		fund.setReceiptDescription(fundBO.getPurpose());
		fund.setLoginMaster(loginMaster);
		fund.setConfirmedDate(new Date());
		fund.setFundStatus((FundStatus) caseRepository.getById(FundStatus.class, 2));
		String receiptFileName = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String confirmedDateValue = sdf.format(fund.getConfirmedDate());

		try
		{
			receiptFileName = pdfGenerator.generateReceipt(fund.getDonor(), fund.getCreditAmount().toString(),
					fund.getReceiptDescription() + "", ONLINE_TRANSFER, confirmedDateValue, fund.getId().toString());
		}
		catch (IOException e)
		{
			log.error("error while generating signed receipt", e);
		}
		catch (GeneralSecurityException e)
		{
			log.error("error while generating signed receipt", e);
		}
		try
		{
			if (receiptFileName != null)
			{
				FileInputStream receiptFile = new FileInputStream(receiptFileName);

				File receiptFileObj = new File(receiptFileName);
				byte[] fileContent = new byte[(int) receiptFileObj.length()];

				receiptFile.read(fileContent);
				fund.setReceipt(fileContent);
				receiptFile.read(fund.getReceipt());
				receiptFile.close();

			}
		}
		catch (IOException e)
		{
			log.error("error while saving signed receipt into database", e);
		}
		caseRepository.updateFund(fund);
	}

	public byte[] getCaseArtifactById(Integer id)
	{
		CaseArtifact artifact = (CaseArtifact) caseRepository.getById(CaseArtifact.class, id);
		return artifact.getArtifact();

	}
	
	public List<FundBO> getFundsHistoryForUser()
	{
		List<FundBO> fundsList = new ArrayList<FundBO>();
		LoginMaster loginMaster = getLoggedInUser();
		List<Fund> lstFunds = caseRepository.getFundsHistory(loginMaster.getId());

		for (Fund fund : lstFunds)
		{
			fundsList.add(new FundBO(fund.getId(), fund.getCaseMaster().getId(), fund.getDonor(), fund.getFundStatus()
					.getName(), fund.getPromisedDate(), fund.getConfirmedDate(), fund.getPurpose(), fund.getCreditAmount()));
		}
		return fundsList;
	}




}
