package in.careerscale.training;

import in.careerscale.training.spring.manager.EmployeeManager;
import in.careerscale.training.spring.manager.EmployeeManagerImpl;

public class CareerScaleFactory {
	
	public static EmployeeManager getEmployeeManager(){
		return new EmployeeManagerImpl();
	}

}
