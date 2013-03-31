package in.careerscale.apps.ocms.web.backoffice.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BOBean {
	private String name;
	private String description;
	private Integer id;
	private String caseStatusName;
	private String caseStatusDescription;
	
	public String getCaseStatusName() {
		return caseStatusName;
	}
	public void setCaseStatusName(String caseStatusName) {
		this.caseStatusName = caseStatusName;
	}
	public String getCaseStatusDescription() {
		return caseStatusDescription;
	}
	public void setCaseStatusDescription(String caseStatusDescription) {
		this.caseStatusDescription = caseStatusDescription;
	}

	
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).
			       append("name", name).
			       append("description", description).
			       append("caseStatusName",caseStatusName).
			       append("caseStatusDescription",caseStatusDescription).
			       toString();
	}
	
		

}
