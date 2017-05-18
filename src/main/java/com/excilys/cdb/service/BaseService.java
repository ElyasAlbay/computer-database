package com.excilys.cdb.service;

import com.excilys.cdb.ui.Page;

/**
 * Interface inherited by each service interface.
 * @author Elyas Albay
 *
 */
public interface BaseService<T> {
	
	public Page<T> listRequest(Page<T> page);
	public T getById(int id);
}
