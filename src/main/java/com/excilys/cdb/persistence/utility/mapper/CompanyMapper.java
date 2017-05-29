package com.excilys.cdb.persistence.utility.mapper;

import com.excilys.cdb.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for Company. This class converts a ResultSet from a database
 * request into an instance of Company.
 * 
 * @author Elyas Albay
 *
 */
public class CompanyMapper {

	/**
	 * Static method to get a list of Companies from a query.
	 * 
	 * @param resultSet
	 *            ResultSet received from DAO.
	 * @return List of companies.
	 * @throws SQLException
	 */
	public static List<Company> getCompanies(ResultSet resultSet) throws SQLException {
		List<Company> companiesList = new ArrayList<>();

		while (resultSet.next()) {
			companiesList.add(extractCompany(resultSet));
		}

		return companiesList;
	}

	/**
	 * Static method to get an instance of Computer from a query. Assert
	 * ResultSet.next();
	 * 
	 * @param resultSet
	 *            ResultSet received from DAO.
	 * @return Instance of Computer.
	 * @throws SQLException
	 */
	public static Company getCompany(ResultSet resultSet) throws SQLException {
		Company company = null;

		if (resultSet.next()) {
			company = extractCompany(resultSet);
		}

		return company;
	}

	/**
	 * Static method to get an instance of Company from a query.
	 * 
	 * @param resultSet
	 *            ResultSet received from DAO.
	 * @return Instance of Company.
	 * @throws SQLException
	 */
	private static Company extractCompany(ResultSet resultSet) throws SQLException {
		Company company = null;

		company = new Company(resultSet.getInt("id"), resultSet.getString("name"));

		return company;
	}

}
