package in.careerscale.apps.ocms.dao;





import java.util.List;

import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;


import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class UserRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
		
	}

	
}
