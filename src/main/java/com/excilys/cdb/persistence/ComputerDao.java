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
	 * Sends a request to the database to get a unique instance of Computer
	 * corresponding to the given name.
	 * 
	 * @param name
	 *            Name of the computer in the dabatase.
	 * @return Instance of computer.
	 */
	public Computer create(Computer computer);

	/**
	 * Sends a request to the database to update a given entry of the Computer
	 * table in the database.
	 * 
	 * @param computer
	 *            Instance of Computer to update.
	 */
	public Computer update(Computer computer);

	/**
	 * Sends a request to the database to delete a given entry of the Computer
	 * table in the database.
	 * 
	 * @param id
	 *            Identifier of the computer in the database.
	 */
	public void delete(int id);
	
	/**
	 * Sends a request to the database to get a page of computers where computer name
	 * or corresponding company name contains provided string.
	 * 
	 * @param page
	 *            Page of computers.
	 * @param name
	 *            String to search for in computer's name.
	 * @return Page of computers.
	 */
	public Page<Computer> searchByName(Page<Computer> page, String name);
}
