package in.careerscale.apps.ocms.dao.model;

// Generated Jul 7, 2013 10:08:03 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CaseTypeApprover generated by hbm2java
 */
@Entity
@Table(name = "case_type_approver", catalog = "ocms")
public class CaseTypeApprover implements java.io.Serializable {

	private CaseTypeApproverId id;
	private Organization organization;
	private LoginMaster loginMaster;
	private CaseType caseType;

	public CaseTypeApprover() {
	}

	public CaseTypeApprover(CaseTypeApproverId id, Organization organization,
			LoginMaster loginMaster, CaseType caseType) {
		this.id = id;
		this.organization = organization;
		this.loginMaster = loginMaster;
		this.caseType = caseType;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "caseType", column = @Column(name = "case_type", nullable = false)),
			@AttributeOverride(name = "loginId", column = @Column(name = "login_id", nullable = false)),
			@AttributeOverride(name = "organizationId", column = @Column(name = "organization_id", nullable = false)) })
	public CaseTypeApproverId getId() {
		return this.id;
	}

	public void setId(CaseTypeApproverId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false, insertable = false, updatable = false)
	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "login_id", nullable = false, insertable = false, updatable = false)
	public LoginMaster getLoginMaster() {
		return this.loginMaster;
	}

	public void setLoginMaster(LoginMaster loginMaster) {
		this.loginMaster = loginMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_type", nullable = false, insertable = false, updatable = false)
	public CaseType getCaseType() {
		return this.caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

}
