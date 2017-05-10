package com.excilys.cdb.persistence;

public interface CompanyDao extends BaseDao {
	
	public void getById (int id);
	public void getByName (String name);
}
