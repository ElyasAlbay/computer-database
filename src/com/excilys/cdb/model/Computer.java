package com.excilys.cdb.model;

import java.sql.Date;

/**
 * Model for computer.
 * @author excilys
 *
 */
public class Computer {
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int companyId;
	
	/**
	 * Constructor for Computer class.
	 * @param pId
	 * @param pName
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
		}
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String pName) {
		this.name = pName;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date pIntroduced) {
		this.introduced = pIntroduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date pDiscontinued) {
		if (pDiscontinued.compareTo(this.discontinued) > 0) {
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
