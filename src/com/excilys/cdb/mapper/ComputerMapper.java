package com.excilys.cdb.mapper;

import com.excilys.cdb.model.Computer;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for Computer. This class converts a ResultSet from a database request into
 * an instance of Computer.
 * @author excilys
 *
 */
public class ComputerMapper {
	
	/**
	 * Static method to get a list of Computers from a query.
	 * @param resultSet ResultSet received from DAO.
	 * @return List of computers.
	 * @throws SQLException
	 */
	public static List<Computer> getComputers (ResultSet resultSet) throws SQLException {
		List<Computer> computersList = new ArrayList<>();

		while (resultSet.next()) {
			computersList.add(getComputer(resultSet));
		}
		
		return computersList;
	}
	
	/**
	 * Static method to get an instance of Computer from a query.
	 * @param resultSet ResultSet received from DAO.
	 * @return Instance of Computer.
	 * @throws SQLException
	 */
	public static Computer getComputer (ResultSet resultSet) throws SQLException {
		Computer computer = null;
		Date dateInt = resultSet.getDate("introduced");
		Date dateDis = resultSet.getDate("discontinued");
		
		if (resultSet.next()) {
			computer = new Computer(resultSet.getInt("id"), resultSet.getString("name"));
			if(dateInt != null)
				computer.setIntroduced(dateInt.toLocalDate());
			if(dateDis != null)
				computer.setDiscontinued(dateDis.toLocalDate());
			computer.setCompanyId(resultSet.getInt("company_id"));
		}
		
		return computer;
	}
	
}
