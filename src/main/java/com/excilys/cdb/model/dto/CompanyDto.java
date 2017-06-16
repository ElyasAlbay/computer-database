package com.excilys.cdb.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dto for Company object.
 * 
 * @author Elyas Albay
 *
 */
public class CompanyDto {
	@Min(0)
	private int id;
	
	@NotNull
	@Size(min=2, max=30)
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
