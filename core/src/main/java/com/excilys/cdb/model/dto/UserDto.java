package com.excilys.cdb.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
	@Min(0)
	private long id;
	
	@NotNull
	@Size(min=2, max=30)
	private String name;
	
	@NotNull
	@Size(min=6, max=30)
	private String password;
	
	
	/**
	 * Default class constructor.
	 */
	public UserDto() {
		
	}


	/* Getters and setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
