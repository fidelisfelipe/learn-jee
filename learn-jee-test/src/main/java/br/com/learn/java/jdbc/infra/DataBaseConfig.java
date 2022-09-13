package br.com.learn.java.jdbc.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBaseConfig {
	private static final Logger LOG = LoggerFactory.getLogger(DataBaseConfig.class);

	private Connection connection;

	public DataBaseConfig() {
		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:mem:testdb";
			connection = DriverManager.getConnection(url, "sa", "");
		} catch (ClassNotFoundException | SQLException e) {
			LOG.error("falha",e);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void init() {
		createTables();
		createViews();
	}

	private void createTables() {
		try {
			connection.createStatement()
					.executeUpdate("create table PESSOA (ID int primary key auto_increment, NAME VARCHAR(45))");
		} catch (SQLException e) {
			LOG.error("falha", e);
		}
	}

	private void createViews() {
		try {
			connection.createStatement().executeUpdate("CREATE VIEW CUSTOMER_VIEW AS SELECT * FROM CUSTOMER");
		} catch (SQLException e) {
			LOG.error("falha", e);
		}
	}
}