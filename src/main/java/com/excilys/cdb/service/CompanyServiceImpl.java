package com.excilys.cdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ComputerDao;

/**
 * This class is the intermediary between user interface and Company DAO.
 * Transmits queries to the DAO.
 * 
 * @author Elyas Albay
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	CompanyDao companyDao;
	@Autowired
	ComputerDao computerDao;

	
	/**
	 * Class constructor.
	 */
	public CompanyServiceImpl() {

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
	@Transactional
	public void delete(int id) {
		LOG.info("Company deletion...");
		
		computerDao.deleteComputersByCompanyId(id);
		companyDao.delete(id);
	}
}
