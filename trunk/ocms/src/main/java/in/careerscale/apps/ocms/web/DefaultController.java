package in.careerscale.apps.ocms.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping("/default/**/*")
	public void defaultRequest() {}
	
	@RequestMapping("/login")
	public String login() {
		
		return "default/login";
		
	}
}