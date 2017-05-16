package com.excilys.cdb.ui;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exceptions.InvalidCommandException;


/**
 * Command Line Interface class. Using Singleton pattern.
 * @author Elyas Albay
 *
 */
public enum UserInterface {
	INSTANCE;
	
	private Parser parser;
	private Scanner scanner;
	final static Logger LOGGER = LoggerFactory.getLogger(Parser.class);
	
	/**
	 * Private constructor for the User Interface.
	 */
	private UserInterface () {
		parser = new Parser();
		scanner = new Scanner(System.in);
	}
	
	
	/**
	 * Loops while user inputs lines. Breaks if method parseLine() returns false.
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
				LOGGER.error(e.getMessage());
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
