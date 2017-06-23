package com.excilys.cdb.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.User;
import com.excilys.cdb.persistence.UserDao;
import com.excilys.cdb.service.UserService;

/**
 * This class is the intermediary between user interface and User DAO. Transmits
 * queries to the DAO.
 * 
 * @author Elyas Albay
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	
	@Override
	public User create(User user) {
		
		return userDao.create(user);
	}

	@Override
	public User getById(long id) {
		
		return userDao.getById(id);
	}

	@Override
	public User update(User user) {
		
		return userDao.update(user);
	}

	@Override
	public void delete(long id) {
		
		userDao.delete(id);
	}

}
