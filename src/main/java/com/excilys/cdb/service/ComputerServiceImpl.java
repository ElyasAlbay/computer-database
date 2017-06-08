package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDao;
import com.zaxxer.hikari.HikariDataSource;

/**
 * This class is the intermediary between user interface and Computer DAO.
 * Transmits queries to the DAO.
 * 
 * @author Elyas Albay
 *
 */
@Service("computerService")
public class ComputerServiceImpl implements ComputerService {
	private static final Logger LOG = LoggerFactory.getLogger(ComputerServiceImpl.class);

	@Autowired
	HikariDataSource dataSource;
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
		try (Connection connection = dataSource.getConnection()) {

			computerDao.delete(id);
		} catch (SQLException e) {
			LOG.error("Delete("+id+") SQLException:" + e.getMessage());
		}

	}
}
