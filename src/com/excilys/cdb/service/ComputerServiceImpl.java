package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.ComputerDaoImpl;


/**
 * This class is the intermediary between user interface and Computer DAO.
 * Transmits queries to the DAO.
 * @author excilys
 *
 */
public class ComputerServiceImpl implements ComputerService {
	ComputerDao computerDao;
	
	
	/**
	 * Class constructor. Instantiates DAO.
	 */
	public ComputerServiceImpl () {
		computerDao = new ComputerDaoImpl();
	}
	
	
	/**
	 * Calls corresponding method of DAO instance to get a list of all computers in the database.
	 * @return List of Computer instances.
	 */
	@Override
	public List<Computer> listRequest() {
		
		return computerDao.listRequest();
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
	 * Calls corresponding method of DAO instance to get a computer by its name in the database.
	 * @param name Name of the computer in the database.
	 * @return Instance of Computer.
	 */
	@Override
	public Computer getByName(String name) {
		
		return computerDao.getByName(name);
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
