package in.careerscale.apps.ocms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.careerscale.apps.ocms.dao.MasterDataRepository;
import in.careerscale.apps.ocms.dao.model.CaseStatusMaster;
import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.dao.model.OrgType;
import in.careerscale.apps.ocms.dao.model.RoleMaster;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.service.model.MasterType;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("masterDataService")
public class MasterDataService {

	@Autowired
	private MasterDataRepository repository;

	@Autowired
	private EmailSender emailService;

	public void addCaseType(BOBean caseType) throws ApplicationException {

		try {
			repository.save(new CaseType(caseType.getName(), caseType
					.getDescription()));
		} catch (PersistenceException pe) {
			throw new ApplicationException(pe.getMessage());
		}
	}
	
	 public void addHelpCategoryType(BOBean helpCategoryType) throws ApplicationException {

	        try {
	        	repository.save(new HelpCategoryType(helpCategoryType.getName(), helpCategoryType.getDescription()));
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
	    }
	 public void addOrgType(BOBean orgType) throws ApplicationException {

	        try {
	        	repository.save(new OrgType(orgType.getName(), orgType.getDescription()));
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
	    }
	 public void addRoleType(BOBean roleType) throws ApplicationException {

	        try {
	        	repository.save(new RoleMaster(roleType.getName(), roleType.getDescription()));
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
	    }
	 public void addCaseStatus(BOBean caseStatusMaster) throws ApplicationException {

	        try {
	        	repository.save(new CaseStatusMaster(caseStatusMaster.getName(), caseStatusMaster.getDescription()));
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
	    }
	 
	 
	 
	 public Map<Integer, String> getCaseTypes() throws ApplicationException{
		 Map<Integer, String> caseTypes = new HashMap<Integer, String>();		
		   try {
			   List<CaseType> casetypesList =repository.getCaseTypes();
			   
			   for (CaseType caseType : casetypesList) {
				   caseTypes.put(caseType.getId(),caseType.getName());
				
			}
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return caseTypes;
	 }
	 
	 public Map<Integer, String> getCaseStatus() throws ApplicationException{
		 Map<Integer, String> caseStatus = new HashMap<Integer, String>();		
		   try {
			   List<CaseStatusMaster> caseStatusList =repository.getCaseStatusMasters();
			   
			   for (CaseStatusMaster caseStatuses : caseStatusList) {
				   caseStatus.put(caseStatuses.getId(),caseStatuses.getCaseStatusName());
				
			}
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return caseStatus;
	 }
	 
	 
	 public Map<Integer, String> getHelpCategoryTypes() throws ApplicationException{
		 Map<Integer, String> helpCategoryTypes = new HashMap<Integer, String>();		
		   try {
			   List<HelpCategoryType> helpCategoryTypeList =repository.getHelpCategoryTypes();
			   
			   for (HelpCategoryType helpCategoryType : helpCategoryTypeList) {
				   helpCategoryTypes.put(helpCategoryType.getId(), helpCategoryType.getCategoryName());
				
			}
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return helpCategoryTypes;
	 }

	 public Map<Integer, String> getOrgTypes() throws ApplicationException{
		 Map<Integer, String> orgTypes = new HashMap<Integer, String>();		
		   try {
			   List<OrgType> orgtypesList =repository.getOrgTypes();
			   
			   for (OrgType orgType : orgtypesList) {
				   orgTypes.put(orgType.getId(), orgType.getName());
				
			}
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return orgTypes;
	 }
	 public Map<Integer, String> getRoleTypes() throws ApplicationException{
		 Map<Integer, String> roleTypes = new HashMap<Integer, String>();		
		   try {
			   List<RoleMaster> roletypesList =repository.getRoleTypes();
			   
			   for (RoleMaster roleType : roletypesList) {
				   roleTypes.put(roleType.getId(), roleType.getRoleName());
				
			}
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return roleTypes;
	 }
	 
	 

	 public List<CaseType> getCaseTypesList() throws ApplicationException{
		 List<CaseType> casetypesList  = null;  
		 try {
			   casetypesList =repository.getCaseTypes();
			
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return casetypesList ;
	 }
	 public List<HelpCategoryType> getHelpCategoryTypeList() throws ApplicationException{
		 List<HelpCategoryType> helpCategoryTypeList  = null;  
		 try {
			 helpCategoryTypeList =repository.getHelpCategoryTypes();
			
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return helpCategoryTypeList ;
	 }
	 public List<OrgType> getOrgTypesList() throws ApplicationException{
		 List<OrgType> orgtypesList  = null;  
		 try {
			 orgtypesList =repository.getOrgTypes();
			
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return orgtypesList ;
	 }
	 public List<RoleMaster> getRoleTypesList() throws ApplicationException{
		 List<RoleMaster> roletypesList  = null;  
		 try {
			 roletypesList =repository.getRoleTypes();
			
	        } catch (PersistenceException pe) {
	            throw new ApplicationException(pe.getMessage());
	        }
		   
		   return roletypesList ;
	 }
	 
	 public List<CaseStatusMaster> getCaseStatusList() throws ApplicationException{
		 List<CaseStatusMaster> caseStatusList=null;
		 try{
			 caseStatusList=repository.getCaseStatusMasters();
		 }catch(PersistenceException pe)
		 {
			 throw new ApplicationException(pe.getMessage());
		 }
		 return caseStatusList;
	 }

		
	 
	 public List<MasterType> getHelpTypes() throws ApplicationException {
			 List<MasterType> helpTypes = new ArrayList<MasterType>();
			   try {
				   List<HelpCategoryType> helpTypesList =repository.getHelpCategoryTypes();
				   
				   for (HelpCategoryType helpType : helpTypesList) {
					   String helpTypeName = helpType.getHelpCategoryType()!=null?helpType.getHelpCategoryType().getCategoryName() + " - " + helpType.getCategoryName() : helpType.getCategoryName();
						 
					   helpTypes.add(new MasterType(helpType.getId(), helpTypeName));
					
				}
		        } catch (PersistenceException pe) {
		            throw new ApplicationException(pe.getMessage());
		        }
			   
			   return helpTypes;
			
		}
		
		
		
		public List<MasterType> getCaseTypes1() throws ApplicationException{
				
			 List<MasterType> caseTypes = new ArrayList<MasterType>();
			   try {
				   List<CaseType> casetypesList =repository.getCaseTypes();
				   
				   for (CaseType caseType : casetypesList) {
					   String caseTypeName = caseType.getCaseType()!=null?caseType.getCaseType().getName() + " - " + caseType.getName() : caseType.getName();
					   caseTypes.add(new MasterType(caseType.getId(), caseTypeName));
					
				}
		        } catch (PersistenceException pe) {
		            throw new ApplicationException(pe.getMessage());
		        }
			   
			   return caseTypes;
		 }
		 
		 

		 
		 

		 public List<MasterType> getHelpCategoryType1() throws ApplicationException{
				
			 List<MasterType> helpCategoryTypes = new ArrayList<MasterType>();
			   try {
				   List<HelpCategoryType> helpCategoryTypeList =repository.getHelpCategoryTypes();
				   
				   for (HelpCategoryType helpCategoryType : helpCategoryTypeList) {
					   String helpCategoryTypeName = helpCategoryType.getHelpCategoryType()!=null?helpCategoryType.getHelpCategoryType().getCategoryName() + " - " + helpCategoryType.getCategoryName() : helpCategoryType.getCategoryName();
					   helpCategoryTypes.add(new MasterType(helpCategoryType.getId(), helpCategoryTypeName));
					
				}
		        } catch (PersistenceException pe) {
		            throw new ApplicationException(pe.getMessage());
		        }
			   
			   return helpCategoryTypes;
		 }
		 public List<MasterType> getOrgCategoryType1() throws ApplicationException{
				
			 List<MasterType> orgTypes = new ArrayList<MasterType>();
			   try {
				   List<OrgType> orgTypeList =repository.getOrgTypes();
				   
				   for (OrgType orgType : orgTypeList) {
					   String orgTypeName = orgType.getOrgType()!=null?orgType.getOrgType().getName() + " - " + orgType.getName() : orgType.getName();
					   orgTypes.add(new MasterType(orgType.getId(), orgTypeName));
					
				}
		        } catch (PersistenceException pe) {
		            throw new ApplicationException(pe.getMessage());
		        }
			   
			   return orgTypes;
		 }

public List<MasterType> getCaseStatus1() throws ApplicationException{
	
	 List<MasterType> caseStatus = new ArrayList<MasterType>();
	   try {
		   List<CaseStatusMaster> caseStatusList =repository.getCaseStatusMasters();
		   
		   for (CaseStatusMaster caseStatus2 : caseStatusList) {
			   String caseStatusName = caseStatus2.getCaseStatusMaster()!=null?caseStatus2.getCaseStatusMaster().getCaseStatusName()+ " - " + caseStatus2.getCaseStatusName() : caseStatus2.getCaseStatusName();
			   caseStatus.add(new MasterType(caseStatus2.getId(), caseStatusName));
			
		}
       } catch (PersistenceException pe) {
           throw new ApplicationException(pe.getMessage());
       }
	   
	   return caseStatus;
}
}
