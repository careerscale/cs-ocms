package in.careerscale.apps.ocms.service;



import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.UserNetwork;
import in.careerscale.apps.ocms.dao.model.UserRole;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.mail.EmailTemplates;
import in.careerscale.apps.ocms.mail.Template;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("caseService")
public class CaseService {

	Log log = LogFactory.getLog(CaseService.class);
	
	@Autowired
	private CaseRepository caseRepository;
	
	

	@Autowired
	private MasterDataRepository masterDataRepository;

	@PostConstruct
	protected void initialize() {
		// userRepository.save(new User("user", "demo", "ROLE_USER"));
		// userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
	}

	

	public void registerCase(Case bean) throws ApplicationException {
		// for bravity no validations done at service layer, we need to handle
		// and also introduce valid exception handling to manage error
		// situations
		CaseMaster caseMaster;
		try {

			
			caseMaster = new CaseMaster(bean.getCreatedDate(),bean.getUpdatedDate(), bean.getPersonName(),
						bean.getDateOfBirth(), bean.getCaseDescription(),bean.getContact1(),bean.getContact2(),bean.getCaseSource());				
				//TODO set DB flag as well.
			caseRepository.registerCase(caseMaster);
				
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}

	}

	
	}

