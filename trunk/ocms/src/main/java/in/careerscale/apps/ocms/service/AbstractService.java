package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.mail.EmailTemplates;

import java.util.HashMap;
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

	protected Map<String, String> getBasicEmailSettings()
	{
		Map<String, String> settings = new HashMap<String, String>();
		settings.put(EmailTemplates.adminUser, adminUser);
		settings.put(EmailTemplates.productName, productName);
		settings.put(EmailTemplates.hostName, hostName);
		return settings;

	}
}
