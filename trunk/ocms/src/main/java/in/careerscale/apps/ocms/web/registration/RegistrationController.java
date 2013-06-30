package in.careerscale.apps.ocms.web.registration;

import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_ANONYMOUS")
public class RegistrationController implements Validator {

	Log log = LogFactory.getLog(RegistrationController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private MasterDataService masterDataService;

	/**
	 * not in use for now. Old way of formatting date from input to java bean.
	 * but didn't find the solution yet.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		log.debug("within login GET method call");

		return "register/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		setMasterData(bean);
		return "register/register";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		setMasterData(bean);
		return "register/forgotpassword";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String submitForgotPassword(
			@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			userService.forgotPassword(bean);
		} catch (Exception e) {

			// ae.getCause() == null?
			// ae.getMessage():ae.getCause().getMessage())
			errors.addError(new ObjectError(
					"registration.forgotpassword.error",
					"Unable to fetch password : " + e.getMessage()));
			return "register/forgotpassword";
		}

		return "register/forgotpasswordSuccess";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute(value = "user") User bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Validations on server side
		try {
			userService.registerUser(bean);
		} catch (ApplicationException ae) {

			// ae.getCause() == null?
			// ae.getMessage():ae.getCause().getMessage())
			errors.addError(new ObjectError("view.user.registration.error",
					"Email is already in use, please choose another one"));
			setMasterData(bean);
			return "register/register";
		}
		return "register/registered"; // we need to return next page.
	}

	private void setMasterData(User bean) {
		try {
			bean.setCaseMasterTypes(masterDataService.getCaseTypes1());
			bean.setHelpMasterTypes(masterDataService.getHelpTypes());
		} catch (ApplicationException e) {
			log.error("error while retrieving master data", e);
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

}
