package in.careerscale.training.spring.annotation.dao;

import java.sql.Connection;

public interface JDBCConnectionManager {

	Connection getConnection();

}
