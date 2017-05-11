package com.excilys.cdb.persistence;

import com.excilys.cdb.model.Computer;

public interface ComputerDao extends BaseDao<Computer> {
	
	public void create(Computer computer);
	
	public void update(Computer computer);
	
	public void delete(int id);
}
