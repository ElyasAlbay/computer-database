package com.excilys.cdb.webui.utility;

/**
 * This class contains fields names.
 * 
 * @author Elyas Albay
 *
 */
public class Field {
	public static final String COMPUTER_ID = "id";
	public static final String COMPUTER_NAME = "name";
	public static final String INTRODUCED = "introduced";
	public static final String DISCONTINUED = "discontinued";
	public static final String COMPANY_ID = "company.id";
	public static final String COMPANY_NAME = "company.name";


	public static boolean isValidOrder(String field) {
		boolean contains = false;
		
		if (field.equals(COMPUTER_ID)) {
			contains = true;
		} else if (field.equals(COMPUTER_NAME)) {
			contains =  true;
		} else if (field.equals(INTRODUCED)) {
			contains =  true;
		} else if (field.equals(DISCONTINUED)) {
			contains =  true;
		} else if (field.equals(COMPANY_NAME)) {
			contains =  true;
		}

		return contains;
	}
}
