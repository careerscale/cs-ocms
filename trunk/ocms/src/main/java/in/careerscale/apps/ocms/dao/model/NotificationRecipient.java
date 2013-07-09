package in.careerscale.apps.ocms.dao.model;

// Generated Jul 7, 2013 10:49:55 AM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * NotificationRecipient generated by hbm2java
 */
@Entity
@Table(name = "notification_recipient", catalog = "ocms")
public class NotificationRecipient implements java.io.Serializable {

	private Integer id;
	private String type;
	private Set notifications = new HashSet(0);

	public NotificationRecipient() {
	}

	public NotificationRecipient(String type, Set notifications) {
		this.type = type;
		this.notifications = notifications;
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

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notificationRecipient")
	public Set<Notification> getNotifications()
	{
		return this.notifications;
	}

	public void setNotifications(Set<Notification> notifications)
	{
		this.notifications = notifications;
	}

}