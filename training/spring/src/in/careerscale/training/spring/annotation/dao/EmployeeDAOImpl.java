package in.careerscale.training.spring.annotation.dao;

import java.sql.Connection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.careerscale.training.spring.annotation.dao.model.Employee;

@Component("dao")
public class EmployeeDAOImpl implements EmployeeDAO {

	private JDBCConnectionManager connectionManager = null;

	@Autowired
	public void setConnectionManager(JDBCConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public void register(Employee employee) {
		Connection con =connectionManager.getConnection();
		
		System.out.println("Registration successful in DAO");
		
	}

}
