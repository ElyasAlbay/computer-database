package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;


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
	public void displayListCompany(List<Company> companyList) {
		if(companyList != null) {
			for(Company c : companyList) {
				if(c != null) {
					System.out.println(c.getName());
				}
			}
		}
	}
	
	/**
	 * Display a list of computers.
	 * @param computerList List of Computer objects.
	 */
	public void displayListComputer(List<Computer> computerList) {
		if(computerList != null) {
			for(Computer c : computerList) {
				if(c != null) {
					System.out.println(c.getName());
				}
			}
		}
	}
	
	/**
	 * Display details of a computer.
	 * @param computer Computer object.
	 */
	public void displayShow(Computer computer) {
		if (computer != null) {
			System.out.println(computer.toString());
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
