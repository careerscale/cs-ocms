package in.careerscale.apps.ocms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import javax.persistence.PersistenceException;

import org.scribe.builder.api.TwitterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("oauthService")
public class OAuthService {

	@Autowired
	private MasterDataRepository repository;

	@Autowired
	private EmailSender emailService;

	
	public void updateUserInformation(){
		
		TwitterApi api;
		
		
	}



}
