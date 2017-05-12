package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.CompanyServiceImpl;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;


/**
 * Parser for user input. This class parses the input and does appropriate treatment
 * according to the command.
 * @author Elyas Albay
 *
 */
public class Parser {
	CompanyService companyService;
	ComputerService computerService;
	
	
	/**
	 * Class constructor. Instantiates services.
	 */
	public Parser() {
		companyService = CompanyServiceImpl.INSTANCE;
		computerService = ComputerServiceImpl.INSTANCE;
	}
	
	
	/**
	 * Parses a complete input line.
	 * @param line Input line.
	 * @return loop Boolean indicating if the loop should keep going.
	 */
	public boolean parseLine(String line) {
		boolean loop = true;
		Scanner scanLine = new Scanner(line);
		String token = scanLine.next();
		
		if (token.equals(Command.LIST.toString())) {
			parseList(scanLine.next());
			
		} else if (token.equals(Command.SHOW.toString())) {
			parseShow(scanLine.next());
			
		} else if (token.equals(Command.CREATE.toString())) {
			parseCreate();
			
		} else if (token.equals(Command.UPDATE.toString())) {
			parseUpdate(scanLine.next());
			
		} else if (token.equals(Command.DELETE.toString())) {
			parseDelete(scanLine.next());
			
		} else if (token.equals(Command.HELP.toString())) {
			Display.displayHelp();
			
		} else if (token.equals(Command.QUIT.toString())) {
			loop = false;
			
		} else {
			System.err.println("Incorrect command : command invalid");
		}
		
		scanLine.close();
		
		return loop;
	}
	
	/**
	 * Parses a single input word to display a list.
	 * @param token Input word.
	 */
	private void parseList(String token) {
		if (token.equals("company")) {
			List<Company> companyList = companyService.listRequest();
			Display<Company> displayCompany = new Display<>();
			displayCompany.displayList(companyList);
			
		} else if (token.equals("computer")) {
			List<Computer> computerList = computerService.listRequest();
			Display<Computer> displayComputer = new Display<>();
			displayComputer.displayList(computerList);
			
		} else {
			System.err.println("Incorrect command : this table does not exist");
		}
	}
	
	/**
	 * Parses id to display details of a computer.
	 * @param token Input id.
	 */
	private void parseShow(String token) {
		Computer computer = computerService.getById(Integer.parseInt(token));
		Display<Computer> displayComputer = new Display<>();
		displayComputer.displayShow(computer);

	}
	
	/**
	 * Parses line to create a computer.
	 * @param token Input name.
	 */
	private void parseCreate() {
		Computer computer = new Computer (0, "placeholder");
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Name: "); 
		computer.setName(getString(scanner));
		
		System.out.println("Date introduced (yyyy-mm-dd or null): ");
		computer.setIntroduced(getDate(scanner));
		
		System.out.println("Date discontinued (yyyy-mm-dd or null): "); 
		computer.setDiscontinued(getDate(scanner));
		
		System.out.println("Company id: "); 
		computer.setCompanyId(getInt(scanner));
		
		
		computer = computerService.create(computer);
		Display<Computer> displayComputer = new Display<>();
		displayComputer.displayShow(computer);

	}
	
	/**
	 * Parses line to update a computer.
	 * @param token Input id.
	 */
	private void parseUpdate(String token) {
		Computer computer = new Computer (Integer.parseInt(token), "placeholder");
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Name: "); 
		computer.setName(getString(scanner));
		
		System.out.println("Date introduced (yyyy-mm-dd or null): ");
		computer.setIntroduced(getDate(scanner));
		
		System.out.println("Date discontinued (yyyy-mm-dd or null): "); 
		computer.setDiscontinued(getDate(scanner));
		
		System.out.println("Company id: "); 
		computer.setCompanyId(getInt(scanner));
		
		
		computer = computerService.update(computer);
		Display<Computer> displayComputer = new Display<>();
		displayComputer.displayShow(computer);

	}
	
	/**
	 * Parses line to delete a computer.
	 * @param token Input id.
	 */
	private void parseDelete(String token) {
		computerService.delete(Integer.parseInt(token));
	}
	
	/**
	 * Gets a date from user input, then returns it as a LocalDate instance.
	 * @param scanner Scanner instance to get user input.
	 * @return LocalDate instance parsed from user input.
	 */
	private LocalDate getDate(Scanner scanner) {
		String date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
		
		do {
			date = scanner.next();
			if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
				try {
					return LocalDate.parse(date, formatter);
				} catch (DateTimeParseException e) {
					System.err.println("Invalid Discontinued date.");
				}
			} else if (date.equals("null")) {
				return null;
			}
		} while (scanner.hasNext());
		
		return null;
	}
	
	/**
	 * Gets an int from user input, then returns it.
	 * @param scanner Scanner instance to get user input.
	 * @return int parsed from user input.
	 */
	private int getInt(Scanner scanner) {

		if (scanner.hasNext()) {
			return Integer.parseInt(scanner.next());
		}
		
		return 0;
	}
	
	/**
	 * Gets a String from user input, then returns it.
	 * @param scanner Scanner instance to get user input.
	 * @return String from user input.
	 */
	private String getString(Scanner scanner) {

		if (scanner.hasNext()) {
			return scanner.next();
		}
		
		return null;
	}
	
}
