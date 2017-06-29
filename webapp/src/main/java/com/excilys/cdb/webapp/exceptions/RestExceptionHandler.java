package com.excilys.cdb.webapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles exceptions from rest requests.
 * 
 * @author Elyas Albay
 *
 */
public class RestExceptionHandler {

	public static void throwEntityNotFoundException(String message) {
		throw new EntityNotFoundException(message);
	}

	public static void throwInvalidParameterException(String message) {
		throw new InvalidParameterException(message);
	}

}

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This element does not exist in database.")
class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8238509109561566054L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid parameter.")
class InvalidParameterException extends RuntimeException {
	private static final long serialVersionUID = 1444153732055450803L;

	public InvalidParameterException(String message) {
		super(message);
	}
}
