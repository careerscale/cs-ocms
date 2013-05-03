package in.careerscale.apps.ocms.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sun.misc.JavaAWTAccess;

@Entity
@Table(name="document_options" ,catalog="ocms")
public class DocumentOptions implements java.io.Serializable {
	private Integer id;
	private String name;
	private Document documentType;
	
	public DocumentOptions()
	{
		
	}
	public DocumentOptions(Integer id,String name)
	{
		this.name=name;
		this.id=id;
	}
	public DocumentOptions(String name)
	{
		this.name=name;
	}
	public DocumentOptions(String name,Document documentType)
	{
		this.name=name;
		this.documentType=documentType;
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
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="document_type", nullable=true)
	public Document getDocumentType() {
		return documentType;
	}
	public void setDocumentType(Document documentType) {
		this.documentType = documentType;
	}
}
