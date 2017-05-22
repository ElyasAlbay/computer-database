package com.excilys.cdb.webui.dto;

/**
 * 
 * @author Elyas Albay
 *
 */
public class CompanyDto {
	private int id;
	private String name;
	
	
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
