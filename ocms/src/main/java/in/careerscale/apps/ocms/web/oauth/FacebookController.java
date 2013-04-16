package in.careerscale.apps.ocms.web.oauth;

import static in.careerscale.apps.ocms.web.oauth.SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN;
import static in.careerscale.apps.ocms.web.oauth.SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;
import in.careerscale.apps.ocms.integration.oauth.OAuthServiceProvider;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.model.RegistrationResult;
import in.careerscale.apps.ocms.web.oauth.util.OAUthParser;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class FacebookController {

	Log log = LogFactory.getLog(FacebookController.class);
	@Autowired
	@Qualifier("facebookServiceProvider")
	private OAuthServiceProvider facebookServiceProvider;

	private static final Token EMPTY_TOKEN = null;
	private static final String MESSAGE = "message";
	private static final String NAME = "name";
	private static final String CAPTION = "caption";
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login-facebook" }, method = RequestMethod.GET)
	public String login(WebRequest request) {

		// getting request and access token from session
		Token accessToken = (Token) request.getAttribute(
				ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if (accessToken == null) {
			// generate new request token
			OAuthService service = facebookServiceProvider.getService();

			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, EMPTY_TOKEN,
					SCOPE_SESSION);

			// redirect to facebook auth page
			return "redirect:" + service.getAuthorizationUrl(EMPTY_TOKEN);
		}
		// TODO
		// Get user information and update spring security context for the user.
		// Since we are trusting thirdparty oauth providers
		return "home/index";
	}

	@RequestMapping(value = { "/facebook-callback" }, method = RequestMethod.GET)
	public String callback(
			@RequestParam(value = "code", required = false) String oauthVerifier,
			WebRequest request, HttpServletRequest req,
			HttpServletResponse response) {

		// getting request token
		OAuthService service = facebookServiceProvider.getService();
		Token requestToken = (Token) request.getAttribute(
				ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);

		// getting access token
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);

		// store access token as a session attribute
		request.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken,
				SCOPE_SESSION);

		// getting user profile
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET,
				"https://graph.facebook.com/me");
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();

		log.debug("Facebook response " + oauthResponse.getBody());

		User user = OAUthParser.getUserFromFacebookUserProfile(oauthResponse
				.getBody());
		user.setUserAccessToken(accessToken.getToken());

		try {
			RegistrationResult result =userService.registerUser(user);
			if(result == RegistrationResult.SocialRegistered){
				//TODO, organize it better.
				oauthRequest = new OAuthRequest(Verb.POST,
						"https://graph.facebook.com/" + user.getSocialNetworkId()
								+ "/feed");
				//TODO push the template to property file. bad to hard code stuff like this.
				String message = "I have registered on OCMS @ http://ocms-careerscale.rhcloud.com/ OCMS is a technology platform that is being created by careerscale (http://careerscale.in) to help organizations like To Make A Difference (http://tmad.org) and bring more help to the needy people.";
				String name = "Online Case Management System";
				String caption = "Connecting the needy with the donors";
				//String description = 
				oauthRequest.addQuerystringParameter(MESSAGE, message);
				oauthRequest.addBodyParameter(NAME, name);
				oauthRequest.addBodyParameter(CAPTION, caption);
				//oauthRequest.addBodyParameter(DESCRIPTION, description);
				service.signRequest(accessToken, oauthRequest);
				Response res = oauthRequest.send();
				log.info(res.getBody());
			}
			
		} catch (Exception e) {
			log.error("Error while registering google user", e);
		}
		try {
			AuthrnicationController authrnicationController = new AuthrnicationController();
			authrnicationController.authenticate(user);
			response.sendRedirect("/user");
		} catch (Exception e) {
			log.error("Error while logging in google user", e);
		}
		return "home/index";

	}
}