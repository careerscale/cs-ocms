package in.careerscale.apps.ocms.web.user;



import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private MasterDataService masterDataService;

	@RequestMapping(value = "user", method = RequestMethod.GET)	
	public String index(UserDetails userDetails, Model model) {
		log.info(userDetails.toString());
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
	public String UpdateProfileData(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		// TODO Validations on server side
		try
		{
			userService.registerUser(bean);
		}
		catch (ApplicationException ae)
		{

			// ae.getCause() == null?
			// ae.getMessage():ae.getCause().getMessage())
			errors.addError(new ObjectError("view.user.registration.error",
					"Email is already in use, please choose another one"));
			setMasterData(bean);
			return "user/profile";
		}
		return "register/registered"; // we need to return next page.
	}

	private void setMasterData(User bean)
	{
		try
		{
			bean.setCaseMasterTypes(masterDataService.getCaseTypes1());
			bean.setHelpMasterTypes(masterDataService.getHelpTypes());
		}
		catch (ApplicationException e)
		{
			log.error("error while retrieving master data", e);
		}
	}

}
