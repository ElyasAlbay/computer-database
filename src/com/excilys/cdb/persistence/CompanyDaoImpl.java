package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;

/**
 * Implementation of CompanyDao, sends requests to the database and gets an instance of Company
 * from the corresponding Mapper.
 * @author excilys
 *
 */
public class CompanyDaoImpl implements CompanyDao {
	DbConnection dbConnection;
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	public CompanyDaoImpl() {
		dbConnection = DbConnection.getInstance();
	}
	
	
	/**
	 * Sends a request to the database to get a complete list of companies.
	 * @return List of companies.
	 */
	@Override
	public List<Company> listRequest() {
		connection = dbConnection.openConnection();
		List<Company> companiesList = null;
		
		String request = "SELECT * FROM company";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			companiesList = CompanyMapper.getCompanies(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
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
		connection = dbConnection.openConnection();
		Company company = null;
		
		String request = "SELECT * FROM company WHERE id=" + id;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			company = CompanyMapper.getCompany(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
		
		return company;
	}

	/**
	 * Sends a request to the database to get a unique instance of Company
	 * corresponding to the given name.
	 * @param name Name of the company in the dabatase.
	 * @return Instance of company.
	 */
	@Override
	public Company getByName(String name) {
		connection = dbConnection.openConnection();
		Company company = null;
		
		String request = "SELECT * FROM company WHERE name=\"" + name;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(request);
			company = CompanyMapper.getCompany(resultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeConnection();
		}
		
		return company;
	}
	
}
