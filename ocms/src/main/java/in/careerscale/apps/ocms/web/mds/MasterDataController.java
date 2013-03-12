package in.careerscale.apps.ocms.web.mds;


import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_ANONYMOUS")

public class MasterDataController {
	
	
	
	@RequestMapping(value = "/master/casesubtype", method = RequestMethod.GET)
	
	public @ResponseBody Map<String, String> getCaseSubTypes(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the login");
		Map<String, String> subTypes = new HashMap<String, String>();
		subTypes.put("22", "Heart Operation");
		subTypes.put("23", "Kidney Operation");

		BOBean bean = new BOBean();
		bean.setName("harinath");
		bean.setDescription("dummy description");
		return subTypes ;
	}

}
