package com.excilys.cdb.console;

import java.util.Scanner;

import com.excilys.cdb.console.util.InvalidCommandException;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Command Line Interface class. Using Singleton pattern.
 * 
 * @author Elyas Albay
 *
 */
public class UserInterface {
	private Parser parser;
	private Scanner scanner;

	/**
	 * Private constructor for the User Interface.
	 */
	public UserInterface(ComputerService computerService, CompanyService companyService) {
		parser = new Parser(computerService, companyService);
		scanner = new Scanner(System.in);
	}

	/**
	 * Loops while user inputs lines. Breaks if method parseLine() returns
	 * false.
	 */
	public void getUserInput() {
		System.out.println("Please type 'help' to get a list of valid commands.");
		System.out.println("Awaiting input...");
		boolean parse = true;

		while (parse && scanner.hasNextLine()) {
			try {
				parse = parser.parseLine(scanner.nextLine());
			} catch (InvalidCommandException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
