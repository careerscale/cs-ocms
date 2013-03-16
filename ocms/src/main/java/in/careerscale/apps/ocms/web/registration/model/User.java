package in.careerscale.apps.ocms.web.registration.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	
	/*
	 * Default constructor. We need this as spring mvc initializes this form bean with default constructor.
	 *
	 */
	public User(){
		
	}
	
	public String getUserName(){
		return emailId;
	}
	
	
	public String getUsername() {
		return emailId;
	}

	public void setUsername(String username) {
		this.emailId = username;
	}

	public User(String emailId, String firstName, String lastName, Date dateOfBirth){
		this.emailId =emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		
	}
	
	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	@DateTimeFormat(iso=ISO.DATE) 
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	@Override
	public String toString() {
		return new ToStringBuilder(this).
			      // append("id", id).
			       append("firstName", firstName).
			       append("lastName", lastName).
			       append("dateOfBirth", dateOfBirth).
			       append("emailId", emailId).
			       toString();
	}
	
	
	 @InitBinder
	   public void initBinder(WebDataBinder binder) {
	         binder.initDirectFieldAccess();
	         /* register appropriate date editor */
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
	

}
