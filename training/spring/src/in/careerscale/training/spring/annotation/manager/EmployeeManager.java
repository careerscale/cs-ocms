package in.careerscale.training.spring.annotation.manager;

import in.careerscale.training.spring.annotation.dao.model.Employee;

public interface EmployeeManager {

	boolean login(String userName, String password);

	boolean register(Employee employee);

}
