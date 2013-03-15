package in.careerscale.apps.ocms.web.registration;

import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_ANONYMOUS")
public class OAuthController  {

	@Autowired
	private UserService userService;

	@Autowired
	private DaoAuthenticationProvider authenticationProvider;

	
	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		return "oauth/oauthprofile";
	}


}
