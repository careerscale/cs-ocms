package in.careerscale.apps.ocms.dao;

import java.util.ArrayList;
import java.util.List;

import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.DocumentType;
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

	public void registerCase(CaseMaster casemaster) {
		entityManager.persist(casemaster);

	}

	@SuppressWarnings("unchecked")
	public List<DocumentType> getDocumentTypes(Integer caseTypeId) {
		List<Integer> caseTypes = new ArrayList<Integer>();

		CaseType caseType = entityManager.find(CaseType.class, caseTypeId);
		while (caseType != null) {
			caseTypes.add(caseType.getId());
			caseType = caseType.getCaseType();
		}
		Query query = entityManager
				.createQuery("select dt from DocumentType dt where dt.caseType.id in (:ids)");
		query.setParameter("ids", caseTypes);
		return query.getResultList();

	}

}
