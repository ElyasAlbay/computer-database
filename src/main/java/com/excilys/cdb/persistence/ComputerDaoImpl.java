package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Computer;


/**
 * Implementation of ComputerDao, sends requests to the database and gets an instance of Computer
 * from the corresponding Mapper.
 * @author Elyas Albay
 *
 */
public enum ComputerDaoImpl implements ComputerDao {
	INSTANCE;
	
	DbConnection dbConnection;
	private final static String LIST = "SELECT * FROM computer";
	private final static String GET_BY_ID = "SELECT* FROM computer WHERE id=?";
	private final static String CREATE = "INSERT INTO computer SET name=?, introduced=?, discontinued =?, company_id =?";
	private final static String UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued =?, company_id =? WHERE id=?";
	private final static String DELETE = "DELETE FROM computer WHERE id=?";
	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private ComputerDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}
	
	
	/**
	 * Sends a request to the database to get a complete list of computers.
	 * @return List of computers.
	 */
	@Override
	public List<Computer> listRequest() {
		PreparedStatement statement;
		ResultSet resultSet;
		List<Computer> computersList = null;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(LIST);
			resultSet = statement.executeQuery();
			computersList = ComputerMapper.getComputers(resultSet);
			
			resultSet.close();
			statement.close();
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
		
		PreparedStatement statement;
		ResultSet resultSet;
		Computer computer = null;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(GET_BY_ID);
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			computer = ComputerMapper.getComputer(resultSet);
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		PreparedStatement statement;
		ResultSet resultSet;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, computer.getName());
			statement.setInt(4, computer.getCompanyId());
			
			if (computer.getIntroduced() == null) {
				statement.setNull(2, java.sql.Types.TIMESTAMP);
			} else {
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}
			
			if (computer.getDiscontinued() == null) {
				statement.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}
			
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if(resultSet.next()){
				computer.setId(resultSet.getInt(1));
			}
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computer;
	}

	/**
	 * Sends a request to the database to update a given entry of the Computer table in the database.
	 * @param computer Instance of Computer to update.
	 */
	@Override
	public Computer update(Computer computer) {
		
		PreparedStatement statement;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(UPDATE);
			statement.setString(1, computer.getName());
			statement.setInt(4, computer.getCompanyId());
			statement.setInt(5, computer.getId());
			
			if (computer.getIntroduced() == null) {
				statement.setNull(2, java.sql.Types.TIMESTAMP);
			} else {
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}
			
			if (computer.getDiscontinued() == null) {
				statement.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computer;
	}

	/**
	 * Sends a request to the database to delete a given entry of the Computer table in the database.
	 * @param id Identifier of the computer in the database.
	 */
	@Override
	public void delete(int id) {
		PreparedStatement statement;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(DELETE);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
