package in.careerscale.apps.ocms.web.oauth.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import in.careerscale.apps.ocms.service.model.SocialNetwork;
import in.careerscale.apps.ocms.web.registration.model.User;

import org.codehaus.jackson.map.util.JSONPObject;
import org.w3c.dom.Document;
public class OAUthParser {
	/*
	 * private String id; private String name; private String screenName;
	 * private String location; private String description; private String
	 * profileImageUrl; private String profileImageUrlHttps; private String url;
	 * private String protectedFlag; private String followersCount; private
	 * String profilebackGroundColors;
	 */

	public static User getUserFromTwitterUserProfile(String userXmlData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		User user = new User();
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		
		Document doc = dBuilder.parse(new InputSource(new StringReader(userXmlData)));

		XPath xp = XPathFactory.newInstance().newXPath();
		user.setNetwork(SocialNetwork.Twitter);
		user.setSocialNetworkId(xp.evaluate("/user/id/text()", doc));
		user.setFirstName(xp.evaluate("/user/name/text()", doc));
		user.setEmailId(xp.evaluate("/user/name/text()", doc));
		return user;
	}
	
	public static User getUserFromGoogleUserProfile(String userJSONData){
		User user = new User();
	    
		return user;
	}
	
	public static User getUserFromFacebookUserProfile(String userJSONData){
		User user = new User();
	    
		return user;
	}
	
	public static User getUserFromLinkedinUserProfile(String userJSONData){
		User user = new User();
	    
		return user;
	}
	
	
	

}
