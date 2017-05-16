package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
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
	
	
	/**
	 * Calls corresponding method of DAO instance to get a list of all companies in the database.
	 * @return List of Company instances.
	 */
	@Override
	public List<Company> listRequest() {
		
		return companyDao.listRequest();
	}

	/**
	 * Calls corresponding method of DAO instance to get a company by its id in the dabatase.
	 * @param id Identifier of the company in the database.
	 * @return Instance of Company.
	 */
	@Override
	public Company getById(int id) {
		
		return companyDao.getById(id);
	}

}
