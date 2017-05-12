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
		if(pDiscontinued != null && !pDiscontinued.equals("")) {
			if(this.getIntroduced() != null) {
				if (pDiscontinued.compareTo(this.getIntroduced()) >= 0) {	//pDiscontinued.isAfter(this.getIntroduced()) doesn't work if discontinued = introduced
					this.discontinued = pDiscontinued;
					
				} else {
					System.out.println("Error in Computer : Incorrect Discontinued date");
				}
				
			} else {
				this.discontinued = pDiscontinued;
			}
		} else {
			this.discontinued = null;
		}
		
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int pCompanyId) {
		this.companyId = pCompanyId;
	}
}
