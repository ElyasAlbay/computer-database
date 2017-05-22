package com.excilys.cdb.service;

import com.excilys.cdb.model.Computer;

/**
 * Interface implemented by Computer service.
 * @author Elyas Albay
 *
 */
public interface ComputerService extends CommonService<Computer> {
	public Computer create(Computer computer);
	
	public Computer update(Computer computer);
	
	public void delete(int id);
}
