package in.careerscale.training;

import in.careerscale.training.spring.dao.EmployeeDAOImpl;
import in.careerscale.training.spring.dao.JDBCConnectionManagerImpl;
import in.careerscale.training.spring.manager.EmployeeManager;
import in.careerscale.training.spring.manager.EmployeeManagerImpl;

public class CareerScaleFactory {
	
	public static EmployeeManager getEmployeeManager(){
		EmployeeDAOImpl dao = new EmployeeDAOImpl();
		dao.setConnectionManager(new JDBCConnectionManagerImpl("jdbc connection string"));
		
		EmployeeManagerImpl manager =new EmployeeManagerImpl();
		manager.setDao(dao);
		
		return manager;
	}

}
