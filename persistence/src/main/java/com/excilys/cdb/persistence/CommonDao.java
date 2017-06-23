package com.excilys.cdb.persistence;

/**
 * Interface inherited by each DAO interface. Provides CRUD requests.
 * 
 * @author Elyas Albay
 *
 * @param <T>
 *            Table model.
 */
public interface CommonDao<T> {

	/**
	 * Sends a request to the database to insert an element in the
	 * corresponding table.
	 * 
	 * @param element
	 *            Element to insert.
	 * @return Element.
	 */
	public T create(T element);

	/**
	 * Sends a request to the database to get a unique element identified by the
	 * given id.
	 * 
	 * @param id
	 *            Identifier of the element in the dabatase.
	 * @return Element.
	 */
	public T getById(long id);
	
	/**
	 * Sends a request to the database to update a given entry in the
	 * corresponding table.
	 * 
	 * @param computer
	 *            Instance of Computer to update.
	 */
	public T update(T element);	

	/**
	 * Sends a request to the database to delete a given entry of the element
	 * table in the database.
	 * 
	 * For a company deletion, uses a <i>transaction</i> system to ensure all
	 * related computers (i.e. which foreign id identifies the company) are
	 * safely deleted before comitting changes.
	 * 
	 * 
	 * @param id
	 *            Identifier of the element in the database.
	 */
	public void delete(long id) ;
}
