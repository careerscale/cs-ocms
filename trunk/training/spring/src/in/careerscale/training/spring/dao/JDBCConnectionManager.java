package in.careerscale.training.spring.dao;

import java.sql.Connection;

public interface JDBCConnectionManager {

	Connection getConnection();

}
