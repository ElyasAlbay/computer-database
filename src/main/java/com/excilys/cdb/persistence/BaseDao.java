package com.excilys.cdb.persistence;

import java.util.List;

/**
 * Interface inherited by each DAO interface.
 * @author Elyas Albay
 *
 * @param <T> Computer or Company class.
 */
public interface BaseDao<T> {
	
	public List<T> listRequest();
	public T getById(int id);
}
