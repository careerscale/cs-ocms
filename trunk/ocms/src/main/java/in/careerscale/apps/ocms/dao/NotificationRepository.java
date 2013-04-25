package in.careerscale.apps.ocms.dao;

import java.util.List;

import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.Notification;
import in.careerscale.apps.ocms.dao.model.NotificationRecipient;
import in.careerscale.apps.ocms.dao.model.NotificationStatus;
import in.careerscale.apps.ocms.dao.model.NotificationTemplate;
import in.careerscale.apps.ocms.dao.model.OrgType;
import in.careerscale.apps.ocms.dao.model.RoleMaster;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class NotificationRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save(Notification notification) {
		entityManager.persist(notification);
	}
	
	
	public CaseMaster getCaseMaster(Integer id) {
		return entityManager.find(CaseMaster.class, id);
	}

	public void save(CaseMaster caseMaster) {
		entityManager.persist(caseMaster);
	}
	

	public NotificationRecipient getNotificationRecipient(Integer id) {
		return entityManager.find(NotificationRecipient.class, id);

	}
	public void save(NotificationRecipient notificationRecipient) {
		entityManager.persist(notificationRecipient);
	}
	
	public NotificationStatus getNotificationStatus(Integer id) {
		return entityManager.find(NotificationStatus.class, id);

	}
	public void save(NotificationStatus notificationStatus) {
		entityManager.persist(notificationStatus);
	}
	
	
	
	public NotificationTemplate getNotificationTemplate(Integer id)
	{
		return entityManager.find(NotificationTemplate.class,id);
	}
	public void save(NotificationTemplate notificationTemplate)
	{
		entityManager.persist(notificationTemplate);
	}

	
	@SuppressWarnings("unchecked")
	public List<CaseMaster> getCaseMasters() {
		Query query =entityManager.createQuery("SELECT c FROM CaseMaster c order by c.caseMaster.id, c.id"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<NotificationRecipient> getNotificationRecipients() {
		Query query =entityManager.createQuery("SELECT h FROM NotificationRecipient h order by h.notificationRecipient.id asc, h.id asc"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<NotificationStatus> getNotificationStatuss() {
		Query query =entityManager.createQuery("SELECT o FROM NotificationStatus o order by o.notificationStatus.id asc, o.id asc"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<NotificationTemplate> getNotificationTemplates() {
		Query query =entityManager.createQuery("SELECT r FROM NotificationTemplate r order by r.notificationTemplate.id asc, r.id asc"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
	}
	
	public Object getById(Class classObject, Object id){
		return entityManager.find(classObject, id);
	}

	
}
