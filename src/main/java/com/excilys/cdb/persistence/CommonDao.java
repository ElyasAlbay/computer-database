package com.excilys.cdb.persistence;

import java.sql.Connection;

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
	public Page<T> getAll(Page<T> page);

	/**
	 * Sends a request to the database to get a unique element identified by the
	 * given id.
	 * 
	 * @param id
	 *            Identifier of the element in the dabatase.
	 * @return Element.
	 */
	public T getById(int id);

	/**
	 * Sends a request to the database to delete a given entry of the element
	 * table in the database.
	 * 
	 * <i> For a company deletion, uses a transaction system to ensure all
	 * related computers (i.e. which foreign id identifies the company) are
	 * safely deleted before comitting changes.
	 * 
	 * 
	 * @param id
	 *            Identifier of the element in the database.
	 */
	public void delete(int id, Connection connection);
}
