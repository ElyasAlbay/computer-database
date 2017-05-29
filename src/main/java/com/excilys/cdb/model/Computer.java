package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 * Model for a computer.
 * 
 * @author excilys
 *
 */
public class Computer {
	private int id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;

	
	/**
	 * Default class constructor.
	 */
	public Computer() {
		this.introduced = null;
		this.discontinued = null;
		this.company = null;
	}

	/**
	 * Constructor using identifier of the computer.
	 * 
	 * @param id
	 *            Unique identifier for the computer.
	 */
	public Computer(int id) {
		setId(id);

		this.introduced = null;
		this.discontinued = null;
		this.company = null;
	}

	/**
	 * Constructor using identifier and name of the computer.
	 * 
	 * @param id
	 *            Unique identifier for the computer.
	 * @param name
	 *            Name of the computer.
	 */
	public Computer(int id, String name) {
		setId(id);
		setName(name);

		this.introduced = null;
		this.discontinued = null;
		this.company = null;
	}

	
	/**
	 * Returns Computer instance hash code.
	 * 
	 * @return result Generated Hash Code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Converts computer into a string detailing its fields.
	 */
	@Override
	public String toString() {
		String delimit = " | ";
		String string = this.getId() + delimit + this.getName();

		if (this.getIntroduced() != null) {
			string += delimit + this.getIntroduced();
		} else {
			string += delimit + "null";
		}
		if (this.getDiscontinued() != null) {
			string += delimit + this.getDiscontinued();
		} else {
			string += delimit + "null";
		}
		if (this.getCompany() != null) {
			string += delimit + this.getCompany().toString();
		} else {
			string += delimit + "null";
		}

		return string;
	}

	/* Getters and setters */
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		if (id >= 0) {
			this.id = id;
		} else {
			System.err.println("Error in Computer : Incorrect Id");
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
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
		if (discontinued != null) {
			this.discontinued = discontinued;

			if (this.getIntroduced() != null && discontinued.compareTo(this.getIntroduced()) < 0) {
				this.discontinued = null;
				System.err.println("Error in Computer " + getId() + ": Incorrect Discontinued date");
			}
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
