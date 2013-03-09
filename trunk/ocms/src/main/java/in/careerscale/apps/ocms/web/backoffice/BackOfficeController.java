package in.careerscale.apps.ocms.web.backoffice;

import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_USER")
public class BackOfficeController {

	
	@RequestMapping(value = "/admin/casetype", method = RequestMethod.GET)
	public String caseTypeIndex(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		return "backoffice/casetype";
	}
}
