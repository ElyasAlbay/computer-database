package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cdb.exceptions.DatabaseConnectionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Class initiating connection to the mysql database.
 * @author Elyas Albay
 *
 */
public enum DbConnection {
	INSTANCE;

	private Connection connection;
	private HikariConfig config;
	private HikariDataSource dataSource;

	
	/**
	 * Private constructor for CbConnection class.
	 */
	private DbConnection () {
		connection = null;
		
		config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db");
		config.setUsername("admincdb");
		config.setPassword("qwerty1234");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("zeroDateTimeBehavior", "convertToNull");
		config.addDataSourceProperty("useJDBCCompliantTimezoneShift", "true");
		config.addDataSourceProperty("useLegacyDatetimeCode", "false");
		config.addDataSourceProperty("serverTimezone", "Europe/Paris");
		
		config.setMaximumPoolSize(10);
		config.setLeakDetectionThreshold(300);
		
		dataSource = new HikariDataSource(config);
	}
	
	
	/**
	 * Opens connection to the database.
	 */
	public Connection openConnection () {
		if (connection == null) {
			try {
					connection = dataSource.getConnection();
			} catch (SQLException e) {
				throw new DatabaseConnectionException("Failed to open connection to the database: " + e.getMessage());
			}
		}
		
		return connection;
	}
	
	/**
	 * Closes connection to the database.
	 */
	public void closeConnection () {
		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DatabaseConnectionException("Failed to close connection to the database.");
			}
		}
		
	}
	
}
