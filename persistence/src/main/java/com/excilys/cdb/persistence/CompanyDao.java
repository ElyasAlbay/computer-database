package com.excilys.cdb.persistence;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;

/**
 * Interface implemented by Company DAO.
 * 
 * @author Elyas Albay
 *
 */
public interface CompanyDao extends CommonDao<Company> {
	/**
	 * Sends a request to the database to get a page of companies.
	 * 
	 * @return Page of companies.
	 */
	public Page<Company> getAll(Page<Company> page);
}
