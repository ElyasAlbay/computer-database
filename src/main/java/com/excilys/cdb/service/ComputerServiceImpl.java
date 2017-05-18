package com.excilys.cdb.service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.ComputerDaoImpl;
import com.excilys.cdb.ui.Page;


/**
 * This class is the intermediary between user interface and Computer DAO.
 * Transmits queries to the DAO.
 * @author Elyas Albay
 *
 */
public enum ComputerServiceImpl implements ComputerService {
	INSTANCE;
	
	ComputerDao computerDao;
	
	
	/**
	 * Class constructor. Instantiates DAO.
	 */
	private ComputerServiceImpl () {
		computerDao = ComputerDaoImpl.INSTANCE;
	}
	
	
	/**
	 * Calls corresponding method of DAO instance to get a list of all computers in the database.
	 * @return List of Computer instances.
	 */
	@Override
	public Page<Computer> listRequest(Page<Computer> computerPage) {
		
		return computerDao.listRequest(computerPage);
	}

	/**
	 * Calls corresponding method of DAO instance to get a computer by its id in the dabatase.
	 * @param id Identifier of the computer in the database.
	 * @return Computer instance.
	 */
	@Override
	public Computer getById(int id) {
		
		return computerDao.getById(id);
	}

	/**
	 * Calls corresponding method of DAO instance to create a computer in the database.
	 * @param computer Instance of Computer to create.
	 * @return Instance of Computer.
	 */
	@Override
	public Computer create(Computer computer) {
		
		return computerDao.create(computer);
	}

	/**
	 * Calls corresponding method of DAO instance to update a computer in the database.
	 * @param computer Instance of Computer to update.
	 * @return Instance of Computer.
	 */
	@Override
	public Computer update(Computer computer) {
		
		return computerDao.update(computer);
	}

	/**
	 * Calls corresponding method of DAO instance to delete a computer in the database.
	 * @param computer Instance of Computer to delete.
	 */
	@Override
	public void delete(int id) {
		computerDao.delete(id);

	}

}
