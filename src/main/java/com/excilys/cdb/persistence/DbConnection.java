package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class initiating connection to the mysql database.
 * @author excilys
 *
 */
public enum DbConnection {
	INSTANCE;
	
	public final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
	private final String USER_NAME = "admincdb";
	private final String PASSWORD = "qwerty1234";
	
	private Connection connection;


	/**
	 * Private constructor for CbConnection class.
	 */
	private DbConnection () {
		
	}
	
	
	/**
	 * Opens connection to the database.
	 */
	public Connection openConnection () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/**
	 * Closes connection to the database.
	 */
	public void closeConnection () {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
