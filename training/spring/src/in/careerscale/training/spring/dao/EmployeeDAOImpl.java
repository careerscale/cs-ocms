package in.careerscale.training.spring.dao;

import java.sql.Connection;

import in.careerscale.training.spring.dao.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	private JDBCConnectionManager connectionManager = null;

	public void setConnectionManager(JDBCConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}



	public void register(Employee employee) {
		Connection con =connectionManager.getConnection();
		
		System.out.println("Registration successful in DAO");
		
	}

}
