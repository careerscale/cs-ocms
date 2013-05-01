package in.careerscale.apps.ocms.service;



import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.NotificationRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.*;
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
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private NotificationRepository notificationRepository;

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
		    
			SecurityContext context =SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			ExtendedUser user = (ExtendedUser) authentication.getPrincipal();
			Integer userId = user.getId();
			LoginMaster loggedInUser = (LoginMaster) masterDataRepository.getById(LoginMaster.class, userId);

			caseMaster.setLoginMasterByCreatedBy(loggedInUser);
			caseMaster.setLoginMasterByUpdatedBy(loggedInUser);
		
			caseRepository.registerCase(caseMaster);
	        
            NotificationRecipient recipient = null;
            NotificationStatus status = (NotificationStatus) notificationRepository.getById(NotificationStatus.class, 1);
            NotificationTemplate template = (NotificationTemplate) notificationRepository.getById(NotificationTemplate.class, 1);
            Notification notification = new Notification("this is recipient", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), loggedInUser, loggedInUser, caseMaster, recipient, status, template);
            //	 notification.setCreatedOn();
            notificationRepository.save(notification);
				
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}

	}

	
	}

