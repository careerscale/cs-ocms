package in.careerscale.training;


import in.careerscale.training.spring.dao.model.Employee;
import in.careerscale.training.spring.manager.EmployeeManager;
import in.careerscale.training.spring.manager.EmployeeManagerImpl;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Employee employee = new Employee();
		EmployeeManager manager = CareerScaleFactory.getEmployeeManager();
		manager.register(employee);
		
		
		

	}

}
