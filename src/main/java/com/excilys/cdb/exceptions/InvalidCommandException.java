package com.excilys.cdb.exceptions;

/**
 * New exception for invalid user input.
 * 
 * @author Elyas Albay
 *
 */
public class InvalidCommandException extends RuntimeException {
	private static final long serialVersionUID = 27008857691171309L;

	
	public InvalidCommandException() {
		
	}

	public InvalidCommandException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidCommandException(String message) {
		super(message);
	}

	public InvalidCommandException(Throwable cause) {
		super(cause);
	}

}
