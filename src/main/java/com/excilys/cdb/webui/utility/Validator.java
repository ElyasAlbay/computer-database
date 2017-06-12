package com.excilys.cdb.webui.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.excilys.cdb.exceptions.ValidationException;

/**
 * Validates fields received from post request.
 * 
 * @author Elyas Albay
 *
 */
public class Validator {

	/**
	 * Class constructor.
	 */
	private Validator() {

	}

	
	/**
	 * Validates input name.
	 * 
	 * @param name
	 *            Computer name.
	 * @throws ValidationException
	 *             Thrown exception.
	 */
	public static void nameValidation(String name) throws ValidationException {

		if (StringUtils.isBlank(name)) {
			throw new ValidationException("Name has to be specified.");
		}
	}

	/**
	 * Validates input introduced date.
	 * 
	 * @param introduced
	 *            Introduced date.
	 * @throws ValidationException
	 *             Thrown exception.
	 */
	public static void introducedValidation(String introduced) throws ValidationException {

		if (StringUtils.isNotBlank(introduced)) {
			if (!introduced.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new ValidationException("Invalid introduced date format.");
			}
		}
	}

	/**
	 * Validates input discontinued date.
	 * 
	 * @param discontinued
	 *            Discontinued date.
	 * @param introduced
	 *            Introduced date.
	 * @throws ValidationException
	 *             Thrown exception.
	 */
	public static void discontinuedValidation(String discontinued, String introduced) throws ValidationException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (StringUtils.isNotBlank(discontinued)) {
			if (!discontinued.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new ValidationException("Invalid discontinued date format.");
			} else if (introduced != null
					&& LocalDate.parse(introduced, formatter).isAfter(LocalDate.parse(discontinued, formatter))) {
				throw new ValidationException("Discontinued date cannot be before introduced date.");
			}
		}
	}

	/**
	 * Validates input company id.
	 * 
	 * @param companyId
	 *            Company Id.
	 * @throws ValidationException
	 *             Thrown exception.
	 */
	public static void companyIdValidation(String companyId) throws ValidationException {		
		if (StringUtils.isNotBlank(companyId)) {
			int id = Integer.parseInt(companyId);
			
			if (id < 0) {
				throw new ValidationException("Company Id cannot be negative.");
			}
		}
	}

}
