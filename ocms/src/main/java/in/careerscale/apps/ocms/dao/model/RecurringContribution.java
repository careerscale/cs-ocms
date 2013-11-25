package in.careerscale.apps.ocms.dao.model;

// Generated Jul 19, 2013 7:52:43 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RecurringContribution generated by hbm2java
 */
@Entity
@Table(name = "recurring_contribution", catalog = "ocms")
public class RecurringContribution implements java.io.Serializable {

	private Integer id;
	private CaseMaster caseMaster;
	private LoginMaster loginMaster;
	private Integer creditAmount;
	private String frequency;
	private Integer dayOfMonth;
	private Boolean isActive;
	private Date lastContributionDate;
	private Integer debitAmount;
	private String donorName;

	public RecurringContribution() {
	}

	public RecurringContribution(CaseMaster caseMaster,
			LoginMaster loginMaster, Integer creditAmount, String frequency,
			Integer dayOfMonth, Boolean isActive, Date lastContributionDate,
			Integer debitAmount) {
		this.caseMaster = caseMaster;
		this.loginMaster = loginMaster;
		this.creditAmount = creditAmount;
		this.frequency = frequency;
		this.dayOfMonth = dayOfMonth;
		this.isActive = isActive;
		this.lastContributionDate = lastContributionDate;
		this.debitAmount = debitAmount;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "case_id")
	public CaseMaster getCaseMaster() {
		return this.caseMaster;
	}

	public void setCaseMaster(CaseMaster caseMaster) {
		this.caseMaster = caseMaster;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "login_id")
	public LoginMaster getLoginMaster() {
		return this.loginMaster;
	}

	public void setLoginMaster(LoginMaster loginMaster) {
		this.loginMaster = loginMaster;
	}

	@Column(name = "credit_amount")
	public Integer getCreditAmount() {
		return this.creditAmount;
	}

	public void setCreditAmount(Integer creditAmount) {
		this.creditAmount = creditAmount;
	}

	@Column(name = "frequency", length = 100)
	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Column(name = "day_of_month")
	public Integer getDayOfMonth() {
		return this.dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_contribution_date", length = 10)
	public Date getLastContributionDate() {
		return this.lastContributionDate;
	}

	public void setLastContributionDate(Date lastContributionDate) {
		this.lastContributionDate = lastContributionDate;
	}

	@Column(name = "debit_amount")
	public Integer getDebitAmount() {
		return this.debitAmount;
	}

	public void setDebitAmount(Integer debitAmount) {
		this.debitAmount = debitAmount;
	}

	@Column(name = "donor_name")
	public String getDonorName()
	{
		return donorName;
	}

	public void setDonorName(String donorName)
	{
		this.donorName = donorName;
	}

}