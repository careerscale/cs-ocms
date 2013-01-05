package in.careerscale.apps.ocms.web.registration;



import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_ANONYMOUS")
public class RegistrationController implements Validator {

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");
		return "register/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(bean.getEmailId());
		System.out.println(bean.getDateOfBirth());
		System.out.println(bean);
		System.out.println(bean.getFirstName());
		//
		return "register/register"; // we need to return next page.
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		

	}

}
