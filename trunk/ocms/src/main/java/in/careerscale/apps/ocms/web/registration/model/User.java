package in.careerscale.apps.ocms.web.registration.model;


import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class User implements Serializable {
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	
	
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

}
