package in.careerscale.apps.ocms.web.user;



import in.careerscale.apps.ocms.service.CaseService;
import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.registration.model.MyCases;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Secured("ROLE_USER")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CaseService caseService;
	
	@Autowired
	private MasterDataService masterDataService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)	
	public String index(@ModelAttribute(value = "cases") MyCases bean, BindingResult errors,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		List<in.careerscale.apps.ocms.web.cases.model.Case> myApprovedCases = caseService.getCasesToBeActedUpon();
		List<in.careerscale.apps.ocms.web.cases.model.Case> myCasesList = caseService.getMyCases();
		List<in.careerscale.apps.ocms.web.cases.model.Case> interestedCasesList = caseService.getInterestedCases();
		bean.setMyApprovedCases(myApprovedCases);
		
		/*Iterator<in.careerscale.apps.ocms.web.cases.model.Case> itMyCases = myCasesList.iterator();
		Iterator<in.careerscale.apps.ocms.web.cases.model.Case> itInterestedCases = interestedCasesList.iterator();
		while(itInterestedCases.hasNext())
		{
			
		}*/
		
		interestedCasesList = (List<Case>) CollectionUtils.subtract(interestedCasesList, myCasesList);
		bean.setInterestedCases(interestedCasesList);
		bean.setMyCases(myCasesList);
		return "user/index";
	}
	
	@RequestMapping(value = "user.json", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public User jsonGetUser(UserDetails userDetails) {
		return userService.findByUsername(userDetails.getUsername());
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfileData(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		setMasterData(bean);
		userService.getUserProfile(bean);
		return "user/profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String updateProfileData(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// TODO Validations on server side
		try
		{
			userService.updateUserProfile(bean);
		}
		catch (ApplicationException ae)
		{

			// ae.getCause() == null?
			// ae.getMessage():ae.getCause().getMessage())
			log.error(ae.getMessage(), ae.getCause());
			errors.addError(new ObjectError("user.profile.error", "Error while updating profile - "
					+ ae.getLocalizedMessage()));
			setMasterData(bean);
			return "user/profile";
		}
		request.setAttribute("result", "register.registered.success");
		return "user/success"; // we need to return next page.
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String getPasswordForm(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return "user/password";
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String updatePassword(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		// TODO Validations on server side
		try
		{
			userService.updateUserPassword(bean);
		}
		catch (ApplicationException ae)
		{

			// ae.getCause() == null?
			// ae.getMessage():ae.getCause().getMessage())
			log.error(ae.getMessage(), ae.getCause());
			errors.addError(new ObjectError("password.update.error",
					"Unable to update password, please try again."));

			return "user/password";
		}
		request.setAttribute("result", "password.update.success");
		return "user/success"; // we need to return next page.
	}



	private void setMasterData(User bean)
	{
		try
		{
			bean.setCaseMasterTypes(masterDataService.getCaseTypes1());
			bean.setHelpMasterTypes(masterDataService.getHelpTypes());
			bean.setCountryMasterTypes(masterDataService.getCountries());
			if (bean.getCountryId() == null)
			{
				bean.setStateMasterTypes(masterDataService.getStates(bean.getCountryMasterTypes().get(0).getId()));
				if (bean.getStateId() == null)
					bean.setCityMasterTypes(masterDataService.getCities(bean.getStateMasterTypes().get(0).getId()));

			}
		}
		catch (ApplicationException e)
		{
			log.error("error while retrieving master data", e);
		}
	}

}
