package in.careerscale.apps.ocms.mail;

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
	
	private static String fundReceiptGeneratedTemplate = "Dear ${firstName}, \n your contribution of ${amount} towards '${caseDescription}', is confirmed."
			+ " \n\n You can download the digitally signed receipt at:${hostName}/user/downloads/receipt/${id}\n"
			+ "\n You can also download all the receipts  at  {hostName}/downloads"
			+ "\n Thank you for your contribution, together we can bring lot of positive change in the needy's life." +

			"\nRegards, \n" + "${adminUser}\n" + "${productName}\n" + "${hostName}";


	public static final String firstName ="firstName";
	public static final String userName="username";
	public static final String password ="password";
	public static final String activationCode ="activationCode";
	public static final String caseDescription = "caseDescription";
	public static final String fundReceiptId = "id";
	public static final String adminUser = "adminUser";
	public static final String productName = "productName";
	public static final String hostName = "hostName";
	public static final String amount = "amount";
	
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
		case FundReceiptGenerated:
			resolvedString = sub.replace(fundReceiptGeneratedTemplate);
		default:
			//Unsupported template
		}
		 		
		return resolvedString;
		
	}

}
