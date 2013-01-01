package in.careerscale.apps.ocms.model;

// Generated Jun 6, 2011 8:21:35 AM by Hibernate Tools 3.4.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CaseArtifact generated by hbm2java
 */
@Entity
@Table(name = "case_artifact", catalog = "ocms")
public class CaseArtifact implements java.io.Serializable {

	private Integer id;
	private CaseMaster caseMaster;
	private String artifactType;
	private byte[] artifact;

	public CaseArtifact() {
	}

	public CaseArtifact(CaseMaster caseMaster, String artifactType,
			byte[] artifact) {
		this.caseMaster = caseMaster;
		this.artifactType = artifactType;
		this.artifact = artifact;
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
	@JoinColumn(name = "case_id", nullable = false)
	public CaseMaster getCaseMaster() {
		return this.caseMaster;
	}

	public void setCaseMaster(CaseMaster caseMaster) {
		this.caseMaster = caseMaster;
	}

	@Column(name = "artifact_type", nullable = false, length = 25)
	public String getArtifactType() {
		return this.artifactType;
	}

	public void setArtifactType(String artifactType) {
		this.artifactType = artifactType;
	}

	@Column(name = "artifact", nullable = false)
	public byte[] getArtifact() {
		return this.artifact;
	}

	public void setArtifact(byte[] artifact) {
		this.artifact = artifact;
	}

}