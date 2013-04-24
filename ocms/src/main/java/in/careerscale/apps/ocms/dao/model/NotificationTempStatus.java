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
@Table(name = "notification_temp_status", catalog = "ocms")
public class NotificationTempStatus implements java.io.Serializable  {
	private Integer id;
	private String name;
	private Set<NotificationTemplate> notificationtemp = new HashSet<NotificationTemplate>(0);
	
	public NotificationTempStatus()
	{
		
	}
	public NotificationTempStatus(String name) {
		this.name = name;
	}
	
	public NotificationTempStatus(String name, Set<NotificationTemplate> notificationtemp) {
		this.name = name;
		this.notificationtemp = notificationtemp;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notification_template")
	public Set<NotificationTemplate> getNotificationtemp() {
		return notificationtemp;
	}
	public void setNotificationtemp(Set<NotificationTemplate> notificationtemp) {
		this.notificationtemp = notificationtemp;
	}
	

}
