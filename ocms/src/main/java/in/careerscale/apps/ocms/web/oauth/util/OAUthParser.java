package in.careerscale.apps.ocms.web.oauth.util;

import in.careerscale.apps.ocms.service.model.SocialNetwork;
import in.careerscale.apps.ocms.web.oauth.exception.LinkedInException;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OAUthParser {
	/*
	 * private String id; private String name; private String screenName;
	 * private String location; private String description; private String
	 * profileImageUrl; private String profileImageUrlHttps; private String url;
	 * private String protectedFlag; private String followersCount; private
	 * String profilebackGroundColors;
	 */

	public static User getUserFromTwitterUserProfile(String userXmlData)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		User user = new User();
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();

		Document doc = dBuilder.parse(new InputSource(new StringReader(
				userXmlData)));

		XPath xp = XPathFactory.newInstance().newXPath();
		user.setNetwork(SocialNetwork.Twitter);
		user.setSocialNetworkId(xp.evaluate("/user/id/text()", doc));
		user.setFirstName(xp.evaluate("/user/name/text()", doc));
		user.setEmailId(xp.evaluate("/user/screen_name/text()", doc));
		return user;
	}

	public static User getUserFromGoogleUserProfile(String userJSONData) {
		User user = new User();
		JsonElement jelement = new JsonParser().parse(userJSONData);
		JsonObject jobject = jelement.getAsJsonObject();
		Set<Entry<String, JsonElement>> entrySet = jobject.entrySet();
		user.setNetwork(SocialNetwork.Google);
		Map<String, String> googleAttributes = getAttributesMap(entrySet);
		user.setSocialNetworkId(googleAttributes.get("id"));
		user.setFirstName(googleAttributes.get("given_name"));
		user.setLastName(googleAttributes.get("family_name"));
		user.setEmailId(googleAttributes.get("email"));
		return user;
	}
	
	private static Map<String, String> getAttributesMap(
			Set<Entry<String, JsonElement>> entrySet) {
		Map<String, String> attributesMap = new HashMap<String, String>();
		Iterator<Entry<String, JsonElement>> entryIterator = entrySet
				.iterator();

		while (entryIterator.hasNext()) {
			Entry<String, JsonElement> entry = entryIterator.next();
			if(entry.getValue().isJsonNull())
				continue;
			attributesMap.put(entry.getKey(), entry.getValue().toString());

		}
		return attributesMap;
	}

	public static User getUserFromFacebookUserProfile(String userJSONData) {
		// "id":"501130713","name":"Harinath Mallepally","first_name":"Harinath","last_name":"Mallepally","link":"http:\/\/www.facebook.com\/harinath.mallepaly","username":"harinath.mallepaly","hometown":{"id":"115200305158163","name":"Hyderabad,
		// Andhra
		// Pradesh"},"location":{"id":"115200305158163","name":"Hyderabad,
		// Andhra Pradesh"},"bio":"An ordinary techy guy from Hyderabad,
		// India","work":[{"employer":{"id":"110755222281416","name":"IVY
		// Comptech"},"location":{"id":"115200305158163","name":"Hyderabad,
		// Andhra Pradesh"},"position":{"id":"143736908984719","name":"Project
		// Lead"},"start_date":"2011-09"},{"employer":{"id":"211723780536","name":"To
		// Make A
		// Difference"},"location":{"id":"115200305158163","name":"Hyderabad,
		// Andhra
		// Pradesh"},"position":{"id":"147416865300121","name":"President"},"description":"To
		// Make A Difference(TMAD) is a not for profit organization established
		// by people who wanted to see change in the lives of the down trodden.
		// It runs on contributions from donors and with no administrative
		// expenses. Every rupee TMAD gets is spent on activities in helping the
		// needy. (Though we are planning for dedicated employee(s) to take care
		// of extensive needs of the society and the volume of activities we are
		// taking
		// up.","start_date":"0000-00"},{"employer":{"id":"115531031877039","name":"Springboard
		// Technologies"},"location":{"id":"115200305158163","name":"Hyderabad,
		// Andhra Pradesh"},"position":{"id":"108121222554085","name":"Technical
		// Manager"},"start_date":"2008-12","end_date":"2011-09"}],"education":[{"school":{"id":"108222539212415","name":"Pondicherry
		// University"},"type":"College"},{"school":{"id":"135984663094404","name":"Andhra
		// Pradesh Residential Degree
		// College"},"type":"College"},{"school":{"id":"105912072776085","name":"Pondicherry
		// University"},"type":"Graduate
		// School","classes":[{"id":"187173314648857","name":"BSc"},{"id":"178078802236215","name":"MCA","with":[{"id":"785840163","name":"Adarsh
		// Padegal"},{"id":"100000016476112","name":"Punitha
		// Ram"}],"from":{"id":"100000016476112","name":"Punitha
		// Ram"}}]}],"gender":"male","timezone":5.5,"locale":"en_US","verified":true,"updated_time":"2013-03-15T16:37:28+0000"}
		
		User user = new User();
		JsonElement jelement = new JsonParser().parse(userJSONData);
		JsonObject jobject = jelement.getAsJsonObject();
		Set<Entry<String, JsonElement>> entrySet = jobject.entrySet();
		user.setNetwork(SocialNetwork.Facebook);
		Map<String, String> facebookAttributes = getAttributesMap(entrySet);
		user.setSocialNetworkId(facebookAttributes.get("id"));
		user.setFirstName(facebookAttributes.get("first_name"));
		user.setLastName(facebookAttributes.get("last_name"));
		user.setEmailId(facebookAttributes.get("username"));
		return user;
	
		
	}

	/**
	 * <person> <id>WlIVSIfVZi</id> <first-name>Harinath</first-name>
	 * <last-name>Mallepally</last-name> <industry>Information Technology and
	 * Services</industry> <headline>Partner at CareerScale IT Consulting
	 * LLP</headline> </person>
	 * 
	 * @param userXmlData
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static User getUserFromLinkedinUserProfile(String userXmlData)
			throws LinkedInException {
		User user = new User();

		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(new InputSource(new StringReader(
					userXmlData)));

			XPath xp = XPathFactory.newInstance().newXPath();
			user.setNetwork(SocialNetwork.Linkedn);
			user.setSocialNetworkId(xp.evaluate("/person/id/text()", doc));
			user.setFirstName(xp.evaluate("/person/first-name/text()", doc));
			user.setLastName(xp.evaluate("/person/last-name/text()", doc));
			//since linkedin doesnt provide email id, putting the id itself into the email id field to pass registration
			user.setEmailId(xp.evaluate("/person/id/text()", doc));
			

		}
		// ParserConfigurationException , SAXException, IOException
		catch (Exception e) {
			e.printStackTrace(); // TODO remove and replace with proper logging.
			new LinkedInException("Error while processing linkedin response", e);
		}
		return user;
	}

	public static void main(String[] args) {
		String json = "{\"id\": \"118352215351921173597\", \"name\": \"Harinath Mallepally\", \"given_name\": \"Harinath\", \"family_name\": \"Mallepally\", \"link\": \"https://plus.google.com/118352215351921173597\", \"gender\": \"male\", \"birthday\": \"0000-01-11\", \"locale\": \"en-GB\"}";

		OAUthParser.getUserFromGoogleUserProfile(json);

	}

}
