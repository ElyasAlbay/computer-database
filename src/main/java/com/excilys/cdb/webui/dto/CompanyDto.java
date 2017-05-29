package com.excilys.cdb.webui.dto;

/**
 * Dto for Company object.
 * 
 * @author Elyas Albay
 *
 */
public class CompanyDto {
	private int id;
	private String name;

	
	/**
	 * Class constructor.
	 */
	public CompanyDto() {

	}
	

	/* Getters and setters */
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		if (id >= 0) {
			this.id = id;
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
