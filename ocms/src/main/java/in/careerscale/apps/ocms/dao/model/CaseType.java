package in.careerscale.apps.ocms.dao.model;

// Generated Jul 7, 2013 10:49:55 AM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * CaseType generated by hbm2java
 */
@Entity
@Table(name = "case_type", catalog = "ocms", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class CaseType implements java.io.Serializable {

	private Integer id;
	private CaseType caseType;
	private String name;
	private String description;
	private Set<CaseType> caseTypes = new HashSet(0);
	private Set<DocumentType> documentTypes = new HashSet(0);
	private Set<LoginMaster> loginMasters = new HashSet(0);
	private Set<CaseMaster> caseMasters = new HashSet(0);
	private Set caseTypeApprovers = new HashSet(0);

	public CaseType() {
	}

	public CaseType(String name) {
		this.name = name;
	}

	public CaseType(CaseType caseType, String name, String description,
			Set caseTypes, Set documentTypes, Set loginMasters, Set caseMasters) {
		this.caseType = caseType;
		this.name = name;
		this.description = description;
		this.caseTypes = caseTypes;
		this.documentTypes = documentTypes;
		this.loginMasters = loginMasters;
		this.caseMasters = caseMasters;
	}

	public CaseType(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public CaseType(Integer id, String name, String description)
	{
		this.id = id;
		this.name = name;
		this.description = description;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_type_id")
	public CaseType getCaseType() {
		return this.caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseType")
	public Set<CaseType> getCaseTypes()
	{
		return this.caseTypes;
	}

	public void setCaseTypes(Set<CaseType> caseTypes)
	{
		this.caseTypes = caseTypes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseType")
	public Set<DocumentType> getDocumentTypes()
	{
		return this.documentTypes;
	}

	public void setDocumentTypes(Set<DocumentType> documentTypes)
	{
		this.documentTypes = documentTypes;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "case_type_user", catalog = "ocms", joinColumns = { @JoinColumn(name = "case_type_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public Set<LoginMaster> getLoginMasters()
	{
		return this.loginMasters;
	}

	public void setLoginMasters(Set<LoginMaster> loginMasters)
	{
		this.loginMasters = loginMasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseType")
	public Set<CaseMaster> getCaseMasters()
	{
		return this.caseMasters;
	}

	public void setCaseMasters(Set<CaseMaster> caseMasters)
	{
		this.caseMasters = caseMasters;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseType")
	public Set<CaseTypeApprover> getCaseTypeApprovers()
	{
		return this.caseTypeApprovers;
	}

	public void setCaseTypeApprovers(Set<CaseTypeApprover> caseTypeApprovers)
	{
		this.caseTypeApprovers = caseTypeApprovers;
	}

}
