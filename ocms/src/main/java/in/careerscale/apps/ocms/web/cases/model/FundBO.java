package in.careerscale.apps.ocms.web.cases.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class FundBO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer caseId;

	private String donor;
	
	private String fundStatus;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date  promisedDate;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date  confirmedDate;
	
	private String purpose;
	
	private Integer amount;
	
	private Integer debitAmount;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date  receiptDate;
	
	private String createdBy;

	public FundBO()
	{
		
	}
	
	public FundBO(Integer id,Integer caseId, String donor, String fundStatus, Date  promisedDate, Date  confirmedDate
			,String purpose,Integer amount)
	{
		this.id = id;
		this.caseId = caseId;
		this.donor = donor;
		this.fundStatus = fundStatus;
		this.promisedDate = promisedDate;
		this.confirmedDate = confirmedDate;
		this.purpose = purpose;
		this.amount = amount;
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

	public String getDonor()
	{
		return donor;
	}

	public void setDonor(String donor)
	{
		this.donor = donor;
	}

	public String getFundStatus()
	{
		return fundStatus;
	}

	public void setFundStatus(String fundStatus)
	{
		this.fundStatus = fundStatus;
	}

	public Date getPromisedDate()
	{
		return promisedDate;
	}

	public void setPromisedDate(Date promisedDate)
	{
		this.promisedDate = promisedDate;
	}

	public Date getConfirmedDate()
	{
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate)
	{
		this.confirmedDate = confirmedDate;
	}

	public String getPurpose()
	{
		return purpose;
	}

	public void setPurpose(String purpose)
	{
		this.purpose = purpose;
	}

	public Integer getAmount()
	{
		return amount;
	}

	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

	public Integer getDebitAmount()
	{
		return debitAmount;
	}

	public void setDebitAmount(Integer debitAmount)
	{
		this.debitAmount = debitAmount;
	}

	public Date getReceiptDate()
	{
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate)
	{
		this.receiptDate = receiptDate;
	}

	public String getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
	
	
	

}
