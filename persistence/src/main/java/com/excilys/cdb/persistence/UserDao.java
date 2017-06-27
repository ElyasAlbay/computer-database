package com.excilys.cdb.persistence;

import com.excilys.cdb.model.User;

/**
 * Interface implemented by User DAO.
 * 
 * @author Elyas Albay
 *
 */
public interface UserDao {

	/**
	 * Sends a request to the database to get a unique user identified by the
	 * given name.
	 * 
	 * @param name
	 *            User name in the dabatase.
	 * @return User.
	 */
	public User getByName(String name);
}
