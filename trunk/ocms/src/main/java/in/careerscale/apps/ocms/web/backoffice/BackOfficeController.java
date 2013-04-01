package in.careerscale.apps.ocms.web.backoffice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.dao.model.HelpCategoryType;
import in.careerscale.apps.ocms.service.BackOfficeService;
import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured("ROLE_USER")
public class BackOfficeController {

	Log log = LogFactory.getLog(BackOfficeController.class);
	
	@Autowired
	private BackOfficeService backOfficeService;
	
	@Autowired
	MasterDataService masterDataService;
	

	
	@RequestMapping(value = "/backoffice", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Within GET method for /backoffice");

		return "backoffice/index";
	}

	
	@RequestMapping(value = "/backoffice/casetype", method = RequestMethod.GET)	
	public String getCaseTypes(@ModelAttribute(value = "botypeList")  ArrayList<BOBean> lstBean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		
		log.debug("Within GET method for /backoffice/casetype");
		List<CaseType> casetypesList  = null;  
	
		  try{
			  casetypesList = masterDataService.getCaseTypesList();
		        }catch(ApplicationException ae){
		        	log.error(ae);
		        }
		  
		  BOBean boBean = null;
		  Iterator<CaseType> it = casetypesList.iterator();
		  Integer intName = null;
		  CaseType caseType = null;
		  while(it.hasNext()){
			  caseType = (CaseType)it.next();
			  boBean = new BOBean();
			  boBean.setName(caseType.getName());
			  boBean.setDescription(caseType.getDescription());
			  boBean.setId(caseType.getId());
			  lstBean.add(boBean);
		  }
		  return "backoffice/casetype";
		
	}
	
	@RequestMapping(value = "/backoffice/delCaseType", method = RequestMethod.GET)
	public String caseTypeDelete(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/DeleteData");

		
		return "backoffice/casetype";
	}
	

	
	@RequestMapping(value = "/backoffice/casetypeAdd", method = RequestMethod.GET)
	public void caseTypeAdd(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		request.getParameterNames();
		try{
        	backOfficeService.addCaseType(bean);
        	
        }catch(ApplicationException ae){
       		errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
        	
		}
		log.debug("Within GET method for /backoffice/casetypeAdd");

		
	}
	

	@RequestMapping(value = "/backoffice/delCaseType", method = RequestMethod.POST)
	public void caseTypeDelete1(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		
		try{
			if(bean.getName() == null && id != null && id.length() > 0){
				bean = new BOBean();
				bean.setId(Integer.parseInt(id));
			}
        	backOfficeService.deleteCaseType(bean);
        	
        }catch(ApplicationException ae){
       		errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
        	
		}
		log.debug("Within GET method for /backoffice/DeleteData");

		
		//return "backoffice/casetype";
	}

	
	@RequestMapping(value = "/backoffice/updateCaseType", method = RequestMethod.POST)
	public @ResponseBody  String caseTypeUpdate(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		BOBean bean1 = null;
		String id = request.getParameter("id");
		String name = null;
		String description = null;
		boolean boolName = false;
		
				String value = request.getParameter("value");
				String clname = request.getParameter("columnName");
				//String c = request.getParameter("columnId");
				//String r = request.getParameter("rowId");
				//String columnPosition = request.getParameter("columnPosition");
				
		
		try{
			if(value != null && id != null && id.length() > 0){
				bean = new BOBean();
				bean.setId(Integer.parseInt(id));
				if(clname.equalsIgnoreCase("Case Type")){
					bean.setName(value);
					boolName = true;
				}
				else
					bean.setDescription(value);
			}
			bean1 = backOfficeService.updateCaseType(bean);
        	
        }catch(ApplicationException ae){
       		errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
       		
		}
		log.debug("Within GET method for /backoffice/UpdateData");
		if(boolName){
			return bean1.getName();
		}else{
			return bean1.getDescription();
		}
	}

	
	
	
	@RequestMapping(value = "/backoffice/addCaseType", method = RequestMethod.POST)
	public String addCaseType(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		//TODO Validations on server side
		
        try{
        	backOfficeService.addCaseType(bean);
        	
        }catch(ApplicationException ae){
       		
        	//ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
        	errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
        	return "backoffice/casetype";
		}
        return "backoffice/success"; // we need to return next page.
	}
	
	/*@RequestMapping(value = "/backoffice/helptype", method = RequestMethod.GET)
    public String HelpTypeIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/helptype");

        return "backoffice/helptype";
    }*/
	
	
	
	
	
	@RequestMapping(value = "/backoffice/helptype", method = RequestMethod.GET)	
	public String getHelpCategoryType(@ModelAttribute(value = "botypeList")  ArrayList<BOBean> lstBean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		
		log.debug("Within GET method for /backoffice/helptype");
		List<HelpCategoryType> helpCategoryTypeList  = null;  
	
		  try{
			  helpCategoryTypeList = masterDataService.getHelpCategoryTypeList();
		        }catch(ApplicationException ae){
		        	log.error(ae);
		        }
		  
		  BOBean boBean = null;
		  Iterator<HelpCategoryType> it = helpCategoryTypeList.iterator();
		  Integer intName = null;
		  HelpCategoryType helpCategoryType = null;
		  while(it.hasNext()){
			  helpCategoryType = (HelpCategoryType)it.next();
			  boBean = new BOBean();
			  boBean.setName(helpCategoryType.getCategoryName());
			  boBean.setDescription(helpCategoryType.getDescription());
			  boBean.setId(helpCategoryType.getId());
			  lstBean.add(boBean);
		  }
		  return "backoffice/helptype";
		
	}
	

