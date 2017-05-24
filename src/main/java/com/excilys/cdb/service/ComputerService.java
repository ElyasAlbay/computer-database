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
	 * Calls corresponding method of DAO instance to create a computer in the
	 * database.
	 * 
	 * @param computer
	 *            Instance of Computer to create.
	 * @return Instance of Computer.
	 */
	public Computer create(Computer computer);

	/**
	 * Calls corresponding method of DAO instance to update a computer in the
	 * database.
	 * 
	 * @param computer
	 *            Instance of Computer to update.
	 * @return Instance of Computer.
	 */
	public Computer update(Computer computer);

	/**
	 * Calls corresponding method of DAO instance to delete a computer in the
	 * database.
	 * 
	 * @param computer
	 *            Instance of Computer to delete.
	 */
	public void delete(int id);
	
	/**
	 * Calls corresponding method of DAO instance to get a page of computers
	 * where computer name or corresponding company name contains provided string.
	 * 
	 * @param page
	 *            Page of computers.
	 * @param name
	 *            String to look for in computer's name.
	 * @return Page of computers.
	 */
	public Page<Computer> searchByName(Page<Computer> page, String name);
}
