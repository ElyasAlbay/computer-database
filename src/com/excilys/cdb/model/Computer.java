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
	public Computer (int pId, String pName) {
		setId(pId);
		setName(pName);
	}
	
	
	/* Getters and setters */
	public int getId () {
		return this.id;
	}
	
	public void setId (int pId) {
		if (pId >= 0) {
			this.id = pId;
		} else {
			System.out.println("Error in Computer : Incorrect Id");
		}
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String pName) {
		this.name = pName;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate pIntroduced) {
		this.introduced = pIntroduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate pDiscontinued) {
		if(this.getIntroduced() != null) {
			
			if (pDiscontinued.isAfter(this.getIntroduced())) {
				this.discontinued = pDiscontinued;
			} else {
				System.out.println("Error in Computer : Incorrect Discontinued date");
			}
			
		} else {
			this.discontinued = pDiscontinued;
		}
		
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int pCompanyId) {
		this.companyId = pCompanyId;
	}
}
