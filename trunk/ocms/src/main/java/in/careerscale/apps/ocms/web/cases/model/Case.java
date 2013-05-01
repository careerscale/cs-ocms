package in.careerscale.apps.ocms.web.cases.model;

import in.careerscale.apps.ocms.service.model.MasterType;
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
public class Case  implements Serializable 
{
	private String personName;
	private String emailId;
	private String caseDescription;
	private String  caseSource;
	private Date dateOfBirth;
	private Date createdDate;
	private Date UpdatedDate;
	private String contact1;
	private String contact2;
	
	
	private List<Integer> caseTypes=new ArrayList<Integer>();
	private List<Integer> helpTypes= new ArrayList<Integer>();
	private List<Integer> caseStatus = new ArrayList<Integer>();
	
	private List<MasterType> caseMasterTypes= new ArrayList<MasterType>();
	private List<MasterType> helpMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> caseStatusTypes=new ArrayList<MasterType>();
	
	
	public Case(){
		
	}
	public Case(String personName,String caseDescription,String  caseSource,Date dateOfBirth,Date createdDate,Date UpdatedDate,String contact1,String contact2)
	{
		this.personName =personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate=createdDate;
		this.UpdatedDate=UpdatedDate;
		this.contact1=contact1;
		this.contact2=contact2;
		
	}
	public Case(String personName,String caseDescription,String  caseSource,Date dateOfBirth,Date createdDate,String contact1)
	{
		this.personName =personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate=createdDate;
		this.contact1=contact1;
		
	}
	public Case(String personName,String emailId,String caseDescription,String  caseSource,Date dateOfBirth,Date createdDate,String contact1)
	{
		this.emailId=emailId;
		this.personName =personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate=createdDate;
		this.contact1=contact1;
		
	}
	
	public String getPersonName() {
		return personName;
	}



	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = trim(emailId);
	}


	public String getCaseDescription() {
		return caseDescription;
	}



	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}



	public String getCaseSource() {
		return caseSource;
	}



	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}



	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	@DateTimeFormat(iso=ISO.DATE)
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public Date getCreatedDate() {
		return createdDate;
	}


	@DateTimeFormat(iso=ISO.DATE)
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getUpdatedDate() {
		return UpdatedDate;
	}


	@DateTimeFormat(iso=ISO.DATE)
	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}



	public String getContact1() {
		return contact1;
	}



	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}



	public String getContact2() {
		return contact2;
	}



	public void setContact2(String contact2) {
		this.contact2 = contact2;
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



	public List<Integer> getCaseStatus() {
		return caseStatus;
	}



	public void setCaseStatus(List<Integer> caseStatus) {
		this.caseStatus = caseStatus;
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



	public List<MasterType> getCaseStatusTypes() {
		return caseStatusTypes;
	}



	public void setCaseStatusTypes(List<MasterType> caseStatusTypes) {
		this.caseStatusTypes = caseStatusTypes;
	}
	
	public String toString() {
		return new ToStringBuilder(this).
			      // append("id", id).
			       append("personName",personName).
			       append("caseDescription", caseDescription ).
			       append("dateOfBirth", dateOfBirth).
			       append("caseSource", caseSource).
			       toString();
	}
	
	@SuppressWarnings("unused")
	private String trim(String input){		
		 return input != null ?input.replaceAll("^\"|\"$", ""): input;
	 }
	

}

