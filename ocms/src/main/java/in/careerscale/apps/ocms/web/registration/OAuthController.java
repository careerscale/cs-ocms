package in.careerscale.apps.ocms.web.registration;

import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_ANONYMOUS")
public class OAuthController  {

	Log log = LogFactory.getLog(OAuthController.class);
	@Autowired
	private UserService userService;

	
	
	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		
        log.debug("within index metho in OAuthController");
		return "oauth/oauthprofile";
	}


}
