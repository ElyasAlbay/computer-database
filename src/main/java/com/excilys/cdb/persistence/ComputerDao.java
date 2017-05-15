package com.excilys.cdb.persistence;

import com.excilys.cdb.model.Computer;

/**
 * Interface implemented by Computer DAO.
 * @author Elyas Albay
 *
 */
public interface ComputerDao extends BaseDao<Computer> {
	public Computer create(Computer computer);
	
	public Computer update(Computer computer);
	
	public void delete(int id);
}
