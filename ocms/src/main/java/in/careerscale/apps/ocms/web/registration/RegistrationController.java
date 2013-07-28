package in.careerscale.apps.ocms.web.registration;

import in.careerscale.apps.ocms.common.captcha.ReCaptchaResponse;
import in.careerscale.apps.ocms.common.captcha.ReCaptchaService;
import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Secured("ROLE_ANONYMOUS")
public class RegistrationController
{

	Log log = LogFactory.getLog(RegistrationController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private ReCaptchaService reCaptchaService;

	@Value("captchaEnabled")
	private String captchaEnabled;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("within login GET method call");

		return "register/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "user") User bean, BindingResult errors, HttpServletRequest request,
			HttpServletResponse response)
	{
		setMasterData(bean);
		return "register/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute(value = "user") User bean,
			@RequestParam("recaptcha_challenge_field") String challangeField,
			@RequestParam("recaptcha_response_field") String responseField, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		String remoteAddress = "http://careerscale.in";
		boolean isCaptchaValid = true;

		if ("true".equalsIgnoreCase(captchaEnabled))
		{
		ReCaptchaResponse reCaptchaResponse = this.reCaptchaService.checkAnswer(remoteAddress, challangeField,
				responseField);
			isCaptchaValid = reCaptchaResponse.isValid();

		}

		if (isCaptchaValid)
		{
			// TODO Validations on server side
			try
			{
				userService.registerUser(bean);
			}
			catch (ApplicationException ae)
			{
				errors.addError(new ObjectError("view.user.registration.error", ae.getMessage()));
				setMasterData(bean);
				return "register/register";
			}
		}
		else
		{
			errors.addError(new ObjectError("view.user.registration.error", "Image validation failed, please try again"));
			setMasterData(bean);
			return "register/register";
		}

		request.setAttribute("result", "register.registered.success");
		return "user/success"; // we need to return next page.
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

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verifyEmail(HttpServletRequest request, HttpServletResponse response)
	{

		String id = request.getParameter("id");
		String secret = request.getParameter("secret");
		userService.verifyEmail(id, secret);
		return "home/index";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotPassword(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
	{
		setMasterData(bean);
		return "register/forgotpassword";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String submitForgotPassword(@ModelAttribute(value = "user") User bean, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			userService.forgotPassword(bean);
		}
		catch (Exception e)
		{

			// ae.getCause() == null?
			// ae.getMessage():ae.getCause().getMessage())
			errors.addError(new ObjectError("registration.forgotpassword.error", "Unable to fetch password : "
					+ e.getMessage()));
			return "register/forgotpassword";
		}
		request.setAttribute("result", "user.forgotpassword.success");
		return "user/success";
	}

}
