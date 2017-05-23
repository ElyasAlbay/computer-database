package com.excilys.cdb.webui.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.CompanyServiceImpl;


public class Validator {
	private static CompanyService companyService;
	
	
	/**
	 * Class constructor.
	 */
	public Validator() {
		companyService = CompanyServiceImpl.INSTANCE;
	}
	
	
	/**
	 * Validates input name.
	 * 
	 * @param name
	 *            Computer name.
	 * @throws Exception
	 *             Thrown exception.
	 */
	public static void nameValidation(String name) throws Exception {

		if (StringUtils.isBlank(name)) {
			throw new Exception("Name has to be specified.");
		}
	}

	/**
	 * Validates input introduced date.
	 * 
	 * @param introduced
	 *            Introduced date.
	 * @throws Exception
	 *             Thrown Exception.
	 */
	public static void introducedValidation(String introduced) throws Exception {

		if (StringUtils.isNotBlank(introduced)) {
			if (!introduced.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new Exception("Invalid introduced date format.");
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
	 * @throws Exception
	 *             Thrown exception.
	 */
	public static void discontinuedValidation(String discontinued, String introduced) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (StringUtils.isNotBlank(discontinued)) {
			if (!discontinued.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new Exception("Invalid discontinued date format.");
			} else if (introduced != null
					&& LocalDate.parse(introduced, formatter).isAfter(LocalDate.parse(discontinued, formatter))) {
				throw new Exception("Discontinued date should not be before introduced date.");
			}
		}
	}

	/**
	 * Validates input company id.
	 * 
	 * @param companyId
	 *            COmpany Id.
	 * @throws Exception
	 *             Thrown Exception.
	 */
	public static void companyIdValidation(String companyId) throws Exception {
		int id = Integer.parseInt(companyId);
		
		if (id < 0) {
			throw new Exception("Company Id cannot be negative.");
		} else if (id != 0 && companyService.getById(id) == null) {
			throw new Exception("This id does not exist in the database." + companyId);
		}
	}

}
