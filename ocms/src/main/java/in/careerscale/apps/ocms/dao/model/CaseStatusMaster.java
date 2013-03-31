package in.careerscale.apps.ocms.dao.model;

// Generated Mar 16, 2013 8:20:08 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * CaseStatusMaster generated by hbm2java
 */
@Entity
@Table(name = "case_status_master", catalog = "ocms", uniqueConstraints = @UniqueConstraint(columnNames = "case_status_name"))
public class CaseStatusMaster implements java.io.Serializable {

	private Integer id;
	private String caseStatusName;
	private String caseStatusDescription;
	private Set<CaseMaster> caseMasters = new HashSet<CaseMaster>(0);

	public CaseStatusMaster() {
	}

	public CaseStatusMaster(String caseStatusName) {
		this.caseStatusName = caseStatusName;
	}
	

	public CaseStatusMaster(String caseStatusName, String caseStatusDescription) {
		super();
		this.caseStatusName = caseStatusName;
		this.caseStatusDescription = caseStatusDescription;
	}

	public CaseStatusMaster(String caseStatusName,
			String caseStatusDescription, Set<CaseMaster> caseMasters) {
		this.caseStatusName = caseStatusName;
		this.caseStatusDescription = caseStatusDescription;
		this.caseMasters = caseMasters;
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

	@Column(name = "case_status_name", unique = true, nullable = false, length = 50)
	public String getCaseStatusName() {
		return this.caseStatusName;
	}

	public void setCaseStatusName(String caseStatusName) {
		this.caseStatusName = caseStatusName;
	}

	@Column(name = "case_status_description")
	public String getCaseStatusDescription() {
		return this.caseStatusDescription;
	}

	public void setCaseStatusDescription(String caseStatusDescription) {
		this.caseStatusDescription = caseStatusDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caseStatusMaster")
	public Set<CaseMaster> getCaseMasters() {
		return this.caseMasters;
	}

	public void setCaseMasters(Set<CaseMaster> caseMasters) {
		this.caseMasters = caseMasters;
	}

}
