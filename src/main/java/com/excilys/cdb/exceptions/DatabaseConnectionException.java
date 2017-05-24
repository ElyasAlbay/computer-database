package com.excilys.cdb.exceptions;

/**
 * New exception for issues during connection to the database.
 * 
 * @author Elyas Albay
 *
 */
public class DatabaseConnectionException extends RuntimeException {
	private static final long serialVersionUID = -7528518047881107912L;

	
	public DatabaseConnectionException() {
		super();
	}

	public DatabaseConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseConnectionException(String message) {
		super(message);
	}

	public DatabaseConnectionException(Throwable cause) {
		super(cause);
	}

}
