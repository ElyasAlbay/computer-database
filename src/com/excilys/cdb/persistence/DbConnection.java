package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class initiating connection to the mysql database.
 * @author excilys
 *
 */
public class DbConnection {
	public final static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	private final String USER_NAME = "admincdb";
	private final String PASSWORD = "qwerty1234";
	
	private static DbConnection dbcInstance = null;
	private Connection connection;


	/**
	 * Private constructor for CbConnection class.
	 */
	private DbConnection () {
		
	}
	
	/**
	 * Returns unique instance of class.
	 * @return Instance of DbConnection
	 */
	public static DbConnection getInstance() {
		if (dbcInstance == null) {
			dbcInstance = new DbConnection();
		}
		
		return dbcInstance;
	}
	
	/**
	 * Opens connection to the database.
	 */
	public Connection openConnection () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
