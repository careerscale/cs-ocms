package in.careerscale.apps.ocms.dao.model;

// Generated Jul 7, 2013 10:08:03 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CaseTypeApproverId generated by hbm2java
 */
@Embeddable
public class CaseTypeApproverId implements java.io.Serializable {

	private int caseType;
	private int loginId;
	private int organizationId;

	public CaseTypeApproverId() {
	}

	public CaseTypeApproverId(int caseType, int loginId, int organizationId) {
		this.caseType = caseType;
		this.loginId = loginId;
		this.organizationId = organizationId;
	}

	@Column(name = "case_type", nullable = false)
	public int getCaseType() {
		return this.caseType;
	}

	public void setCaseType(int caseType) {
		this.caseType = caseType;
	}

	@Column(name = "login_id", nullable = false)
	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	@Column(name = "organization_id", nullable = false)
	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CaseTypeApproverId))
			return false;
		CaseTypeApproverId castOther = (CaseTypeApproverId) other;

		return (this.getCaseType() == castOther.getCaseType())
				&& (this.getLoginId() == castOther.getLoginId())
				&& (this.getOrganizationId() == castOther.getOrganizationId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCaseType();
		result = 37 * result + this.getLoginId();
		result = 37 * result + this.getOrganizationId();
		return result;
	}

}
