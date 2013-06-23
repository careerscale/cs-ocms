package in.careerscale.apps.rms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "home/index";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutUs(Model model) {
		return "home/about";
	}

}
