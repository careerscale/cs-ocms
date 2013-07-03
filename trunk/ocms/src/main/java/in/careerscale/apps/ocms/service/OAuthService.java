package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.mail.EmailSender;

import org.scribe.builder.api.TwitterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oauthService")
public class OAuthService extends AbstractService
{


	@Autowired
	private EmailSender emailService;

	
	public void updateUserInformation(){
		
		TwitterApi api;
		
		
	}



}
