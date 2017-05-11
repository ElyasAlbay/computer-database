package com.excilys.cdb.persistence;

import java.util.List;

/**
 * Interface inherited by each DAO interface.
 * @author excilys
 *
 * @param <T>
 */
public interface BaseDao<T> {
	
	public List<T> listRequest();
	public T getById(int id);
	public T getByName(String name);
}
