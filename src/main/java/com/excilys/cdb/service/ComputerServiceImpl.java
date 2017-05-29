package com.excilys.cdb.service;

import java.sql.Connection;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.ComputerDaoImpl;
import com.excilys.cdb.persistence.utility.DbConnection;

/**
 * This class is the intermediary between user interface and Computer DAO.
 * Transmits queries to the DAO.
 * 
 * @author Elyas Albay
 *
 */
public enum ComputerServiceImpl implements ComputerService {
	INSTANCE;

	DbConnection dbConnection;
	ComputerDao computerDao;

	
	/**
	 * Class constructor. Instantiates DAO.
	 */
	private ComputerServiceImpl() {
		computerDao = ComputerDaoImpl.INSTANCE;
	}
	

	@Override
	public Page<Computer> getAll(Page<Computer> computerPage) {

		return computerDao.getAll(computerPage);
	}

	@Override
	public Computer getById(int id) {

		return computerDao.getById(id);
	}

	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {

		return computerDao.searchByName(computerPage, name);
	}

	@Override
	public Computer create(Computer computer) {

		return computerDao.create(computer);
	}

	@Override
	public Computer update(Computer computer) {

		return computerDao.update(computer);
	}

	@Override
	public void delete(int id) {
		Connection connection = dbConnection.openConnection();
		
		computerDao.delete(id, connection);
	}

}
