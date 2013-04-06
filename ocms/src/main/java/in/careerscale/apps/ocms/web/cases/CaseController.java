package in.careerscale.apps.ocms.web.cases;



import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.web.registration.model.User;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Secured("ROLE_USER")
public class CaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CaseController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "cases", method = RequestMethod.GET)	
	public String index(UserDetails userDetails, Model model) {
		LOG.info(userDetails.toString());
		return "cases/index";
	}
	
	@RequestMapping(value = "cases/active", method = RequestMethod.GET)	
	public String activeCaess(UserDetails userDetails, Model model) {
		LOG.info(userDetails.toString());
		return "cases/activecases";
	}
}
