package com.excilys.cdb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Model for a user.
 * 
 * @author Elyas Albay
 *
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Role> roles = new HashSet<>(0);

	
	/**
	 * Default class constructor.
	 */
	public User() {

	}

	/**
	 * Constructor using user name and password.
	 * 
	 * @param name
	 *            User name.
	 * @param password
	 *            User password.
	 */
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.enabled = true;
	}

	/**
	 * Constructor using user name, password and set of roles.
	 * 
	 * @param name
	 *            User name.
	 * @param password
	 *            User password.
	 * @param roles
	 *            User roles.
	 */
	public User(String name, String password, Set<Role> roles) {
		this.name = name;
		this.password = password;
		this.enabled = true;
		this.roles = roles;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		User other = (User) obj;
		if (enabled != other.enabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + "]";
	}
	
	
	/* Getters and setters */
	public String getName() {
		return this.name;
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
	
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
