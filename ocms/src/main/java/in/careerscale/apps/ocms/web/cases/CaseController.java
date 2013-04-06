package in.careerscale.apps.ocms.web.cases;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.careerscale.apps.ocms.service.CaseService;
import in.careerscale.apps.ocms.service.MasterDataService;

import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.registration.RegistrationController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@Controller
@Secured("ROLE_USER")
public class CaseController implements Validator  {
	
	Log log = LogFactory.getLog(CaseController.class);
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private MasterDataService masterDataService;
	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("within login GET method call");

		return "register/login";
	}*/
	
	@RequestMapping(value = "/case", method = RequestMethod.GET)	
	public String index(@ModelAttribute(value = "case") Case bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception
			{
				setMasterData(bean);
				return "cases/addcases";
		
	}
	
	@RequestMapping(value = "/case", method = RequestMethod.POST)
	public String register(@ModelAttribute(value = "case") Case bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception
			{
		try{
			caseService.registerCase(bean);
		}catch(ApplicationException ae){
			errors.addError(new ObjectError("view.case.registration.error", "case is already registered, please choose another one" ));
        	setMasterData(bean);
			return "cases/addcases";
			
		}
		return "cases/registeredcases";
			}
	
	
	private void setMasterData(Case bean){
		try {
			bean.setCaseMasterTypes(masterDataService.getCaseTypes1());
			bean.setHelpMasterTypes(masterDataService.getHelpTypes());
		} catch (ApplicationException e) {
			log.error("error while retrieving master data", e);
		}
	}
	

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
}
