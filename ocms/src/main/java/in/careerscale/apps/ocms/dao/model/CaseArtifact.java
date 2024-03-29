package in.careerscale.apps.ocms.dao.model;

// Generated Jul 7, 2013 10:49:55 AM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
	private LoginMaster loginMaster;
	private DocumentType documentType;
	private CaseMaster caseMaster;
	private byte[] artifact;
	private String fileExtension;

	public CaseArtifact() {
	}

	public CaseArtifact(CaseMaster caseMaster, byte[] artifact) {
		this.caseMaster = caseMaster;
		this.artifact = artifact;
	}

	public CaseArtifact(LoginMaster loginMaster, DocumentType documentType,
			CaseMaster caseMaster, byte[] artifact, String fileExtension) {
		this.loginMaster = loginMaster;
		this.documentType = documentType;
		this.caseMaster = caseMaster;
		this.artifact = artifact;
		this.fileExtension = fileExtension;
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
	@JoinColumn(name = "added_by")
	public LoginMaster getLoginMaster() {
		return this.loginMaster;
	}

	public void setLoginMaster(LoginMaster loginMaster) {
		this.loginMaster = loginMaster;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_type")
	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id", nullable = false)
	public CaseMaster getCaseMaster() {
		return this.caseMaster;
	}

	public void setCaseMaster(CaseMaster caseMaster) {
		this.caseMaster = caseMaster;
	}

	@Column(name = "artifact", nullable = false)
	public byte[] getArtifact() {
		return this.artifact;
	}

	public void setArtifact(byte[] artifact) {
		this.artifact = artifact;
	}

	@Column(name = "file_extension", length = 45)
	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

}
