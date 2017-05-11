package com.excilys.cdb.service;

import java.util.List;

/**
 * Interface inherited by each service interface.
 * @author excilys
 *
 */
public interface BaseService<T> {
	
	public List<T> listRequest();
	public T getById(int id);
	public T getByName(String name);
}
