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
	 * @param id Unique identifier for the company.
	 * @param pName Company name.
	 */
	public Company (int id, String name) {
		setId(id);
		setName(name);
	}
	
	
	/* Getters and setters */
	public int getId () {
		return this.id;
	}
	
	public void setId (int id) {
		if(id >= 0) {
			this.id = id;
		} else {
			System.out.println("Error in Company : Incorrect Id");
		}
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
}
