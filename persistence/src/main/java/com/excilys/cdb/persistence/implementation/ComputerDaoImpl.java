package com.excilys.cdb.persistence.implementation;

import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.model.QCompany;
import com.excilys.cdb.model.QComputer;
import com.excilys.cdb.model.util.Field;
import com.excilys.cdb.persistence.ComputerDao;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * Implementation of ComputerDao, sends requests to the database to manipulate computer table.
 * 
 * @author Elyas Albay
 *
 */
@Repository
@Transactional
public class ComputerDaoImpl implements ComputerDao {
	private static final Logger LOG = LoggerFactory.getLogger(ComputerDaoImpl.class);

	private QComputer qComputer = QComputer.computer;
	private QCompany qCompany = QCompany.company;

	@Autowired
	private SessionFactory sessionFactory;
	private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
			sessionFactory.getCurrentSession());

	@Override
	public Page<Computer> getAll(Page<Computer> computerPage) {
		LOG.info("getAll request.");
		LOG.debug("Listing computers. Page size=" + computerPage.getPageSize() + ", page number="
				+ computerPage.getPageNumber() + ", order by " + computerPage.getOrder() + "...");

		// Initialization of parameters for query
		int pageSize = computerPage.getPageSize();
		int pageOffset = (computerPage.getPageNumber() - 1) * computerPage.getPageSize();
		OrderSpecifier<?> order = getOrderSpecifier(computerPage.getOrder());

		// Query to database
		List<Computer> computerList = queryFactory.get().selectFrom(qComputer).leftJoin(qComputer.company, qCompany)
				.limit(pageSize).offset(pageOffset).orderBy(order).fetch();

		computerPage.setElementList(computerList);

		return computerPage;
	}

	@Override
	public Computer create(Computer computer) {
		LOG.info("create request.");
		LOG.debug("Creating computer " + computer.toString() + "...");

		sessionFactory.getCurrentSession().save(computer);

		return computer;
	}
	
	@Override
	public Computer getById(long id) {
		LOG.info("getById request.");
		LOG.debug("Searching computer with id=" + id + "...");

		Computer computer = queryFactory.get().selectFrom(qComputer).leftJoin(qComputer.company, qCompany)
				.where(qComputer.id.eq(id)).fetchOne();

		return computer;
	}

	@Override
	public Computer update(Computer computer) {
		LOG.info("update request.");
		LOG.debug("Updating computer with id=" + computer.getId() + "...");

		queryFactory.get().update(qComputer).where(qComputer.id.eq(computer.getId()))
				.set(qComputer.name, computer.getName())
				.set(qComputer.introduced, computer.getIntroduced())
				.set(qComputer.discontinued, computer.getDiscontinued())
				.set(qComputer.company, computer.getCompany()).execute();

		return computer;
	}

	@Override
	public void delete(long id) {
		LOG.info("delete request.");
		LOG.debug("Deleting computer with id=" + id + "...");

		queryFactory.get().delete(qComputer).where(qComputer.id.eq(id)).execute();
	}
	
	@Override
	public Page<Computer> searchByName(Page<Computer> computerPage, String name) {
		LOG.info("searchByName request.");
		LOG.debug("Searching computers with name or company name=" + name + "...");

		// Initialization of parameters for query
		int pageSize = computerPage.getPageSize();
		int pageOffset = (computerPage.getPageNumber() - 1) * computerPage.getPageSize();
		OrderSpecifier<?> order = getOrderSpecifier(computerPage.getOrder());

		// Query to database
		List<Computer> computerList = queryFactory.get().selectFrom(qComputer).leftJoin(qComputer.company, qCompany)
				.limit(pageSize).offset(pageOffset).orderBy(order)
				.where(qComputer.name.like(name + "%").or(qComputer.company.name.like(name + "%"))).fetch();

		computerPage.setElementList(computerList);
		getNbSearchElements(computerPage, name);

		return computerPage;
	}

	@Override
	public void deleteComputersByCompanyId(long companyId) {
		LOG.info("deleteComputerByCompanyId request.");
		LOG.debug("Deleting computers with company_id=" + companyId + "...");

		queryFactory.get().delete(qComputer).where(qComputer.company.id.eq(companyId)).execute();
	}

	@Override
	public int getNumberOfElements() {
		LOG.info("getNumberOfElements request.");

		return (int) queryFactory.get().from(qComputer).fetchCount();
	}

	/**
	 * Gets number of computers in search query.
	 * 
	 * @return Count of computers.
	 */
	private void getNbSearchElements(Page<Computer> computerPage, String name) {
		LOG.info("getNbSearchElements request.");

		computerPage.setNumberOfElements((int) queryFactory.get().from(qComputer)
				.where(qComputer.name.like(name + "%").or(qCompany.name.like(name + "%"))).fetchCount());

		// set number of elements in search query and rounds to the upper
		// integer if the division has a remainder
		int numberOfPages = computerPage.getNumberOfElements() / computerPage.getPageSize();

		if ((computerPage.getNumberOfElements() % computerPage.getPageSize()) != 0) {
			computerPage.setNumberOfPages(numberOfPages + 1);
		} else {
			computerPage.setNumberOfPages(numberOfPages);
		}
	}

	/**
	 * Get order specifier to order elements by.
	 * 
	 * @param order
	 *            Column to order by.
	 * @return Order Specifier.
	 */
	private OrderSpecifier<?> getOrderSpecifier(String order) {
		OrderSpecifier<?> orderSpecifier;

		switch (order) {
		case Field.COMPUTER_NAME:
			orderSpecifier = qComputer.name.asc();
			break;
		case Field.INTRODUCED:
			orderSpecifier = qComputer.introduced.asc();
			break;
		case Field.DISCONTINUED:
			orderSpecifier = qComputer.discontinued.asc();
			break;
		case Field.COMPANY_NAME:
			orderSpecifier = qComputer.company.name.asc();
			break;
		default:
			orderSpecifier = qComputer.id.asc();
			break;
		}

		return orderSpecifier.nullsLast();
	}
}
