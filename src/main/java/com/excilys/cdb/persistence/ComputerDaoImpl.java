package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.utility.ComputerMapper;

/**
 * Implementation of ComputerDao, sends requests to the database and gets an
 * instance of Computer from the corresponding Mapper.
 * 
 * @author Elyas Albay
 *
 */
public enum ComputerDaoImpl implements ComputerDao {
	INSTANCE;

	private DbConnection dbConnection;

	private final static String LIST = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id LIMIT ?, ?";
	private final static String GET_BY_ID = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.id=?";
	private final static String SEARCH_BY_NAME = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? LIMIT ?, ?";
	private final static String CREATE = "INSERT INTO computer SET name=?, introduced=?, discontinued =?, company_id =?";
	private final static String UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued =?, company_id =? WHERE id=?";
	private final static String DELETE = "DELETE FROM computer WHERE id=?";
	private final static String GET_NUMBER_OF_ELEMENTS = "SELECT count(*) FROM computer";
	private final static String GET_SEARCH_ELEMENTS = "SELECT count(*) FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";

	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private ComputerDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}

	
	@Override
	public Page<Computer> listRequest(Page<Computer> computerPage) {
		PreparedStatement statement;
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection()) {
			// Get list of computers in the database
			statement = connection.prepareStatement(LIST);
			statement.setInt(1, (computerPage.getPageNumber() - 1) * computerPage.getPageSize());
			statement.setInt(2, computerPage.getPageSize());

			resultSet = statement.executeQuery();
			computerPage.setElementList(ComputerMapper.getComputers(resultSet));

			// Gets count of computers in the database 
			statement = connection.prepareStatement(GET_NUMBER_OF_ELEMENTS);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				computerPage.setNumberOfElements(resultSet.getInt(1));
				int numberOfPages = computerPage.getNumberOfElements()/computerPage.getPageSize();

				// Rounds to the upper integer if the division has a remainder
				if((computerPage.getNumberOfElements() % computerPage.getPageSize()) != 0) {
					computerPage.setNumberOfPages(numberOfPages+1);
				} else {
					computerPage.setNumberOfPages(numberOfPages);
				}
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computerPage;
	}

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

	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {
		PreparedStatement statement;
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection()) {
			// Get list of computers in the database
			statement = connection.prepareStatement(SEARCH_BY_NAME);
			statement.setString(1, '%'+name+'%');
			statement.setString(2, '%'+name+'%');
			statement.setInt(3, (computerPage.getPageNumber() - 1) * computerPage.getPageSize());
			statement.setInt(4, computerPage.getPageSize());

			resultSet = statement.executeQuery();
			computerPage.setElementList(ComputerMapper.getComputers(resultSet));

			// Gets count of computers in the database 
			statement = connection.prepareStatement(GET_SEARCH_ELEMENTS);
			statement.setString(1, '%'+name+'%');
			statement.setString(2, '%'+name+'%');
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				computerPage.setNumberOfElements(resultSet.getInt(1));
				int numberOfPages = computerPage.getNumberOfElements()/computerPage.getPageSize();

				// Rounds to the upper integer if the division has a remainder
				if((computerPage.getNumberOfElements() % computerPage.getPageSize()) != 0) {
					computerPage.setNumberOfPages(numberOfPages+1);
				} else {
					computerPage.setNumberOfPages(numberOfPages);
				}
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return computerPage;
	}
	
	@Override
	public Computer create(Computer computer) {
		PreparedStatement statement;
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, computer.getName());

			if (computer.getIntroduced() == null) {
				statement.setNull(2, Types.TIMESTAMP);
			} else {
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			}

			if (computer.getDiscontinued() == null) {
				statement.setNull(3, Types.TIMESTAMP);
			} else {
				statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			}
			
			if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
				statement.setLong(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, Types.BIGINT);
			}

			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();

			// Gets returned generated key from statement
			if (resultSet.next()) {
				computer.setId(resultSet.getInt(1));
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computer;
	}

	@Override
	public Computer update(Computer computer) {
		PreparedStatement statement;

		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(UPDATE);
			statement.setInt(5, computer.getId());
			statement.setString(1, computer.getName());

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
			
			if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
				statement.setInt(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, Types.BIGINT);
			}

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computer;
	}

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
