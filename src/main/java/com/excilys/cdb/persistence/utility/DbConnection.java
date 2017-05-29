package com.excilys.cdb.persistence.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static final Logger LOG = LoggerFactory.getLogger(DbConnection.class);
	
	private static final String PROP_FILE = "/hikari.properties";
	private static final String START_TRANSACTION = "START TRANSACTION";
	private static final String COMMIT = "COMMIT";
	private static final String ROLLBACK = "ROLLBACK";

	
	private HikariDataSource dataSource;
	private static ThreadLocal<Connection> threadConnection = new ThreadLocal<>();

	
	/**
	 * Private constructor for CbConnection class.
	 */
	private DbConnection () {
		HikariConfig config = new HikariConfig(PROP_FILE);
		
		dataSource = new HikariDataSource(config);
	}
	
	
	/**
	 * Opens connection to the database.
	 */
	public Connection openConnection () {		
		LOG.info("Openning connection...");
		
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			threadConnection.set(connection);
		} catch (SQLException e) {
			LOG.error("Connection could not be opened. " + e.getMessage());
			throw new DatabaseConnectionException("Failed to open connection to the database: " + e.getMessage());
		}
		
		return threadConnection.get();
	}
	
	/**
	 * Closes connection to the database.
	 */
	public void closeConnection () {
		LOG.info("Closing connection...");
		if (threadConnection.get() != null) {
			try {
				if (!threadConnection.get().isClosed()) {
					threadConnection.get().close();
					threadConnection.remove();
				}
			} catch (SQLException e) {
				LOG.error("Connection could not be close. " + e.getMessage());
				throw new DatabaseConnectionException("Failed to close connection to the database: " + e.getMessage());
			}
		}
		
	}
	
	/**
	 * Starts transaction for the given connection.
	 * @param connection Connection to the database.
	 */
	public void startTransaction(Connection connection) {
		
		try (PreparedStatement statement = connection.prepareStatement(START_TRANSACTION)) {
			statement.executeQuery();
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Failed to start transaction: " + e.getMessage());
		}
	}
	
	/**
	 * Commit changes to the database.
	 * @param connection Connection to the database.
	 */
	public void commitTransaction(Connection connection) {
		
		try (PreparedStatement statement = connection.prepareStatement(COMMIT)) {
			statement.executeQuery();
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Failed to commit transaction: " + e.getMessage());
		}
	}
	
	/**
	 * Rollback changes to the database.
	 * @param connection Connection to the database.
	 */
	public void rollbackTransaction(Connection connection) {
		
		try (PreparedStatement statement = connection.prepareStatement(ROLLBACK)) {
			statement.executeQuery();
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Failed to rollback transaction: " + e.getMessage());
		}
	}
}
