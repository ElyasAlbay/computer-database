package com.excilys.cdb.persistence.implementation;

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
import com.excilys.cdb.persistence.CompanyDao;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * Implementation of CompanyDao, sends requests to the database to manipulate company table.
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
		LOG.debug("Listing companies...");

		List<Company> companyList = queryFactory.get().selectFrom(qCompany).fetch();
		companyPage.setElementList(companyList);

		return companyPage;
	}

	@Override
	public Company create(Company company) {
		LOG.info("create request.");
		LOG.debug("Creating company " + company.toString() + "...");

		sessionFactory.getCurrentSession().save(company);
		
		return company;
	}
	
	@Override
	public Company getById(long id) {
		LOG.info("getById request.");
		LOG.debug("Searching company with id=" + id + "...");
		
		Company company = queryFactory.get().selectFrom(qCompany).where(qCompany.id.eq(id)).fetchOne();

		return company;
	}
	
	@Override
	public Company update(Company company) {
		LOG.info("update request.");
		LOG.debug("Updating company with id=" + company.getId() + "...");

		queryFactory.get().update(qCompany).where(qCompany.id.eq(company.getId()))
				.set(qCompany.name, company.getName()).execute();
		
		return company;
	}

	@Override
	public long delete(long id) {
		LOG.info("delete request.");
		LOG.debug("Deleting computer with id=" + id + "...");

		return queryFactory.get().delete(qCompany).where(qCompany.id.eq(id)).execute();
	}
	
}
