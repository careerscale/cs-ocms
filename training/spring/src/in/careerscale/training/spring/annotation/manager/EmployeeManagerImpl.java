package in.careerscale.training.spring.annotation.manager;

import in.careerscale.training.spring.annotation.dao.EmployeeDAO;
import in.careerscale.training.spring.annotation.dao.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component("employeeManager")
public class EmployeeManagerImpl implements EmployeeManager {

	private EmployeeDAO dao = null;

	@Autowired
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
