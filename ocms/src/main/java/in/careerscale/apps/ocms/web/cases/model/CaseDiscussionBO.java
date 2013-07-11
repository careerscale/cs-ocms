package in.careerscale.apps.ocms.web.cases.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class CaseDiscussionBO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer caseId;

	private String createdBy;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date  createdOn;
	
	private Integer parentDiscussionId;
	
	private String comments;
	
	private String subject;
	
	private String parentSubject;
	
	private String parentCreatedBy;
	
	public String getParentSubject()
	{
		return parentSubject;
	}
	public void setParentSubject(String parentSubject)
	{
		this.parentSubject = parentSubject;
	}
	public String getParentCreatedBy()
	{
		return parentCreatedBy;
	}
	public void setParentCreatedBy(String parentCreatedBy)
	{
		this.parentCreatedBy = parentCreatedBy;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public CaseDiscussionBO( Integer caseId, String createdBy,  Date  createdOn,  String comments)
	{
		this.caseId = caseId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.comments = comments;
	}
	
	public CaseDiscussionBO( Integer id, Integer caseId, String createdBy,  Date  createdOn,  String comments, String subject, String parentSubject, String parentCreatedBy, Integer parentDiscussionId)
	{
		this.id = id;
		this.caseId = caseId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.comments = comments;
		this.subject = subject;
		this.parentSubject = parentSubject;
		this.parentCreatedBy = parentCreatedBy;
		this.parentDiscussionId = parentDiscussionId;
	}
	
	public CaseDiscussionBO()
	{

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getCaseId()
	{
		return caseId;
	}

	public void setCaseId(Integer caseId)
	{
		this.caseId = caseId;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

	public Integer getParentDiscussionId()
	{
		return parentDiscussionId;
	}

	public void setParentDiscussionId(Integer parentDiscussionId)
	{
		this.parentDiscussionId = parentDiscussionId;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}
	
	

}
