package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.UserNetwork;
import in.careerscale.apps.ocms.dao.model.UserRole;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.mail.EmailTemplates;
import in.careerscale.apps.ocms.mail.Template;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

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

@Service("socialService")
public class SocialService implements UserDetailsService {

	Log log = LogFactory.getLog(SocialService.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSender emailService;

	@Autowired
	private MasterDataRepository masterDataRepository;

	@PostConstruct
	protected void initialize() {
		// userRepository.save(new User("user", "demo", "ROLE_USER"));
		// userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Map<String, String> map = new HashMap<String, String>();

		for ( String actualElement : username.split(",") ) {
			if(actualElement.split("=")[0].equalsIgnoreCase("emailId")){
				map.put(actualElement.split("=")[0], actualElement.split("=")[1]);
				username = actualElement.split("=")[1];
			}
		}

		System.out.println(map);
		username = username.substring(0, username.length()-1);
		LoginMaster user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		// proper
		// rle
		Iterator<UserRole> it = user.getUserRoles().iterator();
		UserRole userRole = null;
		Set<GrantedAuthority> roleSet = new HashSet<GrantedAuthority>();
		while (it.hasNext()) {
			userRole = it.next();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
					userRole.getRoleMaster().getRoleName());
			roleSet.add(grantedAuthority);
		}

		user.setPassword("password");
		return new org.springframework.security.core.userdetails.User(
				user.getFirstName(), user.getPassword(), roleSet);

	}

	
	
}
