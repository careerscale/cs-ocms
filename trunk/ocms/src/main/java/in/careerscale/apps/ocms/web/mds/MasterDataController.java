package in.careerscale.apps.ocms.web.mds;


import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_ANONYMOUS")

public class MasterDataController {
	
	@Autowired
	MasterDataService masterDataService;
	
	
	@RequestMapping(value = "/master/casesubtype", method = RequestMethod.GET)
	
	public @ResponseBody Map<String, String> getCaseSubTypes(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the login");
		Map<String, String> subTypes = new HashMap<String, String>();
		subTypes.put("22", "Heart Operation");
		subTypes.put("23", "Kidney Operation");		
		return subTypes ;
	}
	
	@RequestMapping(value = "/master/casetypes", method = RequestMethod.GET)	
	public @ResponseBody Map<Integer, String> getCaseTypes(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the getCasetypes");
		Map<Integer, String> caseTypes = new HashMap<Integer, String>();
		
	
		  try{
			  caseTypes = masterDataService.getCaseTypes();
		        }catch(ApplicationException ae){
		       		//TODO log this exception, I dont want to expect this at any cost, but still remedy should be there.
		        	ae.printStackTrace();
		        	//TODO remove printStackTrace once stabilized and log it.
				}
		return caseTypes ;
	}

}
