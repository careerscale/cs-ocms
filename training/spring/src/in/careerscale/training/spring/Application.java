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

		/*
		 * ApplicationContext context = new ClassPathXmlApplicationContext(
		 * "spring.xml");
		 */

		BeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"spring.xml"));
		EmployeeManager manager = (EmployeeManager) factory
				.getBean("employeeManager");
		Employee employee = new Employee();
		manager.register(employee);

	}

}
