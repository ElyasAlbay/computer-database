package com.excilys.cdb.service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

/**
 * Interface implemented by Computer service.
 * 
 * @author Elyas Albay
 *
 */
public interface ComputerService extends CommonService<Computer> {

	/**
	 * Calls corresponding method of DAO instance to get a page of all computers
	 * in the database.
	 * 
	 * @return Page of computers..
	 */
	public Page<Computer> getAll(Page<Computer> page);

	/**
	 * Calls corresponding method of DAO instance to get a page of computers
	 * where computer name or corresponding company name contains provided
	 * string.
	 * 
	 * @param page
	 *            Page of computers.
	 * @param name
	 *            String to look for in computer's name.
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

}
