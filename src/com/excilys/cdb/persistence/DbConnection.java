package com.excilys.cdb.service;

import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	public final static String URL = "jdbc:mysql://localhost:3306/computer-database-db";
	private final String USER_NAME = "admincdb";
	private final String PASSWORD = "qwerty1234";
	private Connection connection;

	
	public void openConnection () {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection () {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
