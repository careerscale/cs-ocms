package in.careerscale.apps.ocms.web.cases;

import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.service.CaseService;
import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.cases.model.CaseArtifacts;
import in.careerscale.apps.ocms.web.cases.model.CaseDiscussionBO;
import in.careerscale.apps.ocms.web.cases.model.CaseHistory;
import in.careerscale.apps.ocms.web.cases.model.DocumentType;
import in.careerscale.apps.ocms.web.cases.model.FundBO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@Secured("ROLE_USER")
public class CaseController
{

	Log log = LogFactory.getLog(CaseController.class);

	@Autowired
	private CaseService caseService;

	@Autowired
	private MasterDataService masterDataService;

	@RequestMapping(value = "/addcase", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "caseDetails") Case bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		setMasterData(bean);
		return "cases/addcases";

	}

	@RequestMapping(value = "/addcase", method = RequestMethod.POST)
	public ModelAndView registerCase(@ModelAttribute(value = "caseDetails") Case bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try
		{
			caseService.registerCase(bean);
		}
		catch (ApplicationException ae)
		{
			log.error("error while registering new case", ae);
			errors.addError(new ObjectError("view.case.registration.error",
					"case is already registered, please choose another one"));
			setMasterData(bean);
			return new ModelAndView("cases/addcases");

		}

		Integer caseTypeId = bean.getCaseType();
		ModelAndView result = new ModelAndView("cases/uploaddocs");
		result.addObject("docTypeList", caseService.getDocumentTypes(caseTypeId));
		result.addObject("caseTypeId", caseTypeId);
		result.addObject("caseId", bean.getId());
		CaseArtifacts caseArtifacts = new CaseArtifacts();
		caseArtifacts.setMasterTypes(caseService.getDocumentTypes(caseTypeId));
		request.setAttribute("caseArtifacts", caseArtifacts);
		return result;
	}

	private void setMasterData(Case bean)
	{
		try
		{
			bean.setCaseMasterTypes(masterDataService.getCaseTypes1());
			bean.setHelpMasterTypes(masterDataService.getHelpTypes());
			bean.setCountryMasterTypes(masterDataService.getCountries());
			if (bean.getCountryId() == null)
			{
				bean.setStateMasterTypes(masterDataService.getStates(bean.getCountryMasterTypes().get(0).getId()));
				if (bean.getStateId() == null)
					bean.setCityMasterTypes(masterDataService.getCities(bean.getStateMasterTypes().get(0).getId()));

			}

		}
		catch (ApplicationException e)
		{
			log.error("error while retrieving master data", e);
		}
	}

	/**
	 * this is dummy method to check if we are getting doc types properly or not. remove once the upload functionality
	 * is implemented
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cases/docs", method = RequestMethod.GET)
	public @ResponseBody
	List<DocumentType> getCaseArtifacts(HttpServletRequest request, HttpServletResponse response)
	{
		request.getParameter("caseTypeId");
		return caseService.getDocumentTypes(2);
	}

	@RequestMapping(value = "/cases/upload", method = RequestMethod.POST)
	public String uploadDocuments(@ModelAttribute(value = "caseArtifacts") CaseArtifacts bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		caseService.saveCaseAtrifacts(bean);
		// TODO implement validations here
		//CaseHistory caseHistory = new CaseHistory(caseId, reason, status);
		return "cases/registeredcases";

	}
	
	@RequestMapping(value = "/cases/caseAction", method = RequestMethod.POST)
	public String caseAction(@ModelAttribute(value = "caseHistory")  CaseHistory caseHistory, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// TODO implement validations here
		if(caseHistory.getStatus().contains("Approve"))
		{
			caseHistory.setStatus("Active");
		}else if(caseHistory.getStatus().contains("Reject"))
		{
			caseHistory.setStatus("Rejected");
		}else{
			caseHistory.setStatus("OnHold");
		}
		caseService.updateApproverAction(caseHistory);
		String ret= "redirect:/cases/"+caseHistory.getCaseId();;
		return ret;

	}
	
	@RequestMapping(value = "/cases/confirmDonation", method = RequestMethod.POST)
	public String confirmDonation(@ModelAttribute(value = "fund")  FundBO fund, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		caseService.commitFund(fund);
		String ret= "redirect:/cases/"+fund.getCaseId();;
		return ret;

	}
	
	
	
	@RequestMapping(value = "/cases/caseDiscussion", method = RequestMethod.POST)
	public String addDiscussion(@ModelAttribute(value = "caseDiscussion")  CaseDiscussionBO caseDiscussion, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		caseService.saveCaseDiscussion(caseDiscussion);
		String ret= "redirect:/cases/"+caseDiscussion.getCaseId();;
		return ret;

	}

	
	
	@RequestMapping(value = "/cases/addFund", method = RequestMethod.POST)
	public String addFund(@ModelAttribute(value = "fund")  FundBO fund, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		caseService.saveFund(fund);
		String ret= "redirect:/cases/"+fund.getCaseId();;
		return ret;

	}

	
	@RequestMapping(value = "/cases/receipts", method = RequestMethod.GET)
	public 
	String getFundHistoryForUser(@ModelAttribute(value = "caseDetails") Case bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
	{
		List<FundBO> funds = caseService.getFundsHistoryForUser();
		bean.setFunds(funds);
		return "user/receipts";
	}
	
	
	@RequestMapping(value = "/cases/{id}", method = RequestMethod.GET)
	public String getCaseDetails(@ModelAttribute(value = "caseDetails") Case bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id)
	{
		caseService.getCaseDetails(id, bean);
		CaseArtifacts caseArtifacts = caseService.getCaseArtifacts(id);
		request.setAttribute("caseArtifacts", caseArtifacts);
		setMasterData(bean);
		
		List<CaseHistory> caseHistoryList = caseService.getCaseApprovalHistory(id);
		bean.setCaseHistoryList(caseHistoryList);
		
		List<CaseDiscussionBO> caseDiscussions = caseService.getCasesDiscussions(id);
		bean.setCaseDiscussions(caseDiscussions);
		
		List<FundBO> funds = caseService.getFundsHistory(id);
		bean.setFunds(funds);
		
		String loggedInUserName = caseService.getLoggedInUser().getFirstName();
		for(CaseHistory caseHistory: caseHistoryList){
			if(caseHistory.getUserName().equalsIgnoreCase(loggedInUserName)){
				bean.setCaseStatusFromHistory(caseHistory.getStatus());
				break;
			}
		}
		
		return "cases/casedetails";
	}

	@RequestMapping(value = { "/cases/documents/{artifact_id}", "/cases/artifact/{artifact_id}" }, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody
	byte[] downloadFile(@PathVariable Integer artifact_id, HttpServletRequest request, HttpServletResponse response)
	{

		return caseService.getCaseArtifactById(artifact_id);
	}

	@RequestMapping(value = { "/cases/documents/pdf/{artifact_id}", "/cases/artifact/pdf/{artifact_id}" }, method = RequestMethod.GET, produces = "application/pdf")
	public @ResponseBody
	void downloadDynamicPDF(@PathVariable Integer artifact_id, HttpServletRequest request,
 HttpServletResponse response)
			throws IOException
	{

		try
		{
			Document document = new Document();
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			
			LoginMaster user = caseService.getLoggedInUser();
			document.addTitle("Welcome to OCMS");
			document.addHeader("Hello Welcome to OCMS", "The Online Case Management System");
			document.add(new Paragraph("Hello " + user.getFirstName()));
			document.add(new Paragraph("Current time is " + new Date().toString()));
			document.close();
		}
		catch (DocumentException de)
		{
			throw new IOException(de.getMessage());
		}
	}
}
