package in.careerscale.training.spring.annotation;

import in.careerscale.training.spring.annotation.dao.model.Employee;
import in.careerscale.training.spring.annotation.manager.EmployeeManager;
import in.careerscale.training.spring.annotation.manager.EmployeeManagerImpl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


public class ApplicationWithoutXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	    		
		EmployeeManager manager = (EmployeeManager) context.getBean("employeeManager");
		
		manager.register(new Employee());
		
		

	}

}
