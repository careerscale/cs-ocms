package in.careerscale.apps.ocms.dao;

import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.City;
import in.careerscale.apps.ocms.dao.model.Country;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.OrgType;
import in.careerscale.apps.ocms.dao.model.RoleMaster;
import in.careerscale.apps.ocms.dao.model.State;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public void save(HelpCategoryType helpCategoryType) {
		entityManager.persist(helpCategoryType);
	}
	
	public OrgType getOrgType(Integer id) {
		return entityManager.find(OrgType.class, id);

	}
	public void save(OrgType orgType) {
		entityManager.persist(orgType);
	}
	
	
	public RoleMaster getRoleType(Integer id)
	{
		return entityManager.find(RoleMaster.class,id);
	}
	public void save(RoleMaster roleType)
	{
		entityManager.persist(roleType);
	}
	
	public CaseStatusMaster getCaseStatus(Integer id)
	{
		return entityManager.find(CaseStatusMaster.class, id);
	}
	public void save(CaseStatusMaster caseStatusMaster)
	{
		entityManager.persist(caseStatusMaster);
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
	@SuppressWarnings("unchecked")
	public List<RoleMaster> getRoleTypes() {
		Query query = entityManager.createQuery("SELECT r FROM RoleMaster r order by  r.id asc"); // AS c WHERE
																									// c.caseType is
																									// NULL
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CaseStatusMaster> getCaseStatusMasters()
	{
		Query query = entityManager.createQuery("select cs from CaseStatusMaster cs order by cs.id asc");
		return query.getResultList();
		
	}
	
	public Object getById(Class classObject, Object id){
		return entityManager.find(classObject, id);
	}

	public List<Country> getCountries() {
		Query query=entityManager.createQuery("select c from Country c");
		return query.getResultList();
	}
	
	public List<State> getStates(Integer countryId) {
		Query query=entityManager.createQuery("select s from State s where s.country.id = " +countryId);
		return query.getResultList();
	}
	
	public List<City> getCities(Integer stateId) {
		Query query=entityManager.createQuery("select c from City c where c.state.id="+ stateId);
		return query.getResultList();
	}

	
}
