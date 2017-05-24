package com.excilys.cdb.persistence;

import com.excilys.cdb.model.Page;

/**
 * Interface inherited by each DAO interface.
 * 
 * @author Elyas Albay
 *
 * @param <T>
 *            Table model.
 */
public interface CommonDao<T> {

	/**
	 * Sends a request to the database to get a complete list of elements.
	 * 
	 * @return Page of elements.
	 */
	public Page<T> listRequest(Page<T> page);

	/**
	 * Sends a request to the database to get a unique element identified by the
	 * given id.
	 * 
	 * @param id
	 *            Identifier of the element in the dabatase.
	 * @return Element.
	 */
	public T getById(int id);
}
