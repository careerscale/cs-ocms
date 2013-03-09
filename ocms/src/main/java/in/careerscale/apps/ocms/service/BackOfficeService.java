package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.BackOfficeRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("backOfficeService")
public class BackOfficeService {


	@Autowired
	private BackOfficeRepository backofficeRepository;

	@Autowired
	private EmailSender emailService;

	public void addCase(BOBean caseType) {
		backofficeRepository.save(new CaseType(caseType.getName(),caseType.getDescription()));
		
	}

}
