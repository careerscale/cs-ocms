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
	}
	public void delete(OrgType orgType) {
		// String query = "DELETE FROM CASE_TYPE where name = {:name}";
		// entityManager.createNativeQuery(query).setParameter(1,
		// caseType.getName()).executeUpdate();
		orgType = entityManager.find(OrgType.class, orgType.getId());
		entityManager.remove(orgType);
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

	public OrgType getOrgType(Integer id) {
		return entityManager.find(OrgType.class, id);

	}

	public void save(OrgType orgType) {
		entityManager.persist(orgType);
	}

	public RoleMaster getRoleMaster(Integer id) {
		return entityManager.find(RoleMaster.class, id);

	}

	public void save(RoleMaster roleMaster) {
		entityManager.persist(roleMaster);
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
