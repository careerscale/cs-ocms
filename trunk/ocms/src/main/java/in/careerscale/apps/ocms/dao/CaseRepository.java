package in.careerscale.apps.ocms.dao;

import java.util.List;

import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.LoginMaster;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class CaseRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void registerCase(CaseMaster casemaster){
		entityManager.persist(casemaster);
		
	}
	
}
