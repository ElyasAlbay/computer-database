package com.excilys.cdb.persistence;

import com.excilys.cdb.ui.Page;

/**
 * Interface inherited by each DAO interface.
 * @author Elyas Albay
 *
 * @param <T> Computer or Company class.
 */
public interface BaseDao<T> {
	
	public Page<T> listRequest(Page<T> page);
	public T getById(int id);
}
