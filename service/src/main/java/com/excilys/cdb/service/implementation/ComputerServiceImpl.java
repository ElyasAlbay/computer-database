package com.excilys.cdb.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.service.ComputerService;

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
	int totalElements;

	/**
	 * Default class constructor.
	 */
	public ComputerServiceImpl() {
		totalElements = computerDao.getNumberOfElements();
	}

	@Override
	public Page<Computer> getAll(Page<Computer> computerPage) {
		// Set number of pages and rounds to the upper integer if the division
		// has a remainder
		computerPage.setNumberOfElements(totalElements);
		int numberOfPages = totalElements / computerPage.getPageSize();

		if ((totalElements % computerPage.getPageSize()) != 0) {
			computerPage.setNumberOfPages(numberOfPages + 1);
		} else {
			computerPage.setNumberOfPages(numberOfPages);
		}
		
		return computerDao.getAll(computerPage);
	}

	@Override
	public Computer create(Computer computer) {
		totalElements++;

		return computerDao.create(computer);
	}

	@Override
	public Computer getById(long id) {

		return computerDao.getById(id);
	}

	@Override
	public Computer update(Computer computer) {

		return computerDao.update(computer);
	}

	@Override
	public void delete(long id) {
		totalElements--;
		
		computerDao.delete(id);
	}

	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {

		return computerDao.searchByName(computerPage, name);
	}

	@Override
	public void deleteComputersByCompanyId(long companyId) {
		
		computerDao.deleteComputersByCompanyId(companyId);
		totalElements = computerDao.getNumberOfElements();
	}
}
