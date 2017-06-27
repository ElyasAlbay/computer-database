package com.excilys.cdb.persistence.implementation;

import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.QUser;
import com.excilys.cdb.model.User;
import com.excilys.cdb.persistence.UserDao;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

/**
 * User Dao, sends requests to the database to manipulate user table.
 * 
 * @author Elyas Albay
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	private QUser qUser = QUser.user;
	
	@Autowired
	private SessionFactory sessionFactory;
	private Supplier<HibernateQueryFactory> queryFactory = () -> new HibernateQueryFactory(
			sessionFactory.getCurrentSession());
	
	
	@Override
	public User getByName(String name) {
		LOG.info("getByName request.");
		LOG.debug("Searching company with name=" + name + "...");
		
		User user = queryFactory.get().selectFrom(qUser).where(qUser.name.eq(name)).fetchOne();

		return user;
	}
	
	
	
	
}
