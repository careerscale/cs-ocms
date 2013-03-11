package in.careerscale.apps.ocms.web.backoffice;

import in.careerscale.apps.ocms.service.BackOfficeService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_USER")
public class BackOfficeController {

	
	@Autowired
	private BackOfficeService backOfficeService;

	
	@RequestMapping(value = "/backoffice", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		return "backoffice/index";
	}

	
	
	@RequestMapping(value = "/backoffice/casetype", method = RequestMethod.GET)
	public String caseTypeIndex(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		return "backoffice/casetype";
	}
	
	@RequestMapping(value = "/backoffice/casetype", method = RequestMethod.POST)
	public String addCaseType(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//TODO Validations on server side
		
        try{
        	backOfficeService.addCaseType(bean);
        	
        }catch(ApplicationException ae){
       		
        	//ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
        	errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
        	return "backoffice/casetype";
		}
        return "backoffice/success"; // we need to return next page.
	}
	
	@RequestMapping(value = "/backoffice/helptype", method = RequestMethod.GET)
    public String HelpTypeIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
        System.out.println("into the get method call");

        return "backoffice/helptype";
    }
    
    @RequestMapping(value = "/backoffice/helptype", method = RequestMethod.POST)
    public String addHelpCategoryType(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addHelpCategoryType(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/help_category_type";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
	
}
