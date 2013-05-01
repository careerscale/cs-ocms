package in.careerscale.apps.ocms.dao;

import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.EmailTemplate;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.ModuleMaster;
import in.careerscale.apps.ocms.dao.model.OrgType;
import in.careerscale.apps.ocms.dao.model.RoleMaster;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class BackOfficeRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public CaseType getCaseType(Integer id) {
		return entityManager.find(CaseType.class, id);
	}

	public void save(CaseType caseType) {
		entityManager.persist(caseType);

	}

	public void delete(CaseType caseType) {
		// String query = "DELETE FROM CASE_TYPE where name = {:name}";
		// entityManager.createNativeQuery(query).setParameter(1,
		// caseType.getName()).executeUpdate();
		caseType = entityManager.find(CaseType.class, caseType.getId());
		entityManager.remove(caseType);
		entityManager.flush();
	}
	public void update(CaseType caseType) {
		CaseType caseType1 = entityManager.find(CaseType.class,
				caseType.getId());
		if (caseType.getName() != null) {
			caseType1.setName(caseType.getName());
		} else {
			caseType1.setDescription(caseType.getDescription());
		}
		entityManager.merge(caseType1);
		entityManager.flush();
	}
	
	public RoleMaster getRoleMaster(Integer id) {
		return entityManager.find(RoleMaster.class, id);

	}

	public void save(RoleMaster roleType) {
		entityManager.persist(roleType);
	}
	
	public void delete(RoleMaster roleType) {
		// String query = "DELETE FROM CASE_TYPE where name = {:name}";
		// entityManager.createNativeQuery(query).setParameter(1,
		// caseType.getName()).executeUpdate();
		roleType = entityManager.find(RoleMaster.class, roleType.getId());
		entityManager.remove(roleType);
		entityManager.flush();
	}
	
	public void update(RoleMaster roleType) {
		RoleMaster roleType1 = entityManager.find(RoleMaster.class,
				roleType.getId());
		if (roleType.getRoleName() != null) {
			roleType1.setRoleName(roleType.getRoleName());
		} else {
			roleType1.setDescription(roleType.getDescription());
		}
		entityManager.merge(roleType1);
		entityManager.flush();
	}
	public OrgType getOrgType(Integer id) {
		return entityManager.find(OrgType.class, id);

	}
	

	public void save(OrgType orgType) {
		entityManager.persist(orgType);
	}
	public void delete(OrgType orgType) {
		// String query = "DELETE FROM CASE_TYPE where name = {:name}";
		// entityManager.createNativeQuery(query).setParameter(1,
		// caseType.getName()).executeUpdate();
		orgType = entityManager.find(OrgType.class, orgType.getId());
		entityManager.remove(orgType);
		entityManager.flush();
	}
	
	
	
	public void update(OrgType orgType) {
		OrgType orgType1 = entityManager.find(OrgType.class,
				orgType.getId());
		if (orgType.getName() != null) {
			orgType1.setName(orgType.getName());
		} else {
			orgType1.setDescription(orgType.getDescription());
		}
		entityManager.merge(orgType1);
		entityManager.flush();
	}
	
	public HelpCategoryType getHelpCategoryType(Integer id) {
		return entityManager.find(HelpCategoryType.class, id);

	}

	public void save(HelpCategoryType helpCategoryType) {
		entityManager.persist(helpCategoryType);
	}
	
	public void delete(HelpCategoryType helpCategoryType) {
		// String query = "DELETE FROM CASE_TYPE where name = {:name}";
		// entityManager.createNativeQuery(query).setParameter(1,
		// caseType.getName()).executeUpdate();
		helpCategoryType = entityManager.find(HelpCategoryType.class, helpCategoryType.getId());
		entityManager.remove(helpCategoryType);
		entityManager.flush();
	}

	public void update(HelpCategoryType helpCategoryType) {
		HelpCategoryType helpCategoryType1 = entityManager.find(HelpCategoryType.class,
				helpCategoryType.getId());
		if (helpCategoryType.getCategoryName() != null) {
			helpCategoryType1.setCategoryName(helpCategoryType.getCategoryName());
		} else {
			helpCategoryType1.setDescription(helpCategoryType.getDescription());
		}
		entityManager.merge(helpCategoryType1);
		entityManager.flush();
		
	}

	
	public CaseStatusMaster getCaseStatusMaster(Integer id) {
		return entityManager.find(CaseStatusMaster.class, id);

	}

	public void save(CaseStatusMaster caseStatusMaster) {
		entityManager.persist(caseStatusMaster);
	}

	public void update(CaseStatusMaster caseStatusMaster)
	{
		CaseStatusMaster caseStatusMaster1=entityManager.find(CaseStatusMaster.class,caseStatusMaster.getId());
		if(caseStatusMaster.getCaseStatusName() != null)
		{
			caseStatusMaster1.setCaseStatusName(caseStatusMaster.getCaseStatusName());
		}else
		{
			caseStatusMaster1.setCaseStatusDescription(caseStatusMaster.getCaseStatusDescription());
		}
		entityManager.merge(caseStatusMaster1);
		entityManager.flush();
	}
	public void delete(CaseStatusMaster caseStatusMaster) {
		// String query = "DELETE FROM CASE_TYPE where name = {:name}";
		// entityManager.createNativeQuery(query).setParameter(1,
		// caseType.getName()).executeUpdate();
		caseStatusMaster = entityManager.find(CaseStatusMaster.class, caseStatusMaster.getId());
		entityManager.remove(caseStatusMaster);
		entityManager.flush();
	}
	
	

	public ModuleMaster getModuleMaster(Integer id) {
		return entityManager.find(ModuleMaster.class, id);

	}

	public void save(ModuleMaster moduleMaster) {
		entityManager.persist(moduleMaster);
	}

	public EmailTemplate getEmailTemplate(Integer id) {
		return entityManager.find(EmailTemplate.class, id);

	}

	public void save(EmailTemplate emailTemplate) {
		entityManager.persist(emailTemplate);
	}

}
