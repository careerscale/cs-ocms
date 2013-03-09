package in.careerscale.apps.ocms.dao;





import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;


import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class BackOfficeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	

	public void save(CaseType caseType) {
		entityManager.persist(caseType);
	}

	
}
