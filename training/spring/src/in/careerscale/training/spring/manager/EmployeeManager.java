package in.careerscale.training.spring.manager;

import in.careerscale.training.spring.dao.model.Employee;

public interface EmployeeManager {

	boolean login(String userName, String password);

	boolean register(Employee employee);

}
