package in.careerscale.apps.ocms.web.cases.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CaseHistory implements Serializable
{
	private Integer caseId;

	private String userName;

	private String reason;

	private String status;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date actionDate;
	private String approverContactNo;
	private String approverContactEmail;
	
	public CaseHistory(Integer caseId, String reason, String status, String userName,  Date actionDate, String approverContactEmail, String approverContactNo)
	{
		this.caseId = caseId;
		this.reason = reason;
		this.status = status;
		this.userName = userName;
		this.actionDate = actionDate;
		this.approverContactEmail = approverContactEmail;
		this.approverContactNo = approverContactNo;
		
	}
	public String getApproverContactNo()
	{
		return approverContactNo;
	}
	public void setApproverContactNo(String approverContactNo)
	{
		this.approverContactNo = approverContactNo;
	}
	public String getApproverContactEmail()
	{
		return approverContactEmail;
	}
	public void setApproverContactEmail(String approverContactEmail)
	{
		this.approverContactEmail = approverContactEmail;
	}
	public CaseHistory()
	{
		
	}
	public CaseHistory(Integer caseId, String reason, String status)
	{
		this.caseId = caseId;
		this.reason = reason;
		this.status = status;
	}

	public CaseHistory(Integer caseId, String reason, String status, String userName)
	{
		this.caseId = caseId;
		this.reason = reason;
		this.status = status;
		this.userName = userName;
	}

	public Integer getCaseId()
	{
		return caseId;
	}

	public void setCaseId(Integer caseId)
	{
		this.caseId = caseId;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
