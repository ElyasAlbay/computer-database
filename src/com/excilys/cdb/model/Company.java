package com.excilys.cdb.model;

/**
 * Model for a company.
 * @author excilys
 *
 */
public class Company {
	private int id;
	private String name;
	
	
	/**
	 * Constructor using identifier and name of the company.
	 * @param pId Unique identifier for the company.
	 * @param pName Company name.
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
		} else {
			System.out.println("Error in Company : Incorrect Id");
		}
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String pName) {
		this.name = pName;
	}
}
