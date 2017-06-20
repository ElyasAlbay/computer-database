package com.excilys.cdb.persistence;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.QCompany;
import com.excilys.cdb.persistence.utility.mapper.CompanyMapper;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * Implementation of CompanyDao, sends requests to the database and gets an
 * instance of Company from the corresponding Mapper.
 * 
 * @author Elyas Albay
 *
 */
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
	private static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	private QCompany qCompany = QCompany.company;
	@Autowired
	private SessionFactory sessionFactory;
	private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
			sessionFactory.getCurrentSession());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String NUMBER_OF_ELEMENTS = "SELECT count(*) FROM company";

	@Override
	public Page<Company> getAll(Page<Company> companyPage) {
		LOG.info("getAll request.");

		List<Company> companyList = queryFactory.get().selectFrom(qCompany).fetch();
		companyPage.setElementList(companyList);
		getNumberOfElements(companyPage);

		return companyPage;
	}

	@Override
	public Company getById(long id) {
		LOG.info("getById request.");

		Company company = null;
		List<Company> companyList = queryFactory.get().selectFrom(qCompany).where(qCompany.id.eq(id)).fetch();

		if (!companyList.isEmpty()) {
			company = companyList.get(0);
		}

		return company;
	}

	@Override
	public void delete(long id) {
		LOG.info("delete request.");

		queryFactory.get().delete(qCompany).where(qCompany.id.eq(id)).execute();
	}

	@Override
	public void getNumberOfElements(Page<Company> companyPage) {
		LOG.info("getNumberOfElements request.");

		companyPage.setNumberOfElements((int) queryFactory.get().selectFrom(qCompany).fetchCount());

		// Set number of pages, rounds to the upper integer if the division has
		// a remainder
		int numberOfPages = companyPage.getNumberOfElements() / companyPage.getPageSize();

		if ((companyPage.getNumberOfElements() % companyPage.getPageSize()) != 0) {
			companyPage.setNumberOfPages(numberOfPages + 1);
		} else {
			companyPage.setNumberOfPages(numberOfPages);
		}
	}

}