    @RequestMapping(value = "/backoffice/delHelpType", method = RequestMethod.GET)
	public String getHelpCategoryTypeDelete(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/DeleteData");

		
		return "backoffice/helptype";
	}
	

	
	@RequestMapping(value = "/backoffice/addHelpType", method = RequestMethod.GET)
	public void helpCategoryType(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		request.getParameterNames();
		try{
        	backOfficeService.addHelpCategoryType(bean);
        	
        }catch(ApplicationException ae){
       		errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
        	
		}
		log.debug("Within GET method for /backoffice/helptypeAdd");

		
	}
	
	
	@RequestMapping(value = "/backoffice/delHelpType", method = RequestMethod.POST)
	public void deleteHelpCategoryType(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		
		try{
			if(bean.getName() == null && id != null && id.length() > 0){
				bean = new BOBean();
				bean.setId(Integer.parseInt(id));
			}
        	backOfficeService.deleteHelpCategoryType(bean);
        	
        }catch(ApplicationException ae){
       		errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
        	
		}
		log.debug("Within GET method for /backoffice/DeleteData");

		
		//return "backoffice/casetype";
	}

	
	@RequestMapping(value = "/backoffice/updateHelpType", method = RequestMethod.POST)
	public @ResponseBody  String updateHelpCategoryType(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		BOBean bean1 = null;
		String id = request.getParameter("id");
		String name = null;
		String description = null;
		boolean boolName = false;
		
				String value = request.getParameter("value");
				String clname = request.getParameter("columnName");
				//String c = request.getParameter("columnId");
				//String r = request.getParameter("rowId");
				//String columnPosition = request.getParameter("columnPosition");
				
		
		try{
			if(value != null && id != null && id.length() > 0){
				bean = new BOBean();
				bean.setId(Integer.parseInt(id));
				if(clname.equalsIgnoreCase("Help Type")){
					bean.setName(value);
					boolName = true;
				}
				else
					bean.setDescription(value);
			}
			bean1 = backOfficeService.updateHelpCategoryType(bean);
        	
        }catch(ApplicationException ae){
       		errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
       		
		}
		log.debug("Within GET method for /backoffice/UpdateData");
		if(boolName){
			return bean1.getCategoryName();
		}else{
			return bean1.getDescription();
		}
	}

	

	
	
	
	
    
    @RequestMapping(value = "/backoffice/addHelpType", method = RequestMethod.POST)
    public String addHelpCategoryType(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addHelpCategoryType(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/help_category_type";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
    
    @RequestMapping(value = "/backoffice/casestatus", method = RequestMethod.GET)
    public String caseStatusMasterIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/casestatus");

        return "backoffice/casestatus";
    }
    
    @RequestMapping(value = "/backoffice/addCaseStatus", method = RequestMethod.POST)
    public String addCaseStatusMaster(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addCaseStatusMaster(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/casestatus";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
    
   
    @RequestMapping(value = "/backoffice/orgtype", method = RequestMethod.GET)
    public String orgTypeIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/orgtype");

        return "backoffice/orgtype";
    }
    
    @RequestMapping(value = "/backoffice/addOrgType", method = RequestMethod.POST)
    public String addOrgType(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addOrgType(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/orgtype";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
    
    @RequestMapping(value = "/backoffice/rolemaster", method = RequestMethod.GET)
    public String roleMasterIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/rolemaster");

        return "backoffice/rolemaster";
    }
    
    @RequestMapping(value = "/backoffice/addRoleMaster", method = RequestMethod.POST)
    public String addRoleMaster(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addRoleMaster(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/rolemaster";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
    
    @RequestMapping(value = "/backoffice/modulemaster", method = RequestMethod.GET)
    public String moduleMasterIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/modulemaster");

        return "backoffice/modulemaster";
    }
    
    @RequestMapping(value = "/backoffice/modulemaster", method = RequestMethod.POST)
    public String addModuleMaster(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addModuleMaster(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/modulemaster";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
    
   

    @RequestMapping(value = "/backoffice/emailtemplate", method = RequestMethod.GET)
    public String emailTemplateIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Within GET method for /backoffice/emailtemplate");

        return "backoffice/emailtemplate";
    }
    
    @RequestMapping(value = "/backoffice/emailtemplate", method = RequestMethod.POST)
    public String addEmailTemplate(@ModelAttribute(value = "botype") BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //TODO Validations on server side
        
        try{
            backOfficeService.addEmailTemplate(bean);
            
        }catch(ApplicationException ae){
               
            //ae.getCause() == null? ae.getMessage():ae.getCause().getMessage())
            errors.addError(new ObjectError("caseTypeError", "Unable do add the case." ));
            return "backoffice/emailtemplate";
        }
        return "backoffice/success"; // we need to return next page.
    }
    
    
    
    
    
    
    
	
	
}
