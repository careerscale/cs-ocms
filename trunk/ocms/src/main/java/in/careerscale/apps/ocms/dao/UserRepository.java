package in.careerscale.apps.ocms.dao;





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

	
}
