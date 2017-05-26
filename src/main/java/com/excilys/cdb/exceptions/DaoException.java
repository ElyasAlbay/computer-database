package com.excilys.cdb.exceptions;

/**
 * New exception for issus during access to the database.
 * 
 * @author Elyas Albay
 *
 */
public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 7779567413589384208L;

	
	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
