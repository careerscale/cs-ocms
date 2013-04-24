package in.careerscale.apps.ocms.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification_template", catalog = "ocms")
public class NotificationTemplate implements java.io.Serializable 
{
	private Integer id;
	private String name;
	private NotificationTempStatus notificationTempStatus;
	
	public NotificationTemplate()
	{
		
	}
	public NotificationTemplate(String name)
	{
		this.name=name;
	}
	public NotificationTemplate(String name,NotificationTempStatus notificationTempStatus)
	{
		this.name=name;
		this.notificationTempStatus=notificationTempStatus;
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	public NotificationTempStatus getNotificationTempStatus() {
		return notificationTempStatus;
	}
	public void setNotificationTempStatus(
			NotificationTempStatus notificationTempStatus) {
		this.notificationTempStatus = notificationTempStatus;
	}
	
}
