package com.excilys.cdb.ui;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;


/**
 * Display class. This class displays appropriate information to the user.
 * @author Elyas Albay
 *
 */
public class Display {
	
	/**
	 * Display a list of companies.
	 * @param companyList List of Company objects.
	 */
	public void displayListCompany(Page<Company> companyPage) {
		if(companyPage != null) {
			for(Company c : companyPage.getElementList()) {
				if(c != null) {
					System.out.println(c.getName());
				}
			}
		} else {
			System.out.println("Empty page");
		}
	}
	
	/**
	 * Display a list of computers.
	 * @param computerList List of Computer objects.
	 */
	public void displayListComputer(Page<Computer> computerPage) {
		if(computerPage != null) {
			for(Computer c : computerPage.getElementList()) {
				if(c != null) {
					System.out.println(c.getName());
				}
			}
		} else {
			System.out.println("Empty page");
		}
	}
	
	/**
	 * Display details of a computer.
	 * @param computer Computer object.
	 */
	public void displayShow(Computer computer) {
		if (computer != null) {
			System.out.println(computer.toString());
		} else {
			System.out.println("Computer not found.");
		}
	}
	
	/**
	 * Display help message.
	 */
	public void displayHelp() {
		System.out.println("Here is a list of all valid commands :");
		for(Command comm : Command.values()){
			if(comm.equals(Command.LIST)) {
				System.out.println(comm.toString() + " 'table'");
			} else if(comm.equals(Command.SHOW)) {
				System.out.println(comm.toString() + " 'id'");
			} else if(comm.equals(Command.CREATE)) {
				System.out.println(comm.toString());
			} else if(comm.equals(Command.UPDATE)) {
				System.out.println(comm.toString() + " 'id'");
			} else if(comm.equals(Command.DELETE)) {
				System.out.println(comm.toString() + " 'id'");
			} else {
				System.out.println(comm.toString());
			}
		}
	}
	
}
