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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@SuppressWarnings("serial")
public class User implements Serializable
{

	private Integer id;
	private String emailId;
	private String previousPassword;
	private String password;
	private String firstName;
	private String lastName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;

	// to list the case types of the user's interests
	private List<Integer> caseTypes;
	private List<Integer> helpTypes;

	private List<MasterType> caseMasterTypes;
	private List<MasterType> helpMasterTypes;

	private SocialNetwork network;

	private String socialNetworkId;
	private String userAccessToken;
	private String additionalEmailId;
	private String bloodGroup;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date anniversary;
	private boolean monthlyUpdates;
	private boolean specialUpdates;
	private boolean regularUpdates;
	private String mobileNumber1;
	private String mobileNumber2;
	private String landlineNumber;
	private String addressLine1;
	private String addressLine2;
	private String zipcode;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;

	private List<MasterType> countryMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> stateMasterTypes = new ArrayList<MasterType>();
	private List<MasterType> cityMasterTypes = new ArrayList<MasterType>();

	/*
	 * Default constructor. We need this as spring mvc initializes this form bean with default constructor.
	 */
	public User()
	{

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return emailId;
	}

	public String getUsername()
	{
		return emailId;
	}

	public void setUsername(String username)
	{
		this.emailId = username;
	}

	public User(String emailId, String firstName, String lastName, Date dateOfBirth)
	{
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;

	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = trim(emailId);
	}

	public String getPreviousPassword()
	{
		return previousPassword;
	}

	public void setPreviousPassword(String previousPassword)
	{
		this.previousPassword = previousPassword;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = trim(firstName);
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = trim(lastName);
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public List<Integer> getCaseTypes()
	{
		return caseTypes;
	}

	public void setCaseTypes(List<Integer> caseTypes)
	{
		this.caseTypes = caseTypes;
	}

	public List<Integer> getHelpTypes()
	{
		return helpTypes;
	}

	public void setHelpTypes(List<Integer> helpTypes)
	{
		this.helpTypes = helpTypes;
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

	public SocialNetwork getNetwork()
	{
		return network;
	}

	public void setNetwork(SocialNetwork network)
	{
		this.network = network;

	}

	public String getSocialNetworkId()
	{
		return socialNetworkId;
	}

	public void setSocialNetworkId(String socialNetworkId)
	{
		this.socialNetworkId = trim(socialNetworkId);
	}

	public String getUserAccessToken()
	{
		return userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken)
	{
		this.userAccessToken = userAccessToken;
	}

	public String getAdditionalEmailId()
	{
		return additionalEmailId;
	}

	public void setAdditionalEmailId(String additionalEmailId)
	{
		this.additionalEmailId = additionalEmailId;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public Date getAnniversary()
	{
		return anniversary;
	}


	public void setAnniversary(Date anniversary)
	{
		this.anniversary = anniversary;
	}

	public boolean isMonthlyUpdates()
	{
		return monthlyUpdates;
	}

	public void setMonthlyUpdates(boolean monthlyUpdates)
	{
		this.monthlyUpdates = monthlyUpdates;
	}

	public boolean isSpecialUpdates()
	{
		return specialUpdates;
	}

	public void setSpecialUpdates(boolean specialUpdates)
	{
		this.specialUpdates = specialUpdates;
	}

	public boolean isRegularUpdates()
	{
		return regularUpdates;
	}

	public void setRegularUpdates(boolean regularUpdates)
	{
		this.regularUpdates = regularUpdates;
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

	public String getMobileNumber1()
	{
		return mobileNumber1;
	}

	public void setMobileNumber1(String mobileNumber1)
	{
		this.mobileNumber1 = mobileNumber1;
	}

	public String getMobileNumber2()
	{
		return mobileNumber2;
	}

	public void setMobileNumber2(String mobileNumber2)
	{
		this.mobileNumber2 = mobileNumber2;
	}

	public String getLandlineNumber()
	{
		return landlineNumber;
	}

	public void setLandlineNumber(String landlineNumber)
	{
		this.landlineNumber = landlineNumber;
	}

	public List<MasterType> getCountryMasterTypes()
	{
		return countryMasterTypes;
	}

	public void setCountryMasterTypes(List<MasterType> countryMasterTypes)
	{
		this.countryMasterTypes = countryMasterTypes;
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

	@Override
	public String toString()
	{
		return new ToStringBuilder(this)
				.
				// append("id", id).
				append("firstName", firstName).append("lastName", lastName).append("dateOfBirth", dateOfBirth)
				.append("emailId", emailId).append("userAccessToken", userAccessToken).toString();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.initDirectFieldAccess();
		/* register appropriate date editor */
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	private String trim(String input)
	{
		return input != null ? input.replaceAll("^\"|\"$", "") : input;
	}

}
