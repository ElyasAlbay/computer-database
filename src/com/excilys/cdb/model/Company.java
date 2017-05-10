package com.excilys.cdb.model;

/**
 * Model for company.
 * @author excilys
 *
 */
public class Company {
	private int id;
	private String name;
	
	
	/**
	 * Constructor for Company class.
	 * @param pId
	 * @param pName
	 */
	public Company (int pId, String pName) {
		setId(pId);
		setName(pName);
	}
	
	
	/* Getters and setters */
	public int getId () {
		return this.id;
	}
	
	public void setId (int pId) {
		if(pId >= 0) {
			this.id = pId;
		}
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String pName) {
		this.name = pName;
	}
}
