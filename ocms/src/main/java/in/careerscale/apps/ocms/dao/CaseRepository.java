package in.careerscale.apps.ocms.dao;

import in.careerscale.apps.ocms.dao.model.Address;
import in.careerscale.apps.ocms.dao.model.CaseApprovalHistory;
import in.careerscale.apps.ocms.dao.model.CaseArtifact;
import in.careerscale.apps.ocms.dao.model.CaseDiscussion;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.CaseTypeApprover;
import in.careerscale.apps.ocms.dao.model.City;
import in.careerscale.apps.ocms.dao.model.Country;
import in.careerscale.apps.ocms.dao.model.DocumentType;
import in.careerscale.apps.ocms.dao.model.Fund;
import in.careerscale.apps.ocms.dao.model.Organization;
import in.careerscale.apps.ocms.dao.model.State;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class CaseRepository
{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void registerCase(CaseMaster casemaster)
	{
		entityManager.persist(casemaster);
		entityManager.flush();
	}

	public void save(Address address)
	{
		entityManager.persist(address);
		entityManager.flush();
	}

	public void save(City city)
	{
		entityManager.persist(city);
		entityManager.flush();
	}

	public City getCity(Integer id)
	{
		return entityManager.find(City.class, id);

	}

	public State getState(Integer id)
	{
		return entityManager.find(State.class, id);

	}

	public Country getCountry(Integer id)
	{
		return entityManager.find(Country.class, id);

	}

	public Address getAddress(Integer id)
	{
		return entityManager.find(Address.class, id);

	}

	@SuppressWarnings("unchecked")
	public City getCityies1()
	{
		Query query = entityManager.createQuery("SELECT c FROM City c order by c.city.id,c.id");
		return (City) query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public Address getAddresses()
	{
		Query query = entityManager.createQuery("SELECT a FROM Address a order by a.address.id,a.id");
		return (Address) query.getResultList();

	}

	/*
	 * public City getCities(Integer cityId) { City city = entityManager.find(City.class, cityId); Query
	 * query=entityManager.createQuery("select c from City c where c.city.id="+ cityId); return (City)
	 * query.getResultList(); }
	 */

	@SuppressWarnings("unchecked")
	public List<DocumentType> getDocumentTypes(Integer caseTypeId)
	{
		List<Integer> caseTypes = new ArrayList<Integer>();

		CaseType caseType = entityManager.find(CaseType.class, caseTypeId);
		while (caseType != null)
		{
			caseTypes.add(caseType.getId());
			caseType = caseType.getCaseType();
		}
		Query query = entityManager.createQuery("select dt from DocumentType dt where dt.caseType.id in (:ids)");
		query.setParameter("ids", caseTypes);
		return query.getResultList();

	}

	/*
	 * @SuppressWarnings("unchecked") public City getCityId(Integer cityId) {
	 * 
	 * 
	 * City city = entityManager.find(City.class, cityId);
	 * 
	 * Query query = entityManager .createQuery("select ct from Address ct where ct.city.id in (:ids)");
	 * 
	 * return (Address) query.getResultList();
	 * 
	 * }
	 */
	public Object getById(Class classObject, Object id)
	{
		return entityManager.find(classObject, id);
	}

	public void save(CaseArtifact artifact)
	{
		entityManager.persist(artifact);
		entityManager.flush();

	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<CaseMaster> getCasesToBeActedUpon(Integer userId)
	{
		Query query = entityManager
				.createNativeQuery(
						"SELECT cm.* FROM Case_Master cm,Case_Type_Approver ct where cm.case_type_id = ct.case_type  and ct.login_id=:userId order by cm.id desc"	
						, CaseMaster.class);
				//Query("SELECT cm FROM CaseMaster cm,CaseTypeApprover ct where cm.caseType.id = ct.caseType.id  and ct.loginMaster.id=:userId order by cm.id desc");
		query.setParameter("userId", userId);
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<CaseMaster> getMyCases(Integer userId)
	{
		Query query = entityManager
				.createQuery("SELECT cm FROM CaseMaster cm where cm.createdBy.id = :userId or cm.updatedBy.id = :userId order by cm.id desc");
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<CaseMaster> getInterestedCases(Integer userId)
	{
		// Query query =
		// entityManager.createQuery("SELECT cm FROM CaseMaster cm inner join  cm.caseType ct  where cm.caseType.name = cm. order by cm.id desc");
		Query query = entityManager
				.createQuery("SELECT cm FROM CaseMaster cm where  cm.caseType.id in (select distinct(cm1.caseType.id) from CaseMaster cm1 where cm1.createdBy.id = :userId or cm1.updatedBy.id = :userId)   and  (cm.createdBy.id <> :userId or cm.updatedBy.id <> :userId ) order by cm.id desc");
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
	

	public CaseMaster getCase(Integer id)
	{
		return entityManager.find(CaseMaster.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CaseArtifact> getCaseArtifacts(Integer caseId)
	{
		Query query = entityManager.createQuery("SELECT ca FROM CaseArtifact ca where  ca.caseMaster.id=:id");
		query.setParameter("id", caseId);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<CaseApprovalHistory> getCaseApprovalHistory(Integer caseId)
	{
		Query query = entityManager.createQuery("SELECT ca FROM CaseApprovalHistory ca where  ca.caseMaster.id=:id order by ca.id desc");
		query.setParameter("id", caseId);
		return query.getResultList();

	}
	
//	@SuppressWarnings("unchecked")
//	public List<CaseApprovalHistory> getCaseHistoryFromCaseApprovalHistory(Integer caseId, Integer userId)
//	{
//		Query query = entityManager.createQuery("SELECT ca FROM CaseApprovalHistory ca where  ca.caseMaster.id=:id and ca.loginMaster.id=:userId order by ca.id desc	");
//		query.setParameter("id", caseId);
//		query.setParameter("userId", userId);
//		
//		return query.getResultList();
//
//	}

	public void save(CaseApprovalHistory caseAprovalHistory)
	{
		entityManager.persist(caseAprovalHistory);
		entityManager.flush();

	}

	@SuppressWarnings("unchecked")
	public List<CaseTypeApprover> getCaseApproverList(Integer id, Organization org)
	{
		Query query = entityManager
				.createQuery("SELECT ct FROM CaseTypeApprover ct where  ct.caseType.id=:id and organization= :org");
		query.setParameter("id", id);
		query.setParameter("org", org);
		return query.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<CaseDiscussion> getCaseDiscussions(Integer id)
	{
		Query query = entityManager
				.createQuery("SELECT cd FROM CaseDiscussion cd where  cd.caseMaster.id=:id order by cd.id desc");
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public void saveCaseDiscussion(CaseDiscussion caseDiscussion)
	{
		entityManager.persist(caseDiscussion);
		entityManager.flush();

	}
	
	@SuppressWarnings("unchecked")
	public List<Fund> getFundsHistory(Integer id)
	{
		Query query = entityManager
				.createQuery("SELECT cd FROM Fund cd where  cd.caseMaster.id=:id order by cd.id desc");
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public void saveFund(Fund fund)
	{
		entityManager.persist(fund);
		entityManager.flush();

	}
	
	public void updateFund(Fund fund)
	{
		entityManager.merge(fund);
		entityManager.flush();

	}
	
	
	@SuppressWarnings("unchecked")
	public List<CaseApprovalHistory> getApprovalCountFromCaseApprovalHistory(Integer caseId)
	{
		Query query = entityManager.createQuery("SELECT ca FROM CaseApprovalHistory ca where  ca.caseMaster.id=:id order by ca.id desc");
		query.setParameter("id", caseId);
		return query.getResultList();

	}

	public int getApprovedCount()
	{
		
		
		// TODO write your logic here.
		return 0;
	}

	public void update(CaseMaster caseMaster)
	{
		entityManager.merge(caseMaster);
		entityManager.flush();

	}
}
