package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	DbConnection dbConnection;
	Connection connection;
	Statement statement;
	PreparedStatement preStatement;
	ResultSet resultSet;
	
	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	public ComputerDaoImpl() {
		dbConnection = DbConnection.getInstance();
	}
	
	
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
			preStatement = connection.prepareStatement(request);
			resultSet = preStatement.executeQuery();
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
		
		String request = "SELECT * FROM computer WHERE id=?";
		
		try {
			preStatement = connection.prepareStatement(request);
			preStatement.setInt(1, id);
			
			resultSet = preStatement.executeQuery();
			computer = ComputerMapper.getComputer(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

		return computer;
	}

	/**
	 * Sends a request to the database to get a unique instance of Computer
	 * corresponding to the given id.
	 * @param id Identifier of the computer in the dabatase.
	 * @return Instance of computer.
	 */
	@Override
	public Computer getByName(String name) {
		connection = dbConnection.openConnection();
		Computer computer = null;
		
		String request = "SELECT * FROM computer WHERE name=?";
		
		try {
			preStatement = connection.prepareStatement(request);
			preStatement.setString(1, request);
			
			resultSet = preStatement.executeQuery();
			computer = ComputerMapper.getComputer(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

		return computer;
	}

	/**
	 * Sends a request to the database to get a unique instance of Computer
	 * corresponding to the given name.
	 * @param name Name of the computer in the dabatase.
	 * @return Instance of computer.
	 */
	@Override
	public Computer create(Computer computer) {
		connection = dbConnection.openConnection();

		String request = "INSERT INTO computer SET name=?, introduced=?, discontinued =?, company_id =?";
		
		try {
			preStatement = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, computer.getName());
			preStatement.setInt(4, computer.getCompanyId());
			
			if (computer.getIntroduced() == null) {
				preStatement.setNull(2, java.sql.Types.TIMESTAMP);
			} else {
				preStatement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}
			
			if (computer.getDiscontinued() == null) {
				preStatement.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				preStatement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}
			
			preStatement.executeUpdate();
			resultSet = preStatement.getGeneratedKeys();
			
			if(resultSet.next()){
				computer.setId(resultSet.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

		return computer;
	}

	/**
	 * Sends a request to the database to update a given entry of the Computer table in the database.
	 * @param computer Instance of Computer to update.
	 */
	@Override
	public Computer update(Computer computer) {
		connection = dbConnection.openConnection();
		
		String request = "UPDATE computer SET name=?, introduced=?, discontinued =?, company_id =? WHERE id=?";
		
		try {
			preStatement = connection.prepareStatement(request);
			preStatement.setString(1, computer.getName());
			preStatement.setInt(4, computer.getCompanyId());
			preStatement.setInt(5, computer.getId());
			
			if (computer.getIntroduced() == null) {
				preStatement.setNull(2, java.sql.Types.TIMESTAMP);
			} else {
				preStatement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}
			
			if (computer.getDiscontinued() == null) {
				preStatement.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				preStatement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}

			preStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

		return computer;
	}

	/**
	 * Sends a request to the database to delete a given entry of the Computer table in the database.
	 * @param id Identifier of the computer in the database.
	 */
	@Override
	public void delete(int id) {
		connection = dbConnection.openConnection();
		
		String request = "DELETE FROM computer WHERE id=?";
		
		try {
			preStatement = connection.prepareStatement(request);
			preStatement.setInt(1, id);
			
			preStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}

	}

}
