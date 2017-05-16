package com.excilys.cdb.exceptions;

public class InvalidCommandException extends Exception {
	private static final long serialVersionUID = 27008857691171309L;

	/**
	 * Class constructor.
	 */
	public InvalidCommandException() {}
	
	/**
	 * Class constructor.
	 * @param message Exception detail.
	 */
	public InvalidCommandException(String message) {
		super(message);
	}
	
	/**
	 * Class constructor.
	 * @param cause Cause of the exception.
	 */
	public InvalidCommandException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Class constructor.
	 * @param message Exception detail.
	 * @param cause Cause of the exception.
	 */
	public InvalidCommandException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
}
