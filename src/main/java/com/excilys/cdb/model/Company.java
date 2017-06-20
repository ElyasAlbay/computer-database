package com.excilys.cdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model for a company.
 * 
 * @author Elyas Albay
 *
 */
@Entity
@Table(name="company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
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
	public Company(long id) {
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
	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
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
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
