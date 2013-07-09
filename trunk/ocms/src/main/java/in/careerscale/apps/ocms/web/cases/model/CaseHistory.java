package in.careerscale.apps.ocms.web.cases.model;

public class CaseHistory
{
	private Integer caseId;

	private String userName;

	private String reason;

	private String status;

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
