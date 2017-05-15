package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;


/**
 * Implementation of CompanyDao, sends requests to the database and gets an instance of Company
 * from the corresponding Mapper.
 * @author Elyas Albay
 *
 */
public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;
	
	private DbConnection dbConnection;
	private final static String LIST = "SELECT * FROM company";
	private final static String GET_BY_ID = "SELECT* FROM company WHERE id=?";
	
	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private CompanyDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}
	
	
	/**
	 * Sends a request to the database to get a complete list of companies.
	 * @return List of companies.
	 */
	@Override
	public List<Company> listRequest() {
		PreparedStatement statement;
		ResultSet resultSet;
		List<Company> companiesList = null;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(LIST);
			resultSet = statement.executeQuery();
			companiesList = CompanyMapper.getCompanies(resultSet);
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return companiesList;
	}

	/**
	 * Sends a request to the database to get a unique instance of Company
	 * corresponding to the given id.
	 * @param id Identifier of the company in the dabatase.
	 * @return Instance of company.
	 */
	@Override
	public Company getById(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Company company = null;
		
		try (Connection connection = dbConnection.openConnection()) {
			statement = connection.prepareStatement(GET_BY_ID);
			resultSet = statement.executeQuery();
			company = CompanyMapper.getCompany(resultSet);
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return company;
	}
	
}
