package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.exceptions.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.utility.CompanyMapper;

/**
 * Implementation of CompanyDao, sends requests to the database and gets an
 * instance of Company from the corresponding Mapper.
 * 
 * @author Elyas Albay
 *
 */
public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;

	private DbConnection dbConnection;

	private final static String LIST = "SELECT * FROM company";
	private final static String GET_BY_ID = "SELECT * FROM company WHERE id=?";
	private final static String DELETE = "DELETE FROM company WHERE id=?";
	private final static String COMPUTERS_BY_COMPANYID = "SELECT * FROM computer WHERE company_id=?";
	private final static String GET_NUMBER_OF_ELEMENTS = "SELECT count(*) FROM company";

	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private CompanyDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}

	
	@Override
	public Page<Company> listRequest(Page<Company> companyPage) {
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(LIST)) {
			// Gets list of companies in the database
			resultSet = statement.executeQuery();
			companyPage.setElementList(CompanyMapper.getCompanies(resultSet));

			// Gets count of companies in the database
			getNumberOfElements(companyPage);

		} catch (SQLException e) {
			throw new DaoException("Company list request has failed: " + e.getMessage());
		}

		return companyPage;
	}

	@Override
	public Company getById(int id) {
		ResultSet resultSet;
		Company company = null;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(GET_BY_ID);) {
			statement.setInt(1, id);

			resultSet = statement.executeQuery();
			company = CompanyMapper.getCompany(resultSet);

		} catch (SQLException e) {
			throw new DaoException("Company get by id request has failed: " + e.getMessage());
		}

		return company;
	}

	@Override
	public void delete(int id) {

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE)) {

			// Start transaction
			dbConnection.startTransaction(connection);

			// Sends query to delete computers
			deleteComputersByCompanyId(id);

			// Sends query to delete company
			statement.setInt(1, id);

			try {
				statement.executeUpdate();
			} catch (SQLException e) {
				dbConnection.rollbackTransaction(connection);
				throw new SQLException(e.getMessage());
			}

			// Ends transaction
			dbConnection.commitTransaction(connection);
		} catch (SQLException e) {
			throw new DaoException("Company deletion has failed: " + e.getMessage());
		}
	}

	/**
	 * Gets number of companies in the database.
	 * 
	 * @return Count of companies.
	 */
	private void getNumberOfElements(Page<Company> companyPage) {
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_ELEMENTS)) {

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				companyPage.setNumberOfElements(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new DaoException("Count of companies has failed: " + e.getMessage());
		}

	}

	/**
	 * Deletes computers where company_id is equal to the parameter.
	 * 
	 * @param id
	 *            Company identifier.
	 */
	private void deleteComputersByCompanyId(int id) {
		ComputerDao computerDao = ComputerDaoImpl.INSTANCE;
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(COMPUTERS_BY_COMPANYID)) {
			statement.setInt(1, id);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				computerDao.delete(resultSet.getInt("id"));
			}

		} catch (SQLException e) {
			throw new DaoException("Computer deletion has failed: " + e.getMessage());
		}
	}

}
