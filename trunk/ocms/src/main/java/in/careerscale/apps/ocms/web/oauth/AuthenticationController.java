package in.careerscale.apps.ocms.web.oauth;

import in.careerscale.apps.ocms.web.registration.model.User;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationController
{
	// Default role for the social users, this is temporary role to make the authentication pass iwthout hurdles.
	private static final String REGISTERED = "REGISTERED";
	Log log = LogFactory.getLog(AuthenticationController.class);
	private static AuthenticationManager am = new SampleAuthenticationManager();

	public boolean authenticate(String emailAddress)
	{
		log.info("Inside authenticate:");

		try
		{
			Authentication request = new UsernamePasswordAuthenticationToken(emailAddress, "password");
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			return true;
		}
		catch (AuthenticationException e)
		{
			System.out.println("Authentication failed: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Authentication failed general Exception: " + e.getMessage());
		}

		log.info("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
		return false;
	}

	public boolean authenticate(User user)
	{

		log.info("Inside authenticate:");

		try
		{
			// Authentication request = new UsernamePasswordAuthenticationToken(user.getEmailId(), "");

			Set<GrantedAuthority> roleSet = new HashSet<GrantedAuthority>();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(REGISTERED);
			roleSet.add(grantedAuthority);
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmailId(),
					"password", roleSet);

			Authentication request = new UsernamePasswordAuthenticationToken(userDetails, "password");

			SecurityContextHolder.getContext().setAuthentication(request);
			return true;
		}
		catch (AuthenticationException e)
		{
			System.out.println("Authentication failed: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Authentication failed general Exception: " + e.getMessage());
		}

		log.info("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
		return false;
	}
}
