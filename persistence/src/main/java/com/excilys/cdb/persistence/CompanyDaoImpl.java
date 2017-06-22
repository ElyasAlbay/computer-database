package com.excilys.cdb.persistence;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.QCompany;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * Implementation of CompanyDao, sends requests to the database and gets one or more
 * instance(s) of Company.
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

		Company company = queryFactory.get().selectFrom(qCompany).where(qCompany.id.eq(id)).fetchOne();

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
