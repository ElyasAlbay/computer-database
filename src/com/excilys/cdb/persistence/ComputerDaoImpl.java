package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.cdb.model.Computer;

public class ComputerDaoImpl implements ComputerDao {
	DbConnection dbConnection = DbConnection.getInstance();
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	
	@Override
	public void listRequest() {
		connection = dbConnection.openConnection();
		
		String request = "SELECT * FROM computer";
		
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
	public void showDetails(int id) {
		connection = dbConnection.openConnection();
		
		String request = "SELECT * FROM computer WHERE id=" + id;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString("introduced") + " | " + resultSet.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

	}

	@Override
	public void showDetails(String name) {
		connection = dbConnection.openConnection();
		
		String request = "SELECT * FROM computer WHERE name='" + name;
		
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
	public void create(Computer computer) {
		connection = dbConnection.openConnection();
		
		String request = "INSERT INTO computer SET name='" + computer.getName() + ", introduced=";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

	}

	@Override
	public void update(Computer computer) {
		connection = dbConnection.openConnection();
		
		String request = "UPDATE computer SET name='" + computer.getName() + "WHERE id=" + computer.getId();
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

	}

	@Override
	public void delete(int id) {
connection = dbConnection.openConnection();
		
		String request = "DELETE FROM computer WHERE id=" + id;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(request);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

	}

}
