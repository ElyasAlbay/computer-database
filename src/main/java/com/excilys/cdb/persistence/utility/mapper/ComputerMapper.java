package com.excilys.cdb.persistence.utility.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * Mapper class for Computer. This class converts a ResultSet from a database
 * request into an instance of Computer.
 * 
 * @author Elyas Albay
 *
 */
public class ComputerMapper {

	/**
	 * Static method to get a list of Computers from a query.
	 * 
	 * @param resultSet
	 *            ResultSet received from DAO.
	 * @return List of computers.
	 * @throws SQLException
	 */
	public static List<Computer> getComputers(ResultSet resultSet) throws SQLException {
		List<Computer> computersList = new ArrayList<>();

		while (resultSet.next()) {
			computersList.add(extractComputer(resultSet));
		}

		return computersList;
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
	public static Computer getComputer(ResultSet resultSet) throws SQLException {
		Computer computer = null;

		if (resultSet.next()) {
			computer = extractComputer(resultSet);
		}

		return computer;
	}

	/**
	 * Static method to get an instance of Computer from a query.
	 * 
	 * @param resultSet
	 *            ResultSet received from DAO.
	 * @return Instance of Computer.
	 * @throws SQLException
	 */
	private static Computer extractComputer(ResultSet resultSet) throws SQLException {
		Computer computer = null;
		Company company = null;
		Date dateInt = null;
		Date dateDis = null;

		computer = new Computer(resultSet.getInt("id"), resultSet.getString("name"));
		company = new Company(resultSet.getInt("company_id"), resultSet.getString("company_name"));

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
