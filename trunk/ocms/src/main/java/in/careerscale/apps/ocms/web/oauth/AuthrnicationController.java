package in.careerscale.apps.ocms.web.oauth;

import in.careerscale.apps.ocms.web.registration.model.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthrnicationController
{
	private static AuthenticationManager am = new SampleAuthenticationManager();
	
	public boolean authenticate(String emailAddress){
		System.out.println("Inside authenticate:");
		
		 try {
		        Authentication request = new UsernamePasswordAuthenticationToken(emailAddress, "password");
		        Authentication result = am.authenticate(request);
		        SecurityContextHolder.getContext().setAuthentication(result);
		        return true;
		      } catch(AuthenticationException e) {
		        System.out.println("Authentication failed: " + e.getMessage());
		      }catch (Exception e) {
		    	  System.out.println("Authentication failed general Exception: " + e.getMessage());
			}
		 
		 System.out.println("Successfully authenticated. Security context contains: " +
	              SecurityContextHolder.getContext().getAuthentication());
		return false;
	}

	public boolean authenticate(User user)
	{
		
		System.out.println("Inside authenticate:");
		
		 try {
		        //Authentication request = new UsernamePasswordAuthenticationToken(user.getEmailId(), "");
		        Authentication request = new UsernamePasswordAuthenticationToken(user, "password");
			       
		        //Authentication result = am.authenticate(request);
		        //SecurityContextHolder.getContext().setAuthentication(result);
		        SecurityContextHolder.getContext().setAuthentication(request);
		        return true;
		      } catch(AuthenticationException e) {
		        System.out.println("Authentication failed: " + e.getMessage());
		      }catch (Exception e) {
		    	  System.out.println("Authentication failed general Exception: " + e.getMessage());
			}
		 
		 System.out.println("Successfully authenticated. Security context contains: " +
	              SecurityContextHolder.getContext().getAuthentication());
		return false;
	}
}
