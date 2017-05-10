package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDaoImpl implements CompanyDao {
	DbConnection dbConnection = DbConnection.getInstance();
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	
	@Override
	public void listRequest() {
		connection = dbConnection.openConnection();
		
		String request = "SELECT * FROM company";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + " | " + resultSet.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
	}

	@Override
	public void getById(int id) {
		connection = dbConnection.openConnection();
		
		String request = "SELECT * FROM company WHERE id=" + id +";";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("id") + " | " + resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
	}

	@Override
	public void getByName(String name) {
		connection = dbConnection.openConnection();
		
		String request = "SELECT * FROM company WHERE name=\"" + name +"\";";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("id") + " | " + resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
	}
	
}
