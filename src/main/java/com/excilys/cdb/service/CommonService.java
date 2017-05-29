package com.excilys.cdb.service;

import com.excilys.cdb.model.Page;

/**
 * Interface inherited by each service interface.
 * 
 * @author Elyas Albay
 *
 * @param <T>
 *            Element model.
 */
public interface CommonService<T> {

	/**
	 * Calls corresponding method of DAO instance to get a page of all elements
	 * in the database.
	 * 
	 * @return List of elements.
	 */
	public Page<T> getAll(Page<T> page);

	/**
	 * Calls corresponding method of DAO instance to get an element by its id in
	 * the dabatase.
	 * 
	 * @param id
	 *            Identifier of the element in the database.
	 * @return Instance of element.
	 */
	public T getById(int id);

	/**
	 * Calls corresponding method of DAO instance to delete an element by its id in
	 * the dabatase.
	 * 
	 * @param id
	 *            Identifier of the element in the database.
	 */
	public void delete(int id);
}
