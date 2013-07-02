package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.Address;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.City;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.UserNetwork;
import in.careerscale.apps.ocms.dao.model.UserProfile;
import in.careerscale.apps.ocms.dao.model.UserRole;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.mail.EmailTemplates;
import in.careerscale.apps.ocms.mail.Template;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.service.model.RegistrationResult;
import in.careerscale.apps.ocms.web.registration.model.User;

import java.util.ArrayList;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService
{

	Log log = LogFactory.getLog(UserService.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailSender emailService;

	@Autowired
	private MasterDataRepository masterDataRepository;

	private static final String DUMMY_PASSWORD = "password";

	public void setEmailService(EmailSender emailService)
	{
		this.emailService = emailService;
	}

	@PostConstruct
	protected void initialize()
	{
		// userRepository.save(new User("user", "demo", "ROLE_USER"));
		// userRepository.save(new User("admin", "admin", "ROLE_ADMIN"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		LoginMaster user = userRepository.findByUsername(username);
		if (user == null)
		{
			throw new UsernameNotFoundException("user not found");
		}
		// proper
		// rle
		Iterator<UserRole> it = user.getUserRoles().iterator();
		UserRole userRole = null;
		Set<GrantedAuthority> roleSet = new HashSet<GrantedAuthority>();
		while (it.hasNext())
		{
			userRole = it.next();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRoleMaster().getRoleName());
			roleSet.add(grantedAuthority);
		}

		/**
		 * Password will be null for social users. so setting this value to null, since spring security was throwing
		 * error. not sure if this fixes the actual issue or not.
		 */
		if (user.getPassword() == null)
		{
			user.setPassword(DUMMY_PASSWORD);
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmailId(),
				user.getPassword(), roleSet);
		// return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
		// roleSet);
		return new ExtendedUser(userDetails, user.getFirstName(), roleSet.toString(), user.getId());

	}

	public RegistrationResult registerUser(User user) throws ApplicationException
	{
		// for bravity no validations done at service layer, we need to handle
		// and also introduce valid exception handling to manage error
		// situations
		LoginMaster dbUser;
		RegistrationResult result = RegistrationResult.Error;
		try
		{

			if (user.getNetwork() != null)
			{
				// let us check if this guy is already having an account with
				// us, since email id unique we can count on it.
				// if the email id is used for another OCMS account then, we may
				// need to just link him to that account.
				dbUser = userRepository.findByUsername(user.getEmailId());
				if (dbUser != null)
				{

					// dbUser loginType shouldn't be null.
					if (dbUser.getLoginType() == LoginMaster.SOCIAL_REGISTERED)
					{
						// Good the user exists already, so let us skip further registration
						// check if the association exists already. if yes, skip, it might be login action from user.
						return RegistrationResult.SocialRegisteredAlready;
					}

				}
				dbUser = new LoginMaster(user.getEmailId(), user.getPassword(), user.getFirstName(),
						user.getLastName(), user.getDateOfBirth(), LoginMaster.SOCIAL_REGISTERED);
				// TODO set DB flag as well.
				userRepository.registerUser(dbUser);

				in.careerscale.apps.ocms.dao.model.SocialNetwork network = userRepository.getSocialNetwork(user
						.getNetwork().getId());
				UserNetwork userNetwork = new UserNetwork(user.getSocialNetworkId(), dbUser, network,
						user.getUserAccessToken());
				userRepository.save(userNetwork);

				result = RegistrationResult.SocialRegistered;

			}
			else
			{
				// regular form registration, let us go ahead here
				dbUser = new LoginMaster(user.getEmailId(), user.getPassword(), user.getFirstName(),
						user.getLastName(), user.getDateOfBirth(), LoginMaster.LOCAL_REGISTERED);
				userRepository.registerUser(dbUser);
				List<CaseType> userCaseTypes = userRepository.getCaseTypes(user.getCaseTypes());

				dbUser.setCaseTypes(new HashSet<CaseType>(userCaseTypes));

				List<HelpCategoryType> userHelpTypes = userRepository.gethelpTypes(user.getHelpTypes());

				dbUser.setHelpCategoryTypes(new HashSet<HelpCategoryType>(userHelpTypes));
				userRepository.update(dbUser);
				result = RegistrationResult.Registered;

				try
				{
					// Resolve variables
					Map<String, String> placeHolderValues = new HashMap<String, String>();
					placeHolderValues.put(EmailTemplates.firstName, user.getFirstName());
					placeHolderValues.put(EmailTemplates.userName, user.getEmailId());
					placeHolderValues.put(EmailTemplates.password, user.getPassword());
					String emailText = EmailTemplates.getEmailMessage(Template.Registration, placeHolderValues);
					emailService.sendMailWithSSL("OCMS Registration Successful ::" + dbUser.getFirstName(), emailText,
							user.getEmailId());
				}
				catch (Exception mailFailure)
				{
					result = RegistrationResult.Error;
					log.error("Unable to send mail", mailFailure);
				}

			}

		}
		catch (PersistenceException pe)
		{
			result = RegistrationResult.Error;
			throw new ApplicationException(pe.getMessage());

		}

		if (result == RegistrationResult.SocialRegistered)
		{
			UserProfile profile = new UserProfile();
			profile.setLoginMaster(dbUser);
			userRepository.save(profile);
		}
		return result;

	}

	public User findByUsername(String username)
	{

		User user = null;
		LoginMaster loginMaster = userRepository.findByUsername(username);

		if (loginMaster != null)
		{
			user = new User(loginMaster.getEmailId(), loginMaster.getFirstName(), loginMaster.getLastName(),
					loginMaster.getDateOfBirth());

		}

		return user;

	}

	public void forgotPassword(User user)
	{

		LoginMaster dbUser = userRepository.findByUsername(user.getEmailId());
		if (dbUser == null)
		{
			new ApplicationException("No user account found with the given email id. Please try again");
		}

		try
		{
			// Resolve variables
			Map<String, String> placeHolderValues = new HashMap<String, String>();
			placeHolderValues.put(EmailTemplates.firstName, dbUser.getFirstName());
			placeHolderValues.put(EmailTemplates.userName, dbUser.getEmailId());
			placeHolderValues.put(EmailTemplates.password, dbUser.getPassword());
			String emailText = EmailTemplates.getEmailMessage(Template.ForgotPassword, placeHolderValues);
			emailService
					.sendMailWithSSL(dbUser.getFirstName() + " Your OCMS Password ", emailText, dbUser.getEmailId());
		}
		catch (Exception mailFailure)
		{
			log.error("Unable to send mail", mailFailure);
			new ApplicationException("Unable to send email. Please try again later.");
		}

	}

	public void getUserProfile(User user)
	{
		LoginMaster loginMaster = getLoggedInUser();
		if (loginMaster == null)
		{
			new ApplicationException("No user account found with the given email id. Please try again");
		}
		user.setEmailId(loginMaster.getEmailId());
		user.setFirstName(loginMaster.getFirstName());
		user.setLastName(loginMaster.getLastName());
		user.setDateOfBirth(loginMaster.getDateOfBirth());

		UserProfile profile = userRepository.getUserProfile(loginMaster.getId());
		if (profile != null)
		{
			user.setAdditionalEmailId(profile.getOtherEmailId());
			user.setBloodGroup(profile.getBloodGroup());
			user.setLandlineNumber(profile.getLandlineNumber());
			user.setMobileNumber1(profile.getMobileNumber1());
			user.setMobileNumber2(profile.getMobileNumber2());
			user.setAnniversary(profile.getAnniversary());
			Address address = profile.getAddress();
			user.setAddressLine1(address.getAddressLine1());
			user.setAddressLine2(address.getAddressLine2());
			user.setZipcode(address.getZipCode());
			user.setSpecialUpdates(profile.isSpecialUpdates());
			user.setRegularUpdates(profile.isRegularUpdates());
			user.setMonthlyUpdates(profile.isMonthlyUpdates());

		}
		
		Set<CaseType> caseTypes = loginMaster.getCaseTypes();
		List<Integer> caseTypeIds= new ArrayList<Integer>();
		for (CaseType caseType : caseTypes)
		{
			caseTypeIds.add(caseType.getId());
		}
		Set<HelpCategoryType> helpTypes = loginMaster.getHelpCategoryTypes();
		List<Integer> helpTypeIds = new ArrayList<Integer>();
		for (HelpCategoryType helpCategoryType : helpTypes)
		{
			helpTypeIds.add(helpCategoryType.getId());
		}
		
		user.setCaseTypes(caseTypeIds);
		user.setHelpTypes(helpTypeIds);

	}

	/**
	 * Update user profile to database. If profile doesn't exist, it will be created.
	 * 
	 * @param user
	 *            The User object containing user profile.
	 * @throws ApplicationException
	 */
	public void updateUserProfile(User user) throws ApplicationException
	{
		try
		{
			LoginMaster loggedInUser = getLoggedInUser();
			// Let us udpate the login information first.
			if (null != user.getFirstName())
				loggedInUser.setFirstName(user.getFirstName());
			if (null != user.getLastName())
				loggedInUser.setLastName(user.getLastName());
			if (null != user.getDateOfBirth())
				loggedInUser.setDateOfBirth(user.getDateOfBirth());

			// Let us update user prefered case types and help types.
			List<CaseType> userCaseTypes = userRepository.getCaseTypes(user.getCaseTypes());
			loggedInUser.setCaseTypes(new HashSet<CaseType>(userCaseTypes));
			List<HelpCategoryType> userHelpTypes = userRepository.gethelpTypes(user.getHelpTypes());
			loggedInUser.setHelpCategoryTypes(new HashSet<HelpCategoryType>(userHelpTypes));

			userRepository.update(loggedInUser);



			UserProfile userProfile = userRepository.getUserProfile(loggedInUser.getId());
			if (null == userProfile)
			{
				userProfile = new UserProfile();
			}

			// we need to udpate the profile
			if (null != user.getAnniversary())
				userProfile.setAnniversary(user.getAnniversary());
			if (null != user.getBloodGroup())
				userProfile.setBloodGroup(user.getBloodGroup());
			if (null != user.getLandlineNumber())
				userProfile.setLandlineNumber(user.getLandlineNumber());

			userProfile.setLoginMaster(loggedInUser);

			if (null != user.getMobileNumber1())
				userProfile.setMobileNumber1(user.getMobileNumber1());

			if (null != user.getMobileNumber2())
				userProfile.setMobileNumber2(user.getMobileNumber2());

			userProfile.setMonthlyUpdates(user.isMonthlyUpdates());
			userProfile.setRegularUpdates(user.isRegularUpdates());
			userProfile.setSpecialUpdates(user.isSpecialUpdates());


			if (null != user.getAdditionalEmailId())
				userProfile.setOtherEmailId(user.getAdditionalEmailId());


			Address address = userProfile.getAddress();

			if (null != address)
			{
				if (null != user.getAddressLine1())
					address.setAddressLine1(user.getAddressLine1());
				if (null != user.getAddressLine2())
				{
					address.setAddressLine2(user.getAddressLine2());
				}
				if (null != user.getZipcode())
				{
					address.setZipCode(user.getZipcode());

				}
				if (null != user.getCityId())
				{
					address.setCity((City) masterDataRepository.getById(City.class, user.getCityId()));
				}

				userRepository.update(address);

			}
			else
			{
				address = new Address(user.getAddressLine1(), user.getAddressLine2(), user.getZipcode());
				if (null != user.getCityId())
				{
					address.setCity((City) masterDataRepository.getById(City.class, user.getCityId()));
				}
				userRepository.save(address);
			}

			userProfile.setAddress(address);

			userRepository.saveOrUpdate(userProfile);

		}
		catch (Exception e)
		{
			throw new ApplicationException("Error while updating user profile");
		}

	}

	private LoginMaster getLoggedInUser()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		ExtendedUser user = (ExtendedUser) authentication.getPrincipal();

		return (LoginMaster) masterDataRepository.getById(LoginMaster.class, user.getId());
	}
}
