package in.careerscale.apps.ocms.dao;

import java.util.List;

import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.OrgType;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class MasterDataRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public CaseType getCaseType(Integer id) {
		return entityManager.find(CaseType.class, id);
	}

	public void save(CaseType caseType) {
		entityManager.persist(caseType);
	}

	public HelpCategoryType getHelpCategoryType(Integer id) {
		return entityManager.find(HelpCategoryType.class, id);

	}
	public OrgType getOrgType(Integer id) {
		return entityManager.find(OrgType.class, id);

	}

	public void save(HelpCategoryType helpCategoryType) {
		entityManager.persist(helpCategoryType);
	}
	public void save(OrgType orgType) {
		entityManager.persist(orgType);
	}

	
	@SuppressWarnings("unchecked")
	public List<CaseType> getCaseTypes() {
		Query query =entityManager.createQuery("SELECT c FROM CaseType c order by c.caseType.id, c.id"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<HelpCategoryType> getHelpCategoryTypes() {
		Query query =entityManager.createQuery("SELECT h FROM HelpCategoryType h order by h.helpCategoryType.id asc, h.id asc"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<OrgType> getOrgTypes() {
		Query query =entityManager.createQuery("SELECT o FROM OrgType o order by o.orgType.id asc, o.id asc"); // AS c WHERE c.caseType is NULL
		return query.getResultList();
	}
	
	public Object getById(Class classObject, Object id){
		return entityManager.find(classObject, id);
	}

	
}
