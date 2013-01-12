package in.careerscale.training.spring.annotation.dao;

import java.sql.Connection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("connectionManager")
public class JDBCConnectionManagerImpl implements JDBCConnectionManager {
   
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}
