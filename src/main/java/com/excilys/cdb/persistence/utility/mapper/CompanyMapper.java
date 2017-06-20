package com.excilys.cdb.persistence.utility.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;

/**
 * Mapper class for Company. This class converts a ResultSet from a database
 * request into an instance of Company.
 * 
 * @author Elyas Albay
 *
 */
public class CompanyMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet resultSet, int rowNb) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		
		return company;
	}

}
