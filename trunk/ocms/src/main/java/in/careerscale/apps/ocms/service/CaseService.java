package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.NotificationRepository;
import in.careerscale.apps.ocms.dao.model.Address;
import in.careerscale.apps.ocms.dao.model.CaseApprovalHistory;
import in.careerscale.apps.ocms.dao.model.CaseArtifact;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseStatus;
import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.CaseTypeApprover;
import in.careerscale.apps.ocms.dao.model.City;
import in.careerscale.apps.ocms.dao.model.DocumentType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.Notification;
import in.careerscale.apps.ocms.dao.model.NotificationRecipient;
import in.careerscale.apps.ocms.dao.model.NotificationStatus;
import in.careerscale.apps.ocms.dao.model.NotificationTemplate;
import in.careerscale.apps.ocms.dao.model.Organization;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.cases.model.CaseArtifacts;
import in.careerscale.apps.ocms.web.cases.model.CaseHistory;
import in.careerscale.apps.ocms.web.cases.model.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("caseService")
public class CaseService extends AbstractService
{

	Log log = LogFactory.getLog(CaseService.class);

	@Autowired
	private CaseRepository caseRepository;

	@Autowired
	private NotificationRepository notificationRepository;

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
				String fileName = document.getFile().getName();
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
		//Let us 
		Organization org = (Organization) caseRepository.getById(Organization.class, 1);
		
		List<CaseTypeApprover> caseTypeApprovers = caseRepository.getCaseApproverList(caseMaster.getCaseType().getId(),
				org);

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
							.getFirstName()));
			
		}
		
		return histories;
		

	}

}
