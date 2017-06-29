package com.excilys.cdb.service;

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
	 * Calls corresponding method of DAO instance to create an element in the
	 * database.
	 * 
	 * @param element
	 *            Element to create.
	 * @return Element.
	 */
	public T create(T element);

	/**
	 * Calls corresponding method of DAO instance to get an element by its id in
	 * the dabatase.
	 * 
	 * @param id
	 *            Identifier of the element in the database.
	 * @return Element.
	 */
	public T getById(long id);

	/**
	 * Calls corresponding method of DAO instance to update an element in the
	 * database.
	 * 
	 * @param element
	 *            Element to update.
	 * @return Element.
	 */
	public T update(T element);

	/**
	 * Calls corresponding method of DAO instance to delete an element by its id
	 * in the dabatase.
	 * 
	 * @param id
	 *            Identifier of the element in the database.
	 * @Return Amount of deleted elements.
	 */
	public long delete(long id);
}
