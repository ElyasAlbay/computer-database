package com.excilys.cdb.ui;

import java.util.Scanner;

/**
 * Command Line Interface class. Using Singleton pattern.
 * @author excilys
 *
 */
public class UserInterface {
	private static UserInterface uiInstance = null;
	private Scanner scanner;
	
	
	/**
	 * Private constructor for the User Interface.
	 */
	private UserInterface () {
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
	 * Gets the next line written by the user.
	 * @return Input command.
	 */
	public String getNextLine() {
		return scanner.nextLine();
	}
	
	
	
	/*public static void main(String[] args) {
		Scanner lineSc;
		String line;
		UserInterface instance = getInstance();
		
		System.out.println("Saisir une commande :");
		line = instance.getNextLine();
		lineSc = new Scanner(line);
		
		System.out.println(line);
		System.out.println(lineSc.next());
		System.out.println(lineSc.next());
	}*/

}
