package com.excilys.cdb.persistence.utility.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * Mapper class for Computer. This class converts a ResultSet from a database
 * request into an instance of Computer.
 * 
 * @author Elyas Albay
 *
 */
public class ComputerMapper implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNb) throws SQLException {
		Computer computer;
		Company company;
		Date dateInt = null;
		Date dateDis = null;

		computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"));
		company = new Company(resultSet.getLong("company_id"), resultSet.getString("company_name"));

		Timestamp timeInt = resultSet.getTimestamp("introduced");
		if (timeInt != null) {
			dateInt = new Date(timeInt.getTime());
			computer.setIntroduced(dateInt.toLocalDate());
		}
		Timestamp timeDis = resultSet.getTimestamp("discontinued");
		if (timeDis != null) {
			dateDis = new Date(timeDis.getTime());
			computer.setDiscontinued(dateDis.toLocalDate());
		}

		computer.setCompany(company);
		
		return computer;
	}

}
