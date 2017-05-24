package com.excilys.cdb.exceptions;

/**
 * New exception for issues during validation process.
 * 
 * @author Elyas Albay
 *
 */
public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 6953566993249706080L;

	
	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
}
