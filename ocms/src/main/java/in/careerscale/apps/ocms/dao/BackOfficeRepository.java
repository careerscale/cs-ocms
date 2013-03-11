package in.careerscale.apps.ocms.dao;





import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;


import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class BackOfficeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public CaseType getCaseType(Integer id){
		return entityManager.find(CaseType.class, id);
	}
	

	public void save(CaseType caseType) {
		entityManager.persist(caseType);
		
	}
	
	 public HelpCategoryType getHelpCategoryType(Integer id){
	        return entityManager.find(HelpCategoryType.class, id);
	        
	    }
	     public void save(HelpCategoryType helpCategoryType){
	         entityManager.persist(helpCategoryType);
	     }
	    
	

	
}
