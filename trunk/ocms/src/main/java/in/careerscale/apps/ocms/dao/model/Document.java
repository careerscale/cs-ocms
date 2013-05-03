package in.careerscale.apps.ocms.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="document_type",catalog="ocms")
public class Document implements java.io.Serializable{
	
	private Integer id;
	private String name;
	private String supportedFormat;
	private CaseType caseType;
	private boolean mandatory;
	private Integer maxSize;
	private Set<CaseArtifact> caseArtifact=new HashSet<CaseArtifact>(0);
	private Set<DocumentOptions> documentOptions=new HashSet<DocumentOptions>(0);
	
	public Document()
	{}
	
	public Document(String name,String supportedFormat,CaseType caseType,boolean mandatory,Integer maxSize)
	{
		this.caseType=caseType;
		this.mandatory=true;
		this.maxSize=maxSize;
		this.name=name;
		this.supportedFormat=supportedFormat;
	}
	public Document(String name,String supportedFormat,CaseType caseType,boolean mandatory,Integer maxSize,Set<CaseArtifact> caseArtifact)
	{
		this.caseType=caseType;
		this.mandatory=true;
		this.maxSize=maxSize;
		this.name=name;
		this.supportedFormat=supportedFormat;
		this.caseArtifact=caseArtifact;
	}
	
	public Document(String name,String supportedFormat,CaseType caseType,boolean mandatory,Integer maxSize,Set<CaseArtifact> caseArtifact,Set<DocumentOptions> documentOptions)
	{
		this.caseType=caseType;
		this.mandatory=true;
		this.maxSize=maxSize;
		this.name=name;
		this.supportedFormat=supportedFormat;
		this.caseArtifact=caseArtifact;
		this.documentOptions=documentOptions;
	}
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    @Column(name="name",nullable=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    @Column(name="supported_formats",nullable=true)
	public String getSupportedFormat() {
		return supportedFormat;
	}

	public void setSupportedFormat(String supportedFormat) {
		this.supportedFormat = supportedFormat;
	}
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="case_type_id", nullable=true)
	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}
    @Column(name ="is_mandatory",nullable= true)
	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
    @Column(name ="max_size",nullable= true)
    public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="document")
	public Set<CaseArtifact> getCaseArtifact() {
		return caseArtifact;
	}

	public void setCaseArtifact(Set<CaseArtifact> caseArtifact) {
		this.caseArtifact = caseArtifact;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="documentTypes")
	public Set<DocumentOptions> getDocumentOptions() {
		return documentOptions;
	}

	public void setDocumentOptions(Set<DocumentOptions> documentOptions) {
		this.documentOptions = documentOptions;
	}
	
}

