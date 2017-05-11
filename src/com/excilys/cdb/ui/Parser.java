package com.excilys.cdb.ui;

import java.util.Scanner;

/**
 * Parser for user input. This class parses the input and does appropriate treatment
 * according to the command.
 * @author excilys
 *
 */
public class Parser {
	
	/**
	 * Parses a complete input line.
	 * @param line Input line.
	 * @return loop Boolean indicating if the loop should keep going.
	 */
	public static boolean parseLine(String line) {
		boolean loop = true;
		Scanner scanLine = new Scanner(line);
		String token = scanLine.next();
		
		if (token.equals(Command.LIST.toString())) {
			parseToken(scanLine.next());
			
		} else if (token.equals(Command.SHOW.toString())) {
			token = scanLine.next();
			System.out.println(token);
			
		} else if (token.equals(Command.CREATE.toString())) {
			token = scanLine.next();
			System.out.println(token);
			
		} else if (token.equals(Command.UPDATE.toString())) {
			token = scanLine.next();
			System.out.println(token);
			
		} else if (token.equals(Command.DELETE.toString())) {
			token = scanLine.next();
			System.out.println(token);
			
		} else if (token.equals(Command.QUIT.toString())) {
			loop = false;
			
		} else {
			System.out.println("Incorrect command : command invalid");
		}
		
		scanLine.close();
		
		return loop;
	}
	
	/**
	 * Parses a single input word.
	 * @param token Input word.
	 */
	public static void parseToken(String token) {
		if (token.equals("company")) {
			
		} else if (token.equals("computer")) {
			
		} else {
			System.out.println("Incorrect command : this table does not exist");
		}
	}
}
