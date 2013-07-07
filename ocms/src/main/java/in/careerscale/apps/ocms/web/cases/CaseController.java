package in.careerscale.apps.ocms.web.cases;

import in.careerscale.apps.ocms.service.CaseService;
import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.cases.model.CaseArtifacts;
import in.careerscale.apps.ocms.web.cases.model.DocumentType;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO implement validations here
		caseService.saveCaseAtrifacts(bean);
		return "cases/registeredcases";

	}

	@RequestMapping(value = "/cases/{id}", method = RequestMethod.GET)
	public String getCaseDetails(@ModelAttribute(value = "caseDetails") Case bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id)
	{
		caseService.getCaseDetails(id, bean);
		CaseArtifacts caseArtifacts = caseService.getCaseArtifacts(id);
		request.setAttribute("caseArtifacts", caseArtifacts);
		setMasterData(bean);
		return "cases/casedetails";
	}

	@RequestMapping(value = "/cases/documents/{id}")
	public void writePicture(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		// http://stackoverflow.com/questions/15654036/how-to-retrive-image-from-mysql-database-using-spring
		/**
		 * try { Picture img = pictureService.findById(id); response.setContent(picture.getImagefile());
		 * response.setContentLength(picture.getImagefile().length);
		 * 
		 * // additionally, you should add the mime-type and the last // change date (to allow the browsers to use the
		 * cache) if these info are available
		 * 
		 * response.getOutputStream().write(picture.getImagefile()); response.setStatus(HttpServletResponse.SC_OK); }
		 * catch (Exception e) { response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404. Add specific catch for
		 * specific errors }
		 */
	}


}
