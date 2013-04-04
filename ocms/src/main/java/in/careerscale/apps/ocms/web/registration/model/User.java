package in.careerscale.apps.ocms.web.registration.model;




import in.careerscale.apps.ocms.service.model.MasterType;
import in.careerscale.apps.ocms.service.model.SocialNetwork;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	//to list the case types of the user's interests
	private List<Integer> caseTypes;
	private List<Integer> helpTypes;
	
	private List<MasterType> caseMasterTypes;
	private List<MasterType> helpMasterTypes;
	
	private SocialNetwork network;
	
	private String socialNetworkId;
	
	
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
		this.emailId = trim(emailId);
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
		this.firstName = trim(firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = trim(lastName);
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	@DateTimeFormat(iso=ISO.DATE) 
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Integer> getCaseTypes() {
		return caseTypes;
	}

	public void setCaseTypes(List<Integer> caseTypes) {
		this.caseTypes = caseTypes;
	}

	public List<Integer> getHelpTypes() {
		return helpTypes;
	}

	public void setHelpTypes(List<Integer> helpTypes) {
		this.helpTypes = helpTypes;
	}

	
	public List<MasterType> getCaseMasterTypes() {
		return caseMasterTypes;
	}

	public void setCaseMasterTypes(List<MasterType> caseMasterTypes) {
		this.caseMasterTypes = caseMasterTypes;
	}

	public List<MasterType> getHelpMasterTypes() {
		return helpMasterTypes;
	}

	public void setHelpMasterTypes(List<MasterType> helpMasterTypes) {
		this.helpMasterTypes = helpMasterTypes;
	}
	
	

	public SocialNetwork getNetwork() {
		return network;
	}

	public void setNetwork(SocialNetwork network) {
		this.network = network;
		
	}

	public String getSocialNetworkId() {
		return trim(socialNetworkId);
	}

	public void setSocialNetworkId(String socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
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
	 
	 
	 private String trim(String input){		
		 return input != null ?input.replaceAll("^\"|\"$", ""): input;
	 }
	

}
