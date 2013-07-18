package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.mail.EmailTemplates;
import in.careerscale.apps.ocms.mail.Template;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractService
{
	@Autowired
	protected MasterDataRepository masterDataRepository;

	@Autowired
	protected EmailSender emailService;

	@Value("${hostName}")
	protected String hostName;
	@Value("${adminUser}")
	protected String adminUser;
	@Value("${productName}")
	protected String productName;

	// check if we still need this setter method?
	public void setEmailService(EmailSender emailService)
	{
		this.emailService = emailService;
	}

	public LoginMaster getLoggedInUser()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		ExtendedUser user = (ExtendedUser) authentication.getPrincipal();

		return (LoginMaster) masterDataRepository.getById(LoginMaster.class, user.getId());
	}

	public Integer getLoggedInUserId()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		ExtendedUser user = (ExtendedUser) authentication.getPrincipal();

		return user.getId();
	}

	private void updateBasicEmailSettings(Map<String, String> settings)
	{

		settings.put(EmailTemplates.adminUser, adminUser);
		settings.put(EmailTemplates.productName, productName);
		settings.put(EmailTemplates.hostName, hostName);

	}

	public void sendEmail(String emailId, Template template, Map<String, String> parameters)
	{
		String subject = null;
		String emailText = null;
		String firstName = parameters.get(EmailTemplates.firstName);

		updateBasicEmailSettings(parameters);
		switch (template) {
		case FundReceiptGenerated:
			emailText = EmailTemplates.getEmailMessage(Template.FundReceiptGenerated, parameters);
			subject = "Thank you for your contribution ::" + firstName;
			break;
		case NewCaseForApproval:
			emailText = EmailTemplates.getEmailMessage(Template.NewCaseForApproval, parameters);
			subject = "Dear " + firstName + ", New Case for Approval ";
			break;

		case CaseRegistered:
			emailText = EmailTemplates.getEmailMessage(Template.CaseRegistered, parameters);
			subject = "Dear " + firstName + ", A new case is registered at OCMS";
			break;
		default:

		}

		emailService.sendMailWithSSL(subject, emailText, emailId);
	}
}
