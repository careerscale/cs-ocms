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
@Table(name = "notification_template_status", catalog = "ocms")
public class NotificationTemplateStatus implements java.io.Serializable  {
	private Integer id;
	private String name;
	private Set<NotificationTemplate> notificationTemplate = new HashSet<NotificationTemplate>(0);
	
	public NotificationTemplateStatus()
	{
		
	}
	public NotificationTemplateStatus(String name) {
		this.name = name;
	}
	
	public NotificationTemplateStatus(String name, Set<NotificationTemplate> notificationtemp) {
		this.name = name;
		this.notificationTemplate = notificationtemp;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notificationTemplateStatus")
	public Set<NotificationTemplate> getNotificationTemplate() {
		return notificationTemplate;
	}
	public void setNotificationTemplate(Set<NotificationTemplate> notificationtemp) {
		this.notificationTemplate = notificationtemp;
	}
	

}
