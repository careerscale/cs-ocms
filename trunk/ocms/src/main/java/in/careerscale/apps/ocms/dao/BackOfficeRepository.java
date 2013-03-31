package in.careerscale.apps.ocms.dao;





import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.OrgType;


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
	
	public void delete(CaseType caseType) {
		//String query = "DELETE FROM CASE_TYPE where name = {:name}";
		//entityManager.createNativeQuery(query).setParameter(1, caseType.getName()).executeUpdate();
		caseType = entityManager.find(CaseType.class, caseType.getId());
		entityManager.remove(caseType);
	}
	
	
	 public HelpCategoryType getHelpCategoryType(Integer id){
	        return entityManager.find(HelpCategoryType.class, id);
	        
	    }
	     public void save(HelpCategoryType helpCategoryType){
	         entityManager.persist(helpCategoryType);
	     }


		public CaseType update(CaseType caseType)
		{
			CaseType caseType1 = entityManager.find(CaseType.class, caseType.getId());
			if(caseType.getName()!=null){
				caseType1.setName(caseType.getName());
			}else{
				caseType1.setDescription(caseType.getDescription());
			}
			return entityManager.merge(caseType1);
		}
	    
	
		public CaseStatusMaster getCaseStatusMaster(Integer id){
	        return entityManager.find(CaseStatusMaster.class, id);
	        
	    }
	     public void save(CaseStatusMaster caseStatusMaster){
	         entityManager.persist(caseStatusMaster);
	     }
	     
	     public OrgType getOrgType(Integer id){
		        return entityManager.find(OrgType.class, id);
		        
		    }
		     public void save(OrgType orgType){
		         entityManager.persist(orgType);
		     }
	
}
