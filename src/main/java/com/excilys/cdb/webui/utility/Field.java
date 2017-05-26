package com.excilys.cdb.webui.utility;

/**
 * This class contains fields names.
 * 
 * @author Elyas Albay
 *
 */
public class Field {
	public static final String COMPUTER_ID = "computer.id";
	public static final String COMPUTER_NAME = "computer.name";
	public static final String INTRODUCED = "introduced";
	public static final String DISCONTINUED = "discontinued";
	public static final String COMPANY_ID = "company_id";
	public static final String COMPANY_NAME = "company_name";


	public static boolean contains(String field) {
		if (field.equals(COMPUTER_ID)) {
			return true;
		} else if (field.equals(COMPUTER_NAME)) {
			return true;
		} else if (field.equals(INTRODUCED)) {
			return true;
		} else if (field.equals(DISCONTINUED)) {
			return true;
		} else if (field.equals(COMPANY_NAME)) {
			return true;
		}

		return false;
	}
}
