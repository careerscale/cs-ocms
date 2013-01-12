package in.careerscale.training.spring;

import in.careerscale.training.spring.dao.model.Employee;
import in.careerscale.training.spring.manager.EmployeeManager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"spring.xml"));
		EmployeeManager manager = (EmployeeManager) factory
				.getBean("employeeManager");
		Employee employee = new Employee();
		manager.register(employee);

		//Either beanFactory or application context can be used.
		//ApplicationContext is bean factory + additional facilities.
		System.out.println("\n\nFrom ApplicationContext approach ");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring.xml");
		EmployeeManager manager1 = (EmployeeManager) context.getBean("employeeManager");
		manager1.register(new Employee());

		
	}

}
