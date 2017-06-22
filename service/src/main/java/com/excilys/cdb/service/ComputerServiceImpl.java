package com.excilys.cdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDao;

/**
 * This class is the intermediary between user interface and Computer DAO.
 * Transmits queries to the DAO.
 * 
 * @author Elyas Albay
 *
 */
@Service
public class ComputerServiceImpl implements ComputerService {
	@Autowired
	ComputerDao computerDao;

	
	/**
	 * Class constructor. Instantiates DAO.
	 */
	public ComputerServiceImpl() {

	}

	
	@Override
	public Page<Computer> getAll(Page<Computer> computerPage) {

		return computerDao.getAll(computerPage);
	}

	@Override
	public Computer getById(long id) {

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
	public void delete(long id) {
		
		computerDao.delete(id);
	}
}
