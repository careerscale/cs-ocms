package in.careerscale.apps.ocms.web.oauth;

import static in.careerscale.apps.ocms.web.oauth.SessionAttributes.ATTR_OAUTH_ACCESS_TOKEN;
import static in.careerscale.apps.ocms.web.oauth.SessionAttributes.ATTR_OAUTH_REQUEST_TOKEN;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;
import in.careerscale.apps.ocms.integration.oauth.OAuthServiceProvider;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.oauth.util.OAUthParser;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

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
import org.xml.sax.SAXException;

@Controller
public class TwitterController {

	Log log = LogFactory.getLog(TwitterController.class);
	@Autowired
	@Qualifier("twitterServiceProvider")
	private OAuthServiceProvider twitterServiceProvider;

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/login-twitter" }, method = RequestMethod.GET)
	public String login(WebRequest request) {

		// getting request and access token from session
		Token requestToken = (Token) request.getAttribute(
				ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		Token accessToken = (Token) request.getAttribute(
				ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if (requestToken == null || accessToken == null) {
			// generate new request token
			OAuthService service = twitterServiceProvider.getService();
			requestToken = service.getRequestToken();
			request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, requestToken,
					SCOPE_SESSION);

			// redirect to twitter auth page
			return "redirect:" + service.getAuthorizationUrl(requestToken);
		}
		// Get user information and update spring security context for the user.
		// Since we are trusting thirdparty oauth providers
		return "home/index";
	}

	@RequestMapping(value = { "/twitter-callback" }, method = RequestMethod.GET)
	public String callback(
			@RequestParam(value = "oauth_token", required = false) String oauthToken,
			@RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
			WebRequest request, HttpServletRequest req) {

		// getting request token
		OAuthService service = twitterServiceProvider.getService();
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
				"http://api.twitter.com/1/account/verify_credentials.xml");
		service.signRequest(accessToken, oauthRequest); // the access token from
														// step 4
		Response oauthResponse = oauthRequest.send();
		log.debug(oauthResponse.getBody());

		User user = null;
		try {
			user = OAUthParser.getUserFromTwitterUserProfile(oauthResponse
					.getBody());
		} catch (XPathExpressionException e1) {
			log.error(e1);
		} catch (ParserConfigurationException e1) {
			log.error(e1);
		} catch (SAXException e1) {
			log.error(e1);
		} catch (IOException e1) {
			log.error(e1);
		}
		try {
			userService.registerUser(user);
		} catch (ApplicationException e) {
			log.error("Error whle registering the twitter user in db", e);
		}
		return "oauth/oauthprofile";

	}
}