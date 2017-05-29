package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.utility.DbConnection;
import com.excilys.cdb.persistence.utility.mapper.CompanyMapper;

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
	private static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	private final static String LIST = "SELECT * FROM company";
	private final static String GET_BY_ID = "SELECT * FROM company WHERE id=?";
	private final static String DELETE = "DELETE FROM company WHERE id=?";
	private final static String NUMBER_OF_ELEMENTS = "SELECT count(*) FROM company";

	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private CompanyDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}

	
	@Override
	public Page<Company> getAll(Page<Company> companyPage) {
		ResultSet resultSet;

		try (Connection connection = dbConnection.openConnection();
				PreparedStatement statement = connection.prepareStatement(LIST)) {
			// Get list of companies in the database
			resultSet = statement.executeQuery();
			companyPage.setElementList(CompanyMapper.getCompanies(resultSet));

			// Get count of companies in the database
			getNumberOfElements(companyPage);

		} catch (SQLException e) {
			LOG.error("CompanyDao getAll(): SQLException. " + e.getMessage());
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
			LOG.error("CompanyDao getById("+id+"): SQLException. " + e.getMessage());
			throw new DaoException("Company get by id request has failed: " + e.getMessage());
		}

		return company;
	}

	@Override
	public void delete(int id, Connection connection) {
		try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			LOG.error("CompanyDao delete("+id+"): SQLException. " + e.getMessage());
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
				PreparedStatement statement = connection.prepareStatement(NUMBER_OF_ELEMENTS)) {

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				companyPage.setNumberOfElements(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			LOG.error("CompanyDao getNumberOfElements(): SQLException. " + e.getMessage());
			throw new DaoException("Count of companies has failed: " + e.getMessage());
		} 
	}

}
