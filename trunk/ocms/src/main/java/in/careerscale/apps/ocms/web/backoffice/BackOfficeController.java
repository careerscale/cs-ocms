package in.careerscale.apps.ocms.web.backoffice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.careerscale.apps.ocms.dao.model.CaseType;
import in.careerscale.apps.ocms.service.BackOfficeService;
import in.careerscale.apps.ocms.service.MasterDataService;
import in.careerscale.apps.ocms.service.UserService;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.backoffice.model.BOBean;
import in.careerscale.apps.ocms.web.registration.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	
	@Autowired
	private BackOfficeService backOfficeService;
	
	@Autowired
	MasterDataService masterDataService;
	

	
	@RequestMapping(value = "/backoffice", method = RequestMethod.GET)
	public String index(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		return "backoffice/index";
	}

	

	/*@RequestMapping(value = "/backoffice/casetype", method = RequestMethod.GET)
	public String caseTypeIndex(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		
		return "backoffice/casetype";
	}
	*/
	
	@RequestMapping(value = "/backoffice/casetype", method = RequestMethod.GET)	
	public String getCaseTypes1(@ModelAttribute(value = "botypeList")  ArrayList<BOBean> lstBean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("into the getCasetypes");
		List<CaseType> casetypesList  = null;  
	
		  try{
			  casetypesList = masterDataService.getCaseTypesList();
		        }catch(ApplicationException ae){
		       		//TODO log this exception, I dont want to expect this at any cost, but still remedy should be there.
		        	ae.printStackTrace();
		        	//TODO remove printStackTrace once stabilized and log it.
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
	
	@RequestMapping(value = "/backoffice/DeleteData", method = RequestMethod.DELETE)
	public String caseTypeDelete(@ModelAttribute(value = "botype")  BOBean bean,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("into the get method call");

		
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
       System.out.println("into the get method call" );

		
	}
	
	
	@RequestMapping(value = "/backoffice/DeleteData", method = RequestMethod.GET)
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
		System.out.println("into the get method call");

		
		//return "backoffice/casetype";
	}

	
	@RequestMapping(value = "/backoffice/UpdateData", method = RequestMethod.POST)
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
		System.out.println("into the get method call");
		if(boolName){
			return bean1.getName();
		}else{
			return bean1.getDescription();
		}
	}

	
	
	
	@RequestMapping(value = "/backoffice/casetype", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/backoffice/helptype", method = RequestMethod.GET)
    public String HelpTypeIndex(@ModelAttribute(value = "botype")  BOBean bean,
            BindingResult errors, HttpServletRequest request,
            HttpServletResponse response) {
        System.out.println("into the get method call");

        return "backoffice/helptype";
    }
    
    @RequestMapping(value = "/backoffice/helptype", method = RequestMethod.POST)
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
    
    
   
    
	
}
