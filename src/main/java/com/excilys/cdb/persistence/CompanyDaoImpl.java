package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.ui.Page;


/**
 * Implementation of CompanyDao, sends requests to the database and gets an instance of Company
 * from the corresponding Mapper.
 * @author Elyas Albay
 *
 */
public enum CompanyDaoImpl implements CompanyDao {
	INSTANCE;
	
	private DbConnection dbConnection;
	private final static String LIST = "SELECT * FROM company LIMIT ?, ?";
	private final static String GET_BY_ID = "SELECT * FROM company WHERE id=?";
	private final static String GET_NUMBER_OF_PAGES = "SELECT count(*) FROM company";
	
	
	/**
	 * Class constructor. Initiates connection to the database.
	 */
	private CompanyDaoImpl() {
		dbConnection = DbConnection.INSTANCE;
	}
	
	
	/**
	 * Sends a request to the database to get a complete list of companies.
	 * @return Page of companies.
	 */
	@Override
	public Page<Company> listRequest(Page<Company> companyPage) {
		PreparedStatement statement;
		ResultSet resultSet;
		
		try (Connection connection = dbConnection.openConnection()) {
			/* Get list of companies in the database */
			statement = connection.prepareStatement(LIST);
			statement.setInt(1, (companyPage.getPageNumber()-1)*companyPage.getPageSize());
			statement.setInt(2, companyPage.getPageSize());
			
			resultSet = statement.executeQuery();
			companyPage.setElementList(CompanyMapper.getCompanies(resultSet));
			
			
			/* Gets count of companies in the database */
			statement = connection.prepareStatement(GET_NUMBER_OF_PAGES);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				companyPage.setNumberOfElements(resultSet.getInt(1));
				int numberOfPages = companyPage.getNumberOfElements()/companyPage.getPageSize();

				// Rounds to the upper integer if the division has a remainder.
				if((companyPage.getNumberOfElements() % companyPage.getPageSize()) != 0) {
					companyPage.setNumberOfPages(numberOfPages+1);
				} else {
					companyPage.setNumberOfPages(numberOfPages);
				}
			}
			
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return companyPage;
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
			statement.setInt(1, id);
			
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
