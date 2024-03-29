package in.careerscale.apps.ocms.web.oauth;

import static in.careerscale.apps.ocms.web.oauth.SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN;
import static in.careerscale.apps.ocms.web.oauth.SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.careerscale.apps.ocms.integration.oauth.OAuthServiceProvider;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.oauth.util.OAUthParser;
import in.careerscale.apps.ocms.web.registration.model.User;

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
public class GoogleController {

	Log log = LogFactory.getLog(GoogleController.class);
	@Autowired
	@Qualifier("gmailServiceProvider")
	private OAuthServiceProvider gmailServiceProvider;

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login-google" }, method = RequestMethod.GET)
	public String login(WebRequest request) {

		// getting request and access token from session
		Token requestToken = (Token) request.getAttribute(
				ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		Token accessToken = (Token) request.getAttribute(
				ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if (requestToken == null || accessToken == null) {
			// generate new request token
			OAuthService service = gmailServiceProvider.getService();
			requestToken = service.getRequestToken();
			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, requestToken,
					SCOPE_SESSION);
			// request.setAttribute("scope",
			// "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&",
			// SCOPE_SESSION);

			// redirect to gmail auth page
			return "redirect:" + service.getAuthorizationUrl(requestToken);
		}
		// TODO
		// Get user information and update spring security context for the user.
		// Since we are trusting thirdparty oauth providers
		return "home/index";
	}

	@RequestMapping(value = { "/google-callback" }, method = RequestMethod.GET)
	public String callback(
			@RequestParam(value = "oauth_token", required = false) String oauthToken,
			@RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
			WebRequest request, HttpServletRequest req,
			HttpServletResponse response) {

		// getting request token
		OAuthService service = gmailServiceProvider.getService();
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
				"https://www.googleapis.com/oauth2/v1/userinfo");
		service.signRequest(accessToken, oauthRequest); // the access token from
														// step 4
		Response oauthResponse = oauthRequest.send();
		log.debug(oauthResponse.getBody());
		User user = OAUthParser.getUserFromGoogleUserProfile(oauthResponse
				.getBody());
		user.setUserAccessToken(accessToken.getToken());
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			log.error("Error while registering google user", e);
		}
		try {
			AuthenticationController authrnicationController = new AuthenticationController();
			authrnicationController.authenticate(user);
			response.sendRedirect("/user");
		} catch (Exception e) {
			log.error("Error while logging in google user", e);
		}
		
		return "home/index";
	}
}