package com.excilys.cdb.persistence;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

/**
 * Interface implemented by Computer DAO.
 * 
 * @author Elyas Albay
 *
 */
public interface ComputerDao extends CommonDao<Computer> {

	/**
	 * Sends a request to the database to get a page of computers.
	 * 
	 * @return Page of computers.
	 */
	public Page<Computer> getAll(Page<Computer> page);

	/**
	 * Sends a request to the database to get a page of computers where computer
	 * name or corresponding company name contains provided string.
	 * 
	 * @param page
	 *            Page of computers.
	 * @param name
	 *            String to search for in computer's name.
	 * @return Page of computers.
	 */
	public Page<Computer> searchByName(Page<Computer> page, String name);

	/**
	 * Deletes computers where company_id is equal to the parameter.
	 * 
	 * @param id
	 *            Company identifier.
	 * @return Amount of deleted computers.
	 */
	public long deleteComputersByCompanyId(long companyId);

	/**
	 * Gets number of elements in the database.
	 * 
	 * @return Count of elements.
	 */
	public int getNumberOfElements();
}
