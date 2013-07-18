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
			+ "\n You can also download all the receipts  at  ${hostName}/downloads"
			+ "\n Thank you for your contribution, together we can bring lot of positive change in the needy's life." +

			"\nRegards, \n" + "${adminUser}\n" + "${productName}\n" + "${hostName}";
	private static String caseRegisteredTemplate = "Dear ${firstName}, \n A new case has been registered successfully in ${productName}. We thank you for bringing this requirement to our notice."
			+ "How ever, there will be manual verification followed by approval process, before attending to the requirement. You can view the details and status at:\n"
			+ " ${hostName}/cases/${id}\n"
			+ "\n Thank you for your contribution, together we can bring lot of positive change in the needy's life." +

			"\nRegards, \n" + "${adminUser}\n" + "${productName}\n" + "${hostName}";

	private static String newCaseForApprovalTemplate = "Dear ${firstName}, \n A new case has been registered in ${productName}. Since you are an approver for this type of cases, We request you to kindly login to the application and confirm your decision to help the needy faster."
			+ "How ever, there will be manual verification followed by approval process, before attending to the requirement. You can view the details and status at:\n"
			+ "You can view the details and take action at ${hostName}/cases/${id}\n"
			+ "\n Thank you for your support, together we can bring lot of positive change in the needy's life." +

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
	public static final String caseId = "id";
	
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
			break;
		case CaseRegistered:
			resolvedString = sub.replace(caseRegisteredTemplate);
			break;
		case NewCaseForApproval:
			resolvedString = sub.replace(newCaseForApprovalTemplate);
			break;
		default:
			//Unsupported template
		}
		 		
		return resolvedString;
		
	}

}
