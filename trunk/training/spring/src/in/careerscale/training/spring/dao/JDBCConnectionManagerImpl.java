package in.careerscale.training.spring.dao;

import java.sql.Connection;

public class JDBCConnectionManagerImpl implements JDBCConnectionManager {
   
	private String connection = null;
	
	public JDBCConnectionManagerImpl(String connectionString){
		this.connection = connectionString;
		System.out.println("The JDBC connection string is :: " + this.connection);
	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}
