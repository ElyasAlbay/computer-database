package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 * Model for a computer.
 * @author excilys
 *
 */
public class Computer {
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private int companyId;
	
	/**
	 * Constructor using identifier and name of the computer.
	 * @param pId Unique identifier for the computer.
	 * @param pName Name of the computer.
	 */
	public Computer (int id, String name) {
		setId(id);
		setName(name);
		
	}
	
	
	/**
	 * Converts computer into a string detailing its fields.
	 */
	public String toString() {
		String delimit = " | ";
		String string = this.getId() + delimit + this.getName() + delimit + this.getIntroduced() + delimit + this.getDiscontinued() +delimit + this.getCompanyId();
		
		return string;
	}
	
	
	/* Getters and setters */
	public int getId () {
		return this.id;
	}
	
	public void setId (int id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.err.println("Error in Computer : Incorrect Id");
		}
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {		
		if(discontinued != null) {
			this.discontinued = discontinued;
			
			if (this.getIntroduced() != null && discontinued.compareTo(this.getIntroduced()) < 0) {
				this.discontinued = null;
				System.err.println("Error in Computer " + getId() + ": Incorrect Discontinued date");
			}
		}
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
