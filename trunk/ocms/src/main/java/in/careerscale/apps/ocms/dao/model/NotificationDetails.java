package in.careerscale.apps.ocms.dao.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "notification_details", catalog = "ocms")
public class NotificationDetails implements java.io.Serializable 
{
private Integer id;
private String notificationType;
private String templateText;
private NotificationTemplate template;

public NotificationDetails()
{
	
}

public NotificationDetails(String notificationType)
{
	this.notificationType=notificationType;
}

public NotificationDetails(String notificationType,String templateText)
{
	this.notificationType=notificationType;
	this.templateText=templateText;
}
public NotificationDetails(String notificationType,String templateText,NotificationTemplate template)
{
	this.notificationType=notificationType;
	this.templateText=templateText;
	this.template=template;
}
@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}
@Column(name = "notification_type", nullable = false, length = 45)
public String getNotificationType() {
	return notificationType;
}

public void setNotificationType(String notificationType) {
	this.notificationType = notificationType;
}
@Column(name = "template_text", nullable = false, length = 45)
public String getTemplateText() {
	return templateText;
}

public void setTemplateText(String templateText) {
	this.templateText = templateText;
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
