package in.careerscale.apps.rms.mail;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

public class EmailTemplates {
	
	private static String registrationTemplate ="Dear ${firstName}, \n Your  registration with "+
	"OCMS application is complete. \n Here are your login details:\n"+
	"username:  ${username} \n" +
	"Password:  ${password} \n" +
	"Thank you for registering with our site, Please login and starting using our website\n"+
	"Regards, \n"+
	"Administrator\n"+
	"OCMS\n"+
	"India\n" +
	"http://ocms-careerscale.rhcloud.com/";	
	
	private static String forgotPasswordTemplate ="Dear ${firstName}, \n We have received your forgot password request." +
			" \n Here are your login details:\n"+
				"username:  ${username} \n" +
				"Password:  ${password} \n" +
			"\nRegards, \n"+
			"Administrator\n"+
			"OCMS\n"+
			"India\n" +
			"http://ocms-careerscale.rhcloud.com/";
	
	public static final String firstName ="firstName";
	public static final String userName="username";
	public static final String password ="password";
	public static final String activationCode ="activationCode";
	
	public static String getEmailMessage(Template template, Map<String,String> valuesMap){
		
		
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String resolvedString =null;
		switch(template){
		case Registration:
			resolvedString = sub.replace(registrationTemplate);
			break;
		case ForgotPassword:
			resolvedString = sub.replace(forgotPasswordTemplate);
			break;
		default:
			//Unsupported template
		}
		 		
		return resolvedString;
		
	}

}
