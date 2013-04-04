package in.careerscale.apps.ocms.web.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

//This class is only added for reference, neede when we use custom authenication
public class SampleAuthenticationManager implements AuthenticationManager
{

	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	  static {
	    AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
	    //GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRoleMaster().getRoleName());
		
	  }
	  
	  public Authentication authenticate(Authentication auth) throws AuthenticationException {
	    //if (auth.getName().equals(auth.getCredentials())) {
		  if (auth.getName() != null) {
	      return new UsernamePasswordAuthenticationToken(auth.getName(),
	        auth.getCredentials(), AUTHORITIES);
	      
	      //new org.springframework.security.core.userdetails.User(auth.getEmailId(), user.getPassword(), roleSet)
	      }
	      throw new BadCredentialsException("Bad Credentials");
	  }
}
