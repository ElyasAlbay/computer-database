package com.excilys.cdb.ui;

import java.util.Scanner;


/**
 * Command Line Interface class. Using Singleton pattern.
 * @author excilys
 *
 */
public class UserInterface {
	private static UserInterface uiInstance = null;
	private Parser parser;
	private Scanner scanner;
	
	
	/**
	 * Private constructor for the User Interface.
	 */
	private UserInterface () {
		parser = new Parser();
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Returns unique instance, or instantiates it if null.
	 * @return Instance of UserInterface.
	 */
	public static UserInterface getInstance() {
		if (uiInstance == null) {
			uiInstance = new UserInterface();
		}
		
		return uiInstance;
	}
	
	/**
	 * Loops while user inputs lines. Breaks if method parseLine() returns false.
	 */
	public void getUserInput() {
		System.out.println("Please type 'help' to get a list of valid commands.");
		System.out.println("Awaiting input...");
		while (scanner.hasNextLine()) {
			
			if (!parser.parseLine(scanner.nextLine())) {
				break;
			}
		}
	}
	

}
