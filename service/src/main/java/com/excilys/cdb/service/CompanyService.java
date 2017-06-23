package com.excilys.cdb.service;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;

/**
 * Interface implemented by Company service.
 * 
 * @author Elyas Albay
 *
 */
public interface CompanyService extends CommonService<Company> {

	/**
	 * Calls corresponding method of DAO instance to get a page of all companies
	 * in the database.
	 * 
	 * @return Page of companies.
	 */
	public Page<Company> getAll(Page<Company> page);
}
