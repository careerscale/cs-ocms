package in.careerscale.apps.ocms.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notification", catalog = "ocms")
public class Notification implements java.io.Serializable {
	private Integer id;
	private String recipientInfo;
	private Date createdOn;
	private Date updatedOn;
	private LoginMaster loginMasterByCreatedBy;
	private LoginMaster loginMasterByUpdatedBy;
	private CaseMaster caseMaster;
	private NotificationRecipient recipient;
	private NotificationStatus status;
	private NotificationTemplate template;

	public Notification() {

	}

	public Notification( String recipientInfo, Date createdOn,
			Date updatedOn, LoginMaster loginMasterByCreatedBy,
			LoginMaster loginMasterByUpdatedBy, CaseMaster caseMaster,
			NotificationRecipient recipient, NotificationStatus status,
			NotificationTemplate template) {
		this.caseMaster=caseMaster;
		this.createdOn=createdOn;
		this.updatedOn=updatedOn;
	    this.loginMasterByCreatedBy=loginMasterByCreatedBy;
	    this.loginMasterByUpdatedBy=loginMasterByUpdatedBy;
	    this.recipient=recipient;
	    this.recipientInfo=recipientInfo;
	    this.status=status;
	    this.template=template;

	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "recepient_additional_info", nullable = false, length = 95)
	public String getRecipientInfo() {
		return recipientInfo;
	}

	public void setRecipientInfo(String recipientInfo) {
		this.recipientInfo = recipientInfo;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", nullable = false, length = 0)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", nullable = false, length = 0)
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable = false)
	public LoginMaster getLoginMasterByCreatedBy() {
		return loginMasterByCreatedBy;
	}

	public void setLoginMasterByCreatedBy(LoginMaster loginMasterByCreatedBy) {
		this.loginMasterByCreatedBy = loginMasterByCreatedBy;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable = false)
	public LoginMaster getLoginMasterByUpdatedBy() {
		return loginMasterByUpdatedBy;
	}

	public void setLoginMasterByUpdatedBy(LoginMaster loginMasterByUpdatedBy) {
		this.loginMasterByUpdatedBy = loginMasterByUpdatedBy;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_id", nullable = false)
	public CaseMaster getCaseMaster() {
		return caseMaster;
	}

	public void setCaseMaster(CaseMaster caseMaster) {
		this.caseMaster = caseMaster;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reciepient_type", nullable = false)
	public NotificationRecipient getRecipient() {
		return recipient;
	}

	public void setRecipient(NotificationRecipient recipient) {
		this.recipient = recipient;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", nullable = false)
	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id", nullable = false)
	public NotificationTemplate getTemplate() {
		return template;
	}

	public void setTemplate(NotificationTemplate template) {
		this.template = template;
	}

}
