package in.careerscale.apps.ocms.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class ExtendedUser implements UserDetails
{

	private UserDetails userDetails;

	private String displayName;
	
	private String role;
	
	private Integer id;

	public Integer getId()
	{
		return id;
	}

	/**
	 * Calls the super class constructor
	 */
	ExtendedUser()
	{
		super();
	}

	/**
	 * @param userDetails userdetails
	 * @param displayName displayName
	 * @param role role
	 * @return Extendeduser user info
	 */
	public ExtendedUser(UserDetails userDetails, String displayName, String role)
	{
		super();
		this.userDetails = userDetails;
		this.displayName = displayName;
		this.role = role;
	}

	
	public ExtendedUser(UserDetails userDetails, String displayName, String role, Integer id)
	{
		super();
		this.userDetails = userDetails;
		this.displayName = displayName;
		this.role = role;
		this.id = id;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return userDetails.getAuthorities();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword()
	{
		return userDetails.getPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	public String getUsername()
	{
		return userDetails.getUsername();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired()
	{
		return userDetails.isAccountNonExpired();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked()
	{
		return userDetails.isAccountNonLocked();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired()
	{
		return userDetails.isCredentialsNonExpired();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled()
	{
		return userDetails.isEnabled();
	}

	/**
	 * @return String
	 */
	public String getDisplayName()
	{
		return displayName;
	}


	/**
	 * @return String
	 */
	public String getRole()
	{
		return role;
	}

	
}
