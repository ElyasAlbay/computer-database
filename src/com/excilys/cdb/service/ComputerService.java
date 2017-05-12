package com.excilys.cdb.service;

import com.excilys.cdb.model.Computer;

/**
 * Interface implemented by Computer service.
 * @author excilys
 *
 */
public interface ComputerService extends BaseService<Computer> {
	public Computer create(Computer computer);
	
	public Computer update(Computer computer);
	
	public void delete(int id);
}
