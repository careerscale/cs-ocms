package in.careerscale.apps.ocms.dao.model;

// Generated Mar 16, 2013 8:20:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CaseMaster generated by hbm2java
 */
@Entity
@Table(name = "case_master", catalog = "ocms")
public class CaseMaster implements java.io.Serializable {

	private Integer id;
	private CaseMaster caseMaster;
	private Address address;
	private LoginMaster loginMasterByCreatedBy;
	private CaseStatusMaster caseStatusMaster;
	private LoginMaster loginMasterByUpdatedBy;
	private HelpCategoryType helpCategoryType;
	private CaseType caseType;
	private Date createdOn;
	private Date updatedOn;
	private String personName;
	private Date dateOfBirth;
	private String caseDescription;
	private String contactNumber1;
	private String contactNumber2;
	private String source;
	private String emailId;
	private Set<CaseArtifact> caseArtifacts = new HashSet<CaseArtifact>(0);
	private Set<FundManagement> fundManagements = new HashSet<FundManagement>(0);
	private Set<CaseActivity> caseActivities = new HashSet<CaseActivity>(0);
	private Set<CaseUser> caseUsers = new HashSet<CaseUser>(0);

	public CaseMaster() {
	}

	public CaseMaster(LoginMaster loginMasterByCreatedBy,
			CaseStatusMaster caseStatusMaster,
			LoginMaster loginMasterByUpdatedBy,
			HelpCategoryType helpCategoryType, CaseType caseType,
			Date createdOn, Date updatedOn, String personName,
			String caseDescription, String contactNumber1, String source) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
		this.caseStatusMaster = caseStatusMaster;
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
		this.helpCategoryType = helpCategoryType;
		this.caseType = caseType;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.personName = personName;
		this.caseDescription = caseDescription;
		this.contactNumber1 = contactNumber1;
		this.source = source;
	}

	public CaseMaster(LoginMaster loginMasterByCreatedBy,
			CaseStatusMaster caseStatusMaster,
			LoginMaster loginMasterByUpdatedBy,
			HelpCategoryType helpCategoryType, CaseType caseType,
			Date createdOn, Date updatedOn, String personName,
			Date dateOfBirth, String caseDescription, String contactNumber1,
			String contactNumber2, String source,
			Set<CaseArtifact> caseArtifacts,
			Set<FundManagement> fundManagements,
			Set<CaseActivity> caseActivities, Set<CaseUser> caseUsers) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
		this.caseStatusMaster = caseStatusMaster;
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
		this.helpCategoryType = helpCategoryType;
		this.caseType = caseType;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.personName = personName;
		this.dateOfBirth = dateOfBirth;
		this.caseDescription = caseDescription;
		this.contactNumber1 = contactNumber1;
		this.contactNumber2 = contactNumber2;
		this.source = source;
		this.caseArtifacts = caseArtifacts;
		this.fundManagements = fundManagements;
		this.caseActivities = caseActivities;
		this.caseUsers = caseUsers;
	}
	
	public CaseMaster(Address address,LoginMaster loginMasterByCreatedBy,
			CaseStatusMaster caseStatusMaster,
			LoginMaster loginMasterByUpdatedBy,
			HelpCategoryType helpCategoryType, CaseType caseType,
			Date createdOn, Date updatedOn, String personName,
			Date dateOfBirth, String caseDescription, String contactNumber1,
			String contactNumber2, String source,
			Set<CaseArtifact> caseArtifacts,
			Set<FundManagement> fundManagements,
			Set<CaseActivity> caseActivities, Set<CaseUser> caseUsers) {
		this.address = address;
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
		this.caseStatusMaster = caseStatusMaster;
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
		this.helpCategoryType = helpCategoryType;
		this.caseType = caseType;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.personName = personName;
		this.dateOfBirth = dateOfBirth;
		this.caseDescription = caseDescription;
		this.contactNumber1 = contactNumber1;
		this.contactNumber2 = contactNumber2;
		this.source = source;
		this.caseArtifacts = caseArtifacts;
		this.fundManagements = fundManagements;
		this.caseActivities = caseActivities;
		this.caseUsers = caseUsers;
	}
	
	
	public CaseMaster(Date createdDate, Date updatedDate, String personName2,
			Date dateOfBirth2, String caseDescription2, String contact1,
			String contact2, String caseSource) {
		this.createdOn =createdDate;
		this.updatedOn=updatedDate;
		this.personName=personName2;
		this.dateOfBirth=dateOfBirth2;
		this.caseDescription= caseDescription2;
		this.contactNumber1=contact1;
		this.contactNumber2= contact2;
		this.source=caseSource;
		
	}
	public CaseMaster(Date createdDate, Date updatedDate, String personName2,
			String emialId,Date dateOfBirth2, String caseDescription2, String contact1,
			String contact2, String caseSource) {
		this.emailId=emialId;
		this.createdOn =createdDate;
		this.updatedOn=updatedDate;
		this.personName=personName2;
		this.dateOfBirth=dateOfBirth2;
		this.caseDescription= caseDescription2;
		this.contactNumber1=contact1;
		this.contactNumber2= contact2;
		this.source=caseSource;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "email_id", unique = true, nullable = true, length = 100)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	public LoginMaster getLoginMasterByCreatedBy() {
		return this.loginMasterByCreatedBy;
	}

	public void setLoginMasterByCreatedBy(LoginMaster loginMasterByCreatedBy) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_status_id", nullable = false)
	public CaseStatusMaster getCaseStatusMaster() {
		return this.caseStatusMaster;
	}

	public void setCaseStatusMaster(CaseStatusMaster caseStatusMaster) {
		this.caseStatusMaster = caseStatusMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	public LoginMaster getLoginMasterByUpdatedBy() {
		return this.loginMasterByUpdatedBy;
	}

	public void setLoginMasterByUpdatedBy(LoginMaster loginMasterByUpdatedBy) {
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "help_category_id", nullable = false)
	public HelpCategoryType getHelpCategoryType() {
		return this.helpCategoryType;
	}

	public void setHelpCategoryType(HelpCategoryType helpCategoryType) {
		this.helpCategoryType = helpCategoryType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_type_id", nullable = false)
	public CaseType getCaseType() {
		return this.caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", nullable = false, length = 0)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", nullable = false, length = 0)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "person_name", nullable = false, length = 100)
	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_birth", length = 0)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "case_description", nullable = false, length = 500)
	public String getCaseDescription() {
		return this.caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	@Column(name = "contact_number1", nullable = false, length = 25)
	public String getContactNumber1() {
		return this.contactNumber1;
	}

	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}

	@Column(name = "contact_number2", length = 45)
	public String getContactNumber2() {
		return this.contactNumber2;
	}

	public void setContactNumber2(String contactNumber2) {
		this.contactNumber2 = contactNumber2;
	}

	@Column(name = "source", nullable = false, length = 100)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseMaster")
	public Set<CaseArtifact> getCaseArtifacts() {
		return this.caseArtifacts;
	}

	public void setCaseArtifacts(Set<CaseArtifact> caseArtifacts) {
		this.caseArtifacts = caseArtifacts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseMaster")
	public Set<FundManagement> getFundManagements() {
		return this.fundManagements;
	}

	public void setFundManagements(Set<FundManagement> fundManagements) {
		this.fundManagements = fundManagements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseMaster")
	public Set<CaseActivity> getCaseActivities() {
		return this.caseActivities;
	}

	public void setCaseActivities(Set<CaseActivity> caseActivities) {
		this.caseActivities = caseActivities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseMaster")
	public Set<CaseUser> getCaseUsers() {
		return this.caseUsers;
	}

	public void setCaseUsers(Set<CaseUser> caseUsers) {
		this.caseUsers = caseUsers;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


}
