package com.excilys.cdb.persistence;

import com.excilys.cdb.model.*;

public interface ComputerDao extends BaseDao {

	public void showDetails(int id);
	
	public void showDetails(String name);
	
	public void create(Computer computer);
	
	public void update(Computer computer);
	
	public void delete(int id);
}
