package in.careerscale.apps.ocms.dao.model;

// Generated Jul 21, 2013 10:22:12 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Notification generated by hbm2java
 */
@Entity
@Table(name = "notification", catalog = "ocms")
public class Notification implements java.io.Serializable {

	private Integer id;
	private LoginMaster loginMasterByCreatedBy;
	private NotificationStatus notificationStatus;
	private LoginMaster loginMasterByUpdatedBy;
	private NotificationRecipientType notificationRecipientType;
	private NotificationChannel notificationChannel;
	private CaseMaster caseMaster;
	private String templateName;
	private String recepientAdditionalInfo;
	private Date createdOn;
	private Date updatedOn;

	public Notification() {
	}

	public Notification(LoginMaster loginMasterByCreatedBy,
			LoginMaster loginMasterByUpdatedBy, Date createdOn, Date updatedOn) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public Notification(LoginMaster loginMasterByCreatedBy,
			NotificationStatus notificationStatus,
			LoginMaster loginMasterByUpdatedBy,
			NotificationRecipientType notificationRecipientType,
			NotificationChannel notificationChannel, CaseMaster caseMaster,
			String templateName, String recepientAdditionalInfo,
			Date createdOn, Date updatedOn) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
		this.notificationStatus = notificationStatus;
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
		this.notificationRecipientType = notificationRecipientType;
		this.notificationChannel = notificationChannel;
		this.caseMaster = caseMaster;
		this.templateName = templateName;
		this.recepientAdditionalInfo = recepientAdditionalInfo;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
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
	@JoinColumn(name = "created_by", nullable = false)
	public LoginMaster getLoginMasterByCreatedBy() {
		return this.loginMasterByCreatedBy;
	}

	public void setLoginMasterByCreatedBy(LoginMaster loginMasterByCreatedBy) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	public NotificationStatus getNotificationStatus() {
		return this.notificationStatus;
	}

	public void setNotificationStatus(NotificationStatus notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	public LoginMaster getLoginMasterByUpdatedBy() {
		return this.loginMasterByUpdatedBy;
	}

	public void setLoginMasterByUpdatedBy(LoginMaster loginMasterByUpdatedBy) {
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reciepient_type_id")
	public NotificationRecipientType getNotificationRecipientType() {
		return this.notificationRecipientType;
	}

	public void setNotificationRecipientType(
			NotificationRecipientType notificationRecipientType) {
		this.notificationRecipientType = notificationRecipientType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "channel_id")
	public NotificationChannel getNotificationChannel() {
		return this.notificationChannel;
	}

	public void setNotificationChannel(NotificationChannel notificationChannel) {
		this.notificationChannel = notificationChannel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id")
	public CaseMaster getCaseMaster() {
		return this.caseMaster;
	}

	public void setCaseMaster(CaseMaster caseMaster) {
		this.caseMaster = caseMaster;
	}

	@Column(name = "template_name", length = 100)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "recepient_additional_info")
	public String getRecepientAdditionalInfo() {
		return this.recepientAdditionalInfo;
	}

	public void setRecepientAdditionalInfo(String recepientAdditionalInfo) {
		this.recepientAdditionalInfo = recepientAdditionalInfo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", nullable = false, length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", nullable = false, length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
