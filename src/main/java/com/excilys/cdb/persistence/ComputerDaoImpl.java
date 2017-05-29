package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.excilys.cdb.exceptions.DaoException;
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
			+ "ON computer.company_id = company.id ORDER BY ISNULL(%s), %s LIMIT ?, ?";
	private final static String GET_BY_ID = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.id=?";
	private final static String SEARCH_BY_NAME = "SELECT computer.*, company.name AS company_name FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(%s), %s LIMIT ?, ?";
	private final static String CREATE = "INSERT INTO computer SET name=?, introduced=?, discontinued =?, company_id =?";
	private final static String UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued =?, company_id =? WHERE id=?";
	private final static String DELETE = "DELETE FROM computer WHERE id=?";
	private final static String NUMBER_OF_ELEMENTS = "SELECT count(*) FROM computer";
	private final static String NB_SEARCH_ELEMENTS = "SELECT count(*) FROM computer LEFT JOIN company "
			+ "ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";

	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private ComputerDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}

	
	@Override
	public Page<Computer> listRequest(Page<Computer> computerPage) {
		ResultSet resultSet;
		String query = String.format(LIST, computerPage.getOrder(), computerPage.getOrder());
		
		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(query);) {
			// Get list of computers in the database
			statement.setInt(1, (computerPage.getPageNumber() - 1) * computerPage.getPageSize());
			statement.setInt(2, computerPage.getPageSize());

			resultSet = statement.executeQuery();
			computerPage.setElementList(ComputerMapper.getComputers(resultSet));

			// Get count of computers in the database 
			getNumberOfElements(computerPage);
		} catch (SQLException e) {
			throw new DaoException("Computer list request has failed: " + e.getMessage());
		}

		return computerPage;
	}

	@Override
	public Computer getById(int id) {
		ResultSet resultSet;
		Computer computer = null;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
			statement.setInt(1, id);

			resultSet = statement.executeQuery();
			computer = ComputerMapper.getComputer(resultSet);
		} catch (SQLException e) {
			throw new DaoException("Computer get by id request has failed: " + e.getMessage());
		}

		return computer;
	}

	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {
		ResultSet resultSet;
		String query = String.format(SEARCH_BY_NAME, computerPage.getOrder(), computerPage.getOrder());
		
		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			// Get list of computers in the database
			statement.setString(1, '%'+name+'%');
			statement.setString(2, '%'+name+'%');
			statement.setInt(3, (computerPage.getPageNumber() - 1) * computerPage.getPageSize());
			statement.setInt(4, computerPage.getPageSize());

			resultSet = statement.executeQuery();
			computerPage.setElementList(ComputerMapper.getComputers(resultSet));

			// Gets count of computers in the database 
			getNbSearchElements(computerPage, name);
		} catch (SQLException e) {
			throw new DaoException("Computer search has failed: " + e.getMessage());
		}
		
		return computerPage;
	}
	
	@Override
	public Computer create(Computer computer) {
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, computer.getName());

			if (computer.getIntroduced() != null) {
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			} else {
				statement.setNull(2, Types.TIMESTAMP);
			}

			if (computer.getDiscontinued() != null) {
				statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			} else {
				statement.setNull(3, Types.TIMESTAMP);
			}
			
			if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
				statement.setLong(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, Types.BIGINT);
			}

			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();

			// Get returned generated key from statement
			if (resultSet.next()) {
				computer.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new DaoException("Computer creation has failed: " + e.getMessage());
		}

		return computer;
	}

	@Override
	public Computer update(Computer computer) {
		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE)) {
			statement.setInt(5, computer.getId());
			statement.setString(1, computer.getName());

			if (computer.getIntroduced() != null) {
				statement.setDate(2, Date.valueOf(computer.getIntroduced()));
			} else {
				statement.setNull(2, java.sql.Types.TIMESTAMP);
			}

			if (computer.getDiscontinued() != null) {
				statement.setDate(3, Date.valueOf(computer.getDiscontinued()));
			} else {
				statement.setNull(3, java.sql.Types.TIMESTAMP);
			}
			
			if (computer.getCompany() != null && computer.getCompany().getId() > 0) {
				statement.setLong(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, Types.BIGINT);
			}
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Computer update has failed: " + e.getMessage());
		}

		return computer;
	}

	@Override
	public void delete(int id) {
		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Computer deletion has failed: " + e.getMessage());
		}

	}
	
	
	/**
	 * Gets number of computers in the database.
	 * 
	 * @return Count of computers.
	 */
	private void getNumberOfElements(Page<Computer> computerPage) {
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(NUMBER_OF_ELEMENTS)) {

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
		} catch (SQLException e) {
			throw new DaoException("Count of computers has failed: " + e.getMessage());
		}

	}
	
	/**
	 * Gets number of computers in search query.
	 * 
	 * @return Count of computers.
	 */
	private void getNbSearchElements(Page<Computer> computerPage, String name) {
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(NB_SEARCH_ELEMENTS)) {
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
		} catch (SQLException e) {
			throw new DaoException("Count of search query has failed: " + e.getMessage());
		}

	}

}
