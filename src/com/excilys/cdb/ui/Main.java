package com.excilys.cdb.ui;

import com.excilys.cdb.service.DbConnection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		DbConnection connection = new DbConnection();
		
		connection.openConnection();
		try {
			DriverManager.getDriver(DbConnection.URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
