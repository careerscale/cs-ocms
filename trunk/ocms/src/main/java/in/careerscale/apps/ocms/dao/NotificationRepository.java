package in.careerscale.apps.ocms.dao;

import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.Notification;
import in.careerscale.apps.ocms.dao.model.NotificationStatus;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	


	
	public NotificationStatus getNotificationStatus(Integer id) {
		return entityManager.find(NotificationStatus.class, id);

	}
	public void save(NotificationStatus notificationStatus) {
		entityManager.persist(notificationStatus);
	}
	
	
	

	
	@SuppressWarnings("unchecked")
	public List<CaseMaster> getCaseMasters() {
		Query query =entityManager.createQuery("SELECT c FROM CaseMaster c order by c.caseMaster.id, c.id"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<NotificationStatus> getNotificationStatuss() {
		Query query =entityManager.createQuery("SELECT o FROM NotificationStatus o order by o.notificationStatus.id asc, o.id asc"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
	}

	
	public Object getById(Class classObject, Object id){
		return entityManager.find(classObject, id);
	}

	
}
