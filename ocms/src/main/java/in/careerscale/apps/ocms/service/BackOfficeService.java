package in.careerscale.apps.ocms.service;

import javax.persistence.PersistenceException;

import in.careerscale.apps.ocms.dao.BackOfficeRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
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

	public void addCaseType(BOBean caseType) throws ApplicationException {

		try {
			backofficeRepository.save(new CaseType(caseType.getName(), caseType
					.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	 public void addHelpCategoryType(BOBean helpCategoryType) throws ApplicationException {

	        try {
	            backofficeRepository.save(new HelpCategoryType(helpCategoryType.getName(), helpCategoryType.getDescription()));
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
	    }



}
