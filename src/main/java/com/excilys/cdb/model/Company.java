package com.excilys.cdb.model;

/**
 * Model for a company.
 * 
 * @author Elyas Albay
 *
 */
public class Company {
	private int id;
	private String name;

	
	/**
	 * Class default constructor.
	 */
	public Company() {

	}

	/**
	 * Constructor using identifier of the company.
	 * 
	 * @param id
	 *            Unique identifier for the company.
	 */
	public Company(int id) {
		this.id = id;
	}
	
	/**
	 * Constructor using identifier and name of the company.
	 * 
	 * @param id
	 *            Unique identifier for the company.
	 * @param pName
	 *            Company name.
	 */
	public Company(int id, String name) {
		this.id = id;
		this.name = name;
	}

	
	/**
	 * Returns Company instance hash code.
	 * 
	 * @return result Generated Hash Code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Returns true if instance is equal to the paramater. False else.
	 * 
	 * @param obj
	 *            Object to compare.
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Converts company to a string detailing its fields.
	 */
	@Override
	public String toString() {
		String delimit = " | ";
		String string = this.getId() + delimit + this.getName();

		return string;
	}

	/* Getters and setters */
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
