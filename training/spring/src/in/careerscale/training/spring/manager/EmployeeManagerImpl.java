package in.careerscale.training.spring.manager;

import in.careerscale.training.spring.dao.EmployeeDAO;
import in.careerscale.training.spring.dao.EmployeeDAOImpl;
import in.careerscale.training.spring.dao.model.Employee;

public class EmployeeManagerImpl implements EmployeeManager {

	private EmployeeDAO dao = null;

	public void setDao(EmployeeDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean login(String userName, String password) {
		return false;
	}

	@Override
	public boolean register(Employee employee) {
		dao.register(employee);
		System.out.println("Registration Successful in manager");
		return true; // TODO fix this
	}

}
