package in.careerscale.apps.ocms.dao.model;

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


@Entity
@Table(name = "notification_status", catalog = "ocms")
public class NotificationStatus implements java.io.Serializable {
	
	private Integer id;
	private String name;
	private NotificationStatus notificationStatus;
	private Set<Notification> notification = new HashSet<Notification>(0);
	
	public NotificationStatus()
	{
		
	}
	public NotificationStatus(String name) {
		this.name = name;
	}
	
	public NotificationStatus(String name, Set<Notification> notification) {
		this.name = name;
		this.notification = notification;
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
	
	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	public Set<Notification> getNotification() {
		return this.notification;
	}

	public void setNotification(Set<Notification> notification) {
		this.notification = notification;
	}


}
