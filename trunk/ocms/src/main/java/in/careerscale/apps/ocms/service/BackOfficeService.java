package in.careerscale.apps.ocms.service;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder.Case;

import in.careerscale.apps.ocms.dao.BackOfficeRepository;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.EmailTemplate;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.ModuleMaster;
import in.careerscale.apps.ocms.dao.model.OrgType;
import in.careerscale.apps.ocms.dao.model.RoleMaster;
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

	public void addCaseType(BOBean boBean) throws ApplicationException {

		try {
			backofficeRepository.save(new CaseType(boBean.getName(), boBean
					.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

	public void updateCaseType(BOBean boBean) throws ApplicationException {
		
		try {
			backofficeRepository.update(new CaseType(
					boBean.getId(), boBean.getName(), boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

	public void deleteCaseType(BOBean boBean) throws ApplicationException {

		try {
			backofficeRepository.delete(new CaseType(boBean.getId(), boBean
					.getName(), boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	

	public void addHelpCategoryType(BOBean boBean)
			throws ApplicationException {

		try {
			backofficeRepository.save(new HelpCategoryType(boBean.getName(), boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	public void updateHelpCategoryType(BOBean boBean) throws ApplicationException {
		
		try {
			backofficeRepository.update(new HelpCategoryType(
					boBean.getId(), boBean.getName(), boBean
							.getDescription()));		

		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
		
	}
	
	public void deleteHelpCategoryType(BOBean boBean) throws ApplicationException {

		try {
			backofficeRepository.delete(new HelpCategoryType(boBean.getId(), boBean
					.getName(),boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	

	

	public void addOrgType(BOBean boBean)
			throws ApplicationException {

		try {
			backofficeRepository.save(new OrgType(boBean.getName(),boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	public void updateOrgType(BOBean boBean) throws ApplicationException {		
		try {
		backofficeRepository.update(new OrgType(
					boBean.getId(), boBean.getName(), boBean
							.getDescription()));
		
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
		
	}
	public void deleteOrgType(BOBean boBean) throws ApplicationException {

		try {
			backofficeRepository.delete(new OrgType(boBean.getId(), boBean
					.getName(), boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	public void addRoleType(BOBean boBean)
			throws ApplicationException {

		try {
			backofficeRepository.save(new RoleMaster(boBean.getName(),boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	public void deleteRoleType(BOBean boBean) throws ApplicationException {

		try {
			backofficeRepository.delete(new RoleMaster(boBean.getId(), boBean
					.getName(), boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	public void updateRoleType(BOBean boBean) throws ApplicationException {		
		try {
		backofficeRepository.update(new RoleMaster(
					boBean.getId(), boBean.getName(), boBean
							.getDescription()));
		
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
		
	}
	
	
	public void addCaseStatusMaster(BOBean boBean)
			throws ApplicationException {

		try {
			backofficeRepository.save(new CaseStatusMaster(boBean
					.getName(), boBean
					.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	/*public void addRoleMaster(BOBean boBean)
			throws ApplicationException {

		try {
			backofficeRepository.save(new RoleMaster(boBean.getName(),boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}*/

	public void addModuleMaster(BOBean boBean)
			throws ApplicationException {

		try {
			backofficeRepository.save(new ModuleMaster(boBean.getName(),boBean.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	public void addEmailTemplate(BOBean emailTemplate)
			throws ApplicationException {

		try {
			backofficeRepository.save(new EmailTemplate(emailTemplate.getName(),emailTemplate.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}

}
