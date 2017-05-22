package com.excilys.cdb.ui;

import java.util.Scanner;

import com.excilys.cdb.exceptions.InvalidCommandException;

/**
 * Command Line Interface class. Using Singleton pattern.
 * 
 * @author Elyas Albay
 *
 */
public enum UserInterface {
	INSTANCE;

	private Parser parser;
	private Scanner scanner;

	/**
	 * Private constructor for the User Interface.
	 */
	private UserInterface() {
		parser = new Parser();
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
				parse = false;
			}
		}
	}

	/* Getters and setters */
	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

}
