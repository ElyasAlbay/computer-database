package com.excilys.cdb.service;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.CompanyDaoImpl;


/**
 * This class is the intermediary between user interface and Company DAO.
 * Transmits queries to the DAO.
 * @author Elyas Albay
 *
 */
public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;
	
	CompanyDao companyDao;
	

	/**
	 * Class constructor. Instantiates DAO.
	 */
	private CompanyServiceImpl () {
		
		companyDao = CompanyDaoImpl.INSTANCE;
	}
	
	
	@Override
	public Page<Company> getAll(Page<Company> companyPage) {
		
		return companyDao.getAll(companyPage);
	}

	@Override
	public Company getById(int id) {
		
		return companyDao.getById(id);
	}
	
	@Override
	public void delete(int id) {
		
		companyDao.delete(id);
	}
}
