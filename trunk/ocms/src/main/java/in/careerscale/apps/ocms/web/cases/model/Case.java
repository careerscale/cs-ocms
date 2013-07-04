package in.careerscale.apps.ocms.web.cases.model;

import in.careerscale.apps.ocms.service.model.MasterType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
public class Case implements Serializable
{
	private int id; // used only for doc upload.
	private String personName;
	private String emailId;
	private String caseDescription;
	private String caseSource;
	private Date dateOfBirth;
	private Date createdDate;
	private Date UpdatedDate;
	private String contact1;
	private String contact2;
	private String addressLine1;
	private String addressLine2;
	private String zipcode;

	private Integer countryId;
	private Integer stateId;
	private Integer cityId;

	private Integer caseType;
	private Integer helpType;
	private Integer caseStatus;
	
	private String caseTypeString;
	private String caseStatusString;
	private String helpCategoryString;
	private String createdBy;
	private String updatedBy;
	
	
	public String getCaseTypeString()
	{
		return caseTypeString;
	}


	public void setCaseTypeString(String caseTypeString)
	{
		this.caseTypeString = caseTypeString;
	}


	public String getCreatedBy()
	{
		return createdBy;
	}


	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}


	public String getUpdatedBy()
	{
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}


	public String getHelpCategoryString()
	{
		return helpCategoryString;
	}


	public void setHelpCategoryString(String helpCategoryString)
	{
		this.helpCategoryString = helpCategoryString;
	}


	public String getCaseStatusString()
	{
		return caseStatusString;
	}


	public void setCaseStatusString(String caseStatusString)
	{
		this.caseStatusString = caseStatusString;
	}

	private List<MasterType> caseMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> helpMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> caseStatusTypes = new ArrayList<MasterType>();
	private List<MasterType> countryMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> stateMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> cityMasterTypes = new ArrayList<MasterType>();
	public Case()
	{

	}

	
	public Case(int id,String personName, String caseDescription, String caseSource, Date dateOfBirth, Date createdDate,
			Date UpdatedDate, String contact1, String contact2, String caseStatusString, String helpCategoryString, String caseTypeString, String createdBy, String updatedBy)
	{
		this.id = id;
		this.personName = personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate = createdDate;
		this.UpdatedDate = UpdatedDate;
		this.contact1 = contact1;
		this.contact2 = contact2;
		this.caseStatusString = caseStatusString;
		this.helpCategoryString = helpCategoryString;
		this.caseTypeString = caseTypeString;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;

	}
	
	
	public Case(String personName, String caseDescription, String caseSource, Date dateOfBirth, Date createdDate,
			Date UpdatedDate, String contact1, String contact2)
	{
		this.personName = personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate = createdDate;
		this.UpdatedDate = UpdatedDate;
		this.contact1 = contact1;
		this.contact2 = contact2;

	}

	public Case(String personName, String caseDescription, String caseSource, Date dateOfBirth, Date createdDate,
			String contact1)
	{
		this.personName = personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate = createdDate;
		this.contact1 = contact1;

	}

	public Case(String personName, String emailId, String caseDescription, String caseSource, Date dateOfBirth,
			Date createdDate, String contact1)
	{
		this.emailId = emailId;
		this.personName = personName;
		this.caseDescription = caseDescription;
		this.caseSource = caseSource;
		this.dateOfBirth = dateOfBirth;
		this.createdDate = createdDate;
		this.contact1 = contact1;

	}

	public String getPersonName()
	{
		return personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = trim(emailId);
	}

	public String getCaseDescription()
	{
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription)
	{
		this.caseDescription = caseDescription;
	}

	public String getCaseSource()
	{
		return caseSource;
	}

	public void setCaseSource(String caseSource)
	{
		this.caseSource = caseSource;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	@DateTimeFormat(iso = ISO.DATE)
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	@DateTimeFormat(iso = ISO.DATE)
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate()
	{
		return UpdatedDate;
	}

	@DateTimeFormat(iso = ISO.DATE)
	public void setUpdatedDate(Date updatedDate)
	{
		UpdatedDate = updatedDate;
	}

	public String getContact1()
	{
		return contact1;
	}

	public void setContact1(String contact1)
	{
		this.contact1 = contact1;
	}

	public String getContact2()
	{
		return contact2;
	}

	public void setContact2(String contact2)
	{
		this.contact2 = contact2;
	}

	public Integer getCaseType()
	{
		return caseType;
	}

	public void setCaseType(Integer caseType)
	{
		this.caseType = caseType;
	}

	public Integer getHelpType()
	{
		return helpType;
	}

	public void setHelpType(Integer helpType)
	{
		this.helpType = helpType;
	}

	public Integer getCaseStatus()
	{
		return caseStatus;
	}

	public void setCaseStatus(Integer caseStatus)
	{
		this.caseStatus = caseStatus;
	}

	public List<MasterType> getCaseMasterTypes()
	{
		return caseMasterTypes;
	}

	public void setCaseMasterTypes(List<MasterType> caseMasterTypes)
	{
		this.caseMasterTypes = caseMasterTypes;
	}

	public List<MasterType> getHelpMasterTypes()
	{
		return helpMasterTypes;
	}

	public void setHelpMasterTypes(List<MasterType> helpMasterTypes)
	{
		this.helpMasterTypes = helpMasterTypes;
	}

	public List<MasterType> getCaseStatusTypes()
	{
		return caseStatusTypes;
	}

	public void setCaseStatusTypes(List<MasterType> caseStatusTypes)
	{
		this.caseStatusTypes = caseStatusTypes;
	}

	public void setCountryMasterTypes(List<MasterType> countries)
	{
		this.countryMasterTypes = countries;

	}

	public Integer getCountryId()
	{
		return countryId;
	}

	public void setCountryId(Integer countryId)
	{
		this.countryId = countryId;
	}

	public Integer getStateId()
	{
		return stateId;
	}

	public void setStateId(Integer stateId)
	{
		this.stateId = stateId;
	}

	public Integer getCityId()
	{
		return cityId;
	}

	public void setCityId(Integer cityId)
	{
		this.cityId = cityId;
	}

	public List<MasterType> getCountryMasterTypes()
	{
		return countryMasterTypes;
	}

	public List<MasterType> getStateMasterTypes()
	{
		return stateMasterTypes;
	}

	public void setStateMasterTypes(List<MasterType> stateMasterTypes)
	{
		this.stateMasterTypes = stateMasterTypes;
	}

	public List<MasterType> getCityMasterTypes()
	{
		return cityMasterTypes;
	}

	public void setCityMasterTypes(List<MasterType> cityMasterTypes)
	{
		this.cityMasterTypes = cityMasterTypes;
	}

	public String getAddressLine1()
	{
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2()
	{
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String toString()
	{
		return new ToStringBuilder(this)
				.
				// append("id", id).
				append("personName", personName).append("caseDescription", caseDescription)
				.append("dateOfBirth", dateOfBirth).append("caseSource", caseSource).toString();
	}

	@SuppressWarnings("unused")
	private String trim(String input)
	{
		return input != null ? input.replaceAll("^\"|\"$", "") : input;
	}

}
