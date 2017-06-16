package com.excilys.cdb.persistence;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.utility.mapper.CompanyMapper;

/**
 * Implementation of CompanyDao, sends requests to the database and gets an
 * instance of Company from the corresponding Mapper.
 * 
 * @author Elyas Albay
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String LIST = "SELECT * FROM company";
	private final static String GET_BY_ID = "SELECT * FROM company WHERE id=?";
	private final static String DELETE = "DELETE FROM company WHERE id=?";
	private final static String NUMBER_OF_ELEMENTS = "SELECT count(*) FROM company";


	@Override
	public Page<Company> getAll(Page<Company> companyPage) {
		LOG.info("getAll request.");

		List<Company> companyList = this.jdbcTemplate.query(LIST, new CompanyMapper());
		companyPage.setElementList(companyList);
		getNumberOfElements(companyPage);

		return companyPage;
	}

	@Override
	public Company getById(int id) {
		LOG.info("getById request.");

		Company company = this.jdbcTemplate.queryForObject(GET_BY_ID, new Object[] { id }, new CompanyMapper());

		return company;
	}

	@Override
	public void delete(int id) {
		LOG.info("getById request.");

		this.jdbcTemplate.update(DELETE, id);
	}

	/**
	 * Gets number of companies in the database.
	 * 
	 * @param companyPage Page of companies to edit.
	 */
	private void getNumberOfElements(Page<Company> companyPage) {
		LOG.info("getNumberOfElements request.");

		companyPage.setNumberOfElements(this.jdbcTemplate.queryForObject(NUMBER_OF_ELEMENTS, Integer.class));

		// Set number of pages, rounds to the upper integer if the division has a remainder
		int numberOfPages = companyPage.getNumberOfElements() / companyPage.getPageSize();

		if ((companyPage.getNumberOfElements() % companyPage.getPageSize()) != 0) {
			companyPage.setNumberOfPages(numberOfPages + 1);
		} else {
			companyPage.setNumberOfPages(numberOfPages);
		}
	}

}
