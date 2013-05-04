package in.careerscale.apps.ocms.web.mds;

import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.service.model.MasterType;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_ANONYMOUS")
public class MasterDataController {

	Log log = LogFactory.getLog(MasterDataController.class);

	@Autowired
	MasterDataService masterDataService;

	@RequestMapping(value = "/master/casesubtype", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> getCaseSubTypes(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, String> subTypes = new HashMap<String, String>();
		subTypes.put("22", "Heart Operation");
		subTypes.put("23", "Kidney Operation");
		return subTypes;
	}

	@RequestMapping(value = "/master/casetypes1", method = RequestMethod.GET)
	public @ResponseBody
	Map<Integer, String> getCaseTypes(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("into the getcasetypes");
		Map<Integer, String> caseTypes = new HashMap<Integer, String>();

		try {
			caseTypes = masterDataService.getCaseTypes();
		} catch (ApplicationException ae) {
			// TODO log this exception, I dont want to expect this at any cost,
			// but still remedy should be there.
			ae.printStackTrace();
			// TODO remove printStackTrace once stabilized and log it.
		}
		return caseTypes;
	}

	@RequestMapping(value = "/master/casetypes", method = RequestMethod.GET)
	public String getCaseTypes1(
			@ModelAttribute(value = "botypeList") ArrayList<BOBean> lstBean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {

		log.debug("into the getCasetypes");
		Map<Integer, String> caseTypes = new HashMap<Integer, String>();

		try {
			caseTypes = masterDataService.getCaseTypes();
		} catch (ApplicationException ae) {
			log.error(ae);
		}

		BOBean boBean = null;
		Iterator<Integer> it = caseTypes.keySet().iterator();
		Integer intName = null;
		while (it.hasNext()) {
			intName = (Integer) it.next();
			boBean = new BOBean();
			boBean.setName(intName.toString());
			boBean.setDescription(caseTypes.get(intName));
			lstBean.add(boBean);
		}
		return "backoffice/casetype";

	}

	@RequestMapping(value = "/master/casetypes2", method = RequestMethod.GET)
	public @ResponseBody
	List<MasterType> getCaseTypes2(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("into the getCasetypes");
		List<MasterType> caseTypes = new ArrayList<MasterType>();

		try {
			caseTypes = masterDataService.getCaseTypes1();
		} catch (ApplicationException ae) {
			log.error(ae);
		}
		return caseTypes;
	}

	@RequestMapping(value = "/master/countries", method = RequestMethod.GET)
	public @ResponseBody
	List<MasterType> getCountries(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("into getCountries");
		List<MasterType> countries = new ArrayList<MasterType>();

		try {
			countries = masterDataService.getCountries();
		} catch (ApplicationException ae) {
			// TODO log this exception, I dont want to expect this at any cost,
			// but still remedy should be there.
			log.equals(ae);
		}
		return countries;
	}

	@RequestMapping(value = "/master/states", method = RequestMethod.GET)
	public @ResponseBody
	List<MasterType> getStates(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("into getStates");

		List<MasterType> states = new ArrayList<MasterType>();
		
		if (StringUtils.isBlank(request.getParameter("countryId"))) {
			return states;
		}
        Integer countryId = Integer.parseInt(request.getParameter("countryId"));
		try {
			states = masterDataService.getStates(countryId);
		} catch (ApplicationException ae) {
			// TODO log this exception, I dont want to expect this at any cost,
			// but still remedy should be there.
			log.equals(ae);
		}
		return states;
	}
	
	
	@RequestMapping(value = "/master/cities", method = RequestMethod.GET)
	public @ResponseBody
	List<MasterType> getCities(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("into getStates");

		List<MasterType> cities = new ArrayList<MasterType>();
		
		if (StringUtils.isBlank(request.getParameter("stateId"))) {
			return cities;
		}
        Integer stateId = Integer.parseInt(request.getParameter("stateId"));
		try {
				cities= masterDataService.getCities(stateId);
		} catch (ApplicationException ae) {
			// TODO log this exception, I dont want to expect this at any cost,
			// but still remedy should be there.
			log.equals(ae);
		}
		return cities;
	}
	
	
	

}
