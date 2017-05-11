package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;

/**
 * Implementation of ComputerDao, sends requests to the database and gets an instance of Computer
 * from the corresponding Mapper.
 * @author excilys
 *
 */
public class ComputerDaoImpl implements ComputerDao {
	DbConnection dbConnection = DbConnection.getInstance();
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	
	/**
	 * Sends a request to the database to get a complete list of computers.
	 * @return List of computers.
	 */
	@Override
	public List<Computer> listRequest() {
		connection = dbConnection.openConnection();
		List<Computer> computersList = null;
		
		String request = "SELECT * FROM computer";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			computersList = ComputerMapper.getComputers(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
		
		return computersList;
	}

	/**
	 * Sends a request to the database to get an instance of Computer.
	 * @param id Identifier of the computer in the database.
	 * @return Instance of Computer.
	 */
	@Override
	public Computer getById(int id) {
		connection = dbConnection.openConnection();
		Computer computer = null;
		
		String request = "SELECT * FROM computer WHERE id=" + id;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			ComputerMapper.getComputer(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

		return computer;
	}

	/**
	 * 
	 */
	@Override
	public Computer getByName(String name) {
		connection = dbConnection.openConnection();
		Computer computer = null;
		
		String request = "SELECT * FROM computer WHERE name='" + name;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			ComputerMapper.getComputer(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

		return computer;
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
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

	/**
	 * 
	 */
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
