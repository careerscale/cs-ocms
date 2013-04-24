package in.careerscale.apps.ocms.service;



import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
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

import com.ibm.icu.util.Calendar;

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
		
		CaseMaster caseMaster;
		try {

			
			caseMaster = new CaseMaster(bean.getCreatedDate(),bean.getUpdatedDate(), bean.getPersonName(),bean.getEmailId(),
						bean.getDateOfBirth(), bean.getCaseDescription(),bean.getContact1(),bean.getContact2(),bean.getCaseSource());				
				//TODO set DB flag as well.
			caseMaster.setCaseStatusMaster((CaseStatusMaster)masterDataRepository.getById(CaseStatusMaster.class, new Integer(2))); 
			//TODO replace with status enum or something like that. Here 2 means pending in db.
			caseMaster.setCaseType((CaseType) masterDataRepository.getCaseType(bean.getCaseTypes().get(0)));
			caseMaster.setHelpCategoryType((HelpCategoryType)masterDataRepository.getHelpCategoryType(bean.getHelpTypes().get(0)));
			caseMaster.setContactNumber1(bean.getContact1());
			caseMaster.setContactNumber2(bean.getContact2());
			caseMaster.setEmailId(bean.getEmailId());
			caseMaster.setCreatedOn(Calendar.getInstance().getTime());
			caseMaster.setUpdatedOn(Calendar.getInstance().getTime());
			//TODO hardcoded for now, pleaes fetch this from session and use accordingly. 
			caseMaster.setLoginMasterByCreatedBy((LoginMaster)masterDataRepository.getById(LoginMaster.class, new Integer(1)));
			caseMaster.setLoginMasterByUpdatedBy((LoginMaster)masterDataRepository.getById(LoginMaster.class, new Integer(1)));
			
			caseRepository.registerCase(caseMaster);
				
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}

	}

	
	}

