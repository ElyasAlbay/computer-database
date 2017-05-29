package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.DaoException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.CompanyDaoImpl;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.ComputerDaoImpl;
import com.excilys.cdb.persistence.utility.DbConnection;


/**
 * This class is the intermediary between user interface and Company DAO.
 * Transmits queries to the DAO.
 * @author Elyas Albay
 *
 */
public enum CompanyServiceImpl implements CompanyService {
	INSTANCE;
	
	private static final Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	DbConnection dbConnection;
	CompanyDao companyDao;
	

	/**
	 * Class constructor. Instantiates DAO.
	 */
	private CompanyServiceImpl () {
		dbConnection = DbConnection.INSTANCE;
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
		ComputerDao computerDao = ComputerDaoImpl.INSTANCE;
		Connection connection = dbConnection.openConnection();
		
		try {
			connection.setAutoCommit(false);
			computerDao.deleteComputersByCompanyId(id, connection);
			companyDao.delete(id, connection);
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOG.error("Unable to roll back. " + e.getMessage());
				throw new DaoException("Rollback has failed: " + e.getMessage());
			}
			
			throw new DaoException("Transaction to delete company has failed: " + e.getMessage());
		} finally {
			dbConnection.closeConnection();
		}
		
	}
}
