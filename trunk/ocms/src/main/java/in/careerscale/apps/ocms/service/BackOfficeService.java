package in.careerscale.apps.ocms.service;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder.Case;

import in.careerscale.apps.ocms.dao.BackOfficeRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.OrgType;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("backOfficeService")
public class BackOfficeService {

	@Autowired
	private BackOfficeRepository backofficeRepository;

	@Autowired
	private EmailSender emailService;

	public void addCaseType(BOBean caseType) throws ApplicationException {

		try {
			backofficeRepository.save(new CaseType(caseType.getName(), caseType
					.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

	public BOBean updateCaseType(BOBean caseType) throws ApplicationException {
		BOBean boBean = null;
		try {
			CaseType caseType2 = backofficeRepository.update(new CaseType(
					caseType.getId(), caseType.getName(), caseType
							.getDescription()));
			boBean = new BOBean();
			boBean.setId(caseType2.getId());
			boBean.setName(caseType2.getName());
			boBean.setDescription(caseType2.getDescription());

		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
		return boBean;
	}

	public void deleteCaseType(BOBean caseType) throws ApplicationException {

		try {
			backofficeRepository.delete(new CaseType(caseType.getId(), caseType
					.getName(), caseType.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

	public void addHelpCategoryType(BOBean helpCategoryType)
			throws ApplicationException {

		try {
			backofficeRepository.save(new HelpCategoryType(helpCategoryType
					.getName(), helpCategoryType.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

	public void addCaseStatusMaster(BOBean caseStatusMaster)
			throws ApplicationException {

		try {
			backofficeRepository.save(new CaseStatusMaster(caseStatusMaster
					.getCaseStatusName(), caseStatusMaster
					.getCaseStatusDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

	public void addOrgType(BOBean orgType)
			throws ApplicationException {

		try {
			backofficeRepository.save(new OrgType(orgType.getName(),orgType.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

}
