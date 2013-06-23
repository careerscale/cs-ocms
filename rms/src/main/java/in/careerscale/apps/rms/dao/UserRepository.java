package in.careerscale.apps.rms.dao;





import java.util.List;

import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.SocialNetwork;
import in.careerscale.apps.ocms.dao.model.UserNetwork;


import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class UserRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	@Transactional
	public void save(LoginMaster user) {
		entityManager.persist(user);
	}
	
	public LoginMaster findByUsername(String username) {
		LoginMaster loginMaster = null;
		try {
			loginMaster= entityManager.createNamedQuery(LoginMaster.FIND_BY_USERNAME, LoginMaster.class)
					.setParameter("emailId", username)
					.getSingleResult();
		} catch (PersistenceException e) {
			//User not present in the system, so invalid login most probably.
		}
		
		return loginMaster;
	}
	public void registerUser(LoginMaster user){
		entityManager.persist(user);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CaseType>  getCaseTypes(List<Integer> caseTypes) {
	
		Query query =entityManager.createQuery("SELECT c FROM CaseType c where c.id IN (:id)"); // AS c WHERE c.caseType is NULL
		query.setParameter("id", caseTypes);
		return query.getResultList();
	}

	public void update(LoginMaster dbUser) {
		
		entityManager.merge(dbUser);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<HelpCategoryType> gethelpTypes(List<Integer> helpTypes) {
		Query query =entityManager.createQuery("SELECT h FROM HelpCategoryType h where h.id IN (:id)"); 
		query.setParameter("id", helpTypes);
		return query.getResultList();
	}

	public SocialNetwork getSocialNetwork(Integer id) {
		SocialNetwork network =entityManager.find(SocialNetwork.class, id);
		return network;
	}

	public void save(UserNetwork userNetwork) {
		entityManager.persist(userNetwork);
		entityManager.flush();
	}
**/
	
}
