package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.cdb.exceptions.InvalidCommandException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.CompanyServiceImpl;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.ComputerServiceImpl;

/**
 * Parser for user input. This class parses the input and does appropriate
 * treatment according to the command.
 * 
 * @author Elyas Albay
 *
 */
public class Parser {
	private CompanyService companyService;
	private ComputerService computerService;
	private Display display;

	/**
	 * Class constructor. Instantiates services.
	 */
	public Parser() {
		companyService = CompanyServiceImpl.INSTANCE;
		computerService = ComputerServiceImpl.INSTANCE;
		display = new Display();
	}

	/**
	 * Parses a complete input line.
	 * 
	 * @param line
	 *            Input line.
	 * @return loop Boolean indicating if the loop should keep going.
	 * @throws InvalidCommandException
	 */
	public boolean parseLine(String line) throws InvalidCommandException {
		Scanner scanLine;
		boolean loop = true;

		if (line == null || line.isEmpty()) {
			return loop;
		}

		scanLine = new Scanner(line);
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
			display.displayHelp();

		} else if (token.equals(Command.QUIT.toString())) {
			loop = false;

		} else {
			scanLine.close();
			throw new InvalidCommandException("Unknown command");
		}

		scanLine.close();

		return loop;
	}

	/**
	 * Parses a single input word to display a list.
	 * 
	 * @param token
	 *            Input word.
	 * @throws InvalidCommandException
	 */
	private void parseList(String token) throws InvalidCommandException {
		Scanner scanner = new Scanner(System.in);

		if (token.equals("company")) {
			Page<Company> companyPage = new Page<>();
			getPageInfo(companyPage, scanner);

			companyPage = companyService.listRequest(companyPage);
			display.displayListCompany(companyPage);

		} else if (token.equals("computer")) {
			Page<Computer> computerPage = new Page<>();
			getPageInfo(computerPage, scanner);

			computerPage = computerService.listRequest(computerPage);
			display.displayListComputer(computerPage);

		} else {
			scanner.close();
			throw new InvalidCommandException("Unknown table");
		}

	}

	/**
	 * Parses id to display details of a computer.
	 * 
	 * @param token
	 *            Input id.
	 */
	private void parseShow(String token) {
		Computer computer = computerService.getById(Integer.parseInt(token));
		display.displayShow(computer);

	}

	/**
	 * Parses line to create a computer.
	 * 
	 * @param token
	 *            Input name.
	 */
	private void parseCreate() {
		Computer computer = new Computer(0, "placeholder");
		Scanner scanner = new Scanner(System.in);

		getComputerInfo(computer, scanner);

		computer = computerService.create(computer);
		display.displayShow(computer);

	}

	/**
	 * Parses line to update a computer.
	 * 
	 * @param token
	 *            Input id.
	 */
	private void parseUpdate(String token) {
		Computer computer = new Computer(Integer.parseInt(token), "placeholder");
		Scanner scanner = new Scanner(System.in);

		getComputerInfo(computer, scanner);

		computer = computerService.update(computer);
		display.displayShow(computer);

	}

	/**
	 * Parses line to delete a computer.
	 * 
	 * @param token
	 *            Input id.
	 */
	private void parseDelete(String token) {
		computerService.delete(Integer.parseInt(token));
	}

	/**
	 * Asks and gets computer information from user input.
	 * 
	 * @param computer
	 *            Computer instance. scanner Scanner instance to get user input.
	 */
	public void getComputerInfo(Computer computer, Scanner scanner) {
		
		System.out.println("Name: ");
		computer.setName(getString(scanner));

		System.out.println("Date introduced (yyyy-mm-dd or null): ");
		computer.setIntroduced(getDate(scanner));

		System.out.println("Date discontinued (yyyy-mm-dd or null): ");
		computer.setDiscontinued(getDate(scanner));

		Company company;
		System.out.println("Company id: ");
		int id = getInt(scanner);
		company = companyService.getById(id);
		computer.setCompany(company);
	}

	/**
	 * Asks and gets page information from user input.
	 * 
	 * @param page
	 *            Page instance.
	 * @param scanner
	 *            Scanner instance to get user input.
	 */
	@SuppressWarnings("rawtypes")
	public void getPageInfo(Page page, Scanner scanner) {

		System.out.println("Page size: ");
		page.setPageSize(getInt(scanner));

		System.out.println("Page number: ");
		page.setPageNumber(getInt(scanner));
	}

	/**
	 * Gets a date from user input, then returns it as a LocalDate instance.
	 * 
	 * @param scanner
	 *            Scanner instance to get user input.
	 * @return LocalDate instance parsed from user input.
	 */
	private LocalDate getDate(Scanner scanner) {
		String date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		do {
			date = scanner.next();
			if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
				try {
					return LocalDate.parse(date, formatter);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date.");
				}
			} else if (date.equals("null") || date.isEmpty() || date == null) {
				return null;
			}
		} while (scanner.hasNext());

		return null;
	}

	/**
	 * Gets an int from user input, then returns it.
	 * 
	 * @param scanner
	 *            Scanner instance to get user input.
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
	 * 
	 * @param scanner
	 *            Scanner instance to get user input.
	 * @return String from user input.
	 */
	private String getString(Scanner scanner) {

		if (scanner.hasNext()) {
			return scanner.next();
		}

		return null;
	}

	
	/* Getters and setters */
	public CompanyService getCompanyService() {
		return this.companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public ComputerService getComputerService() {
		return this.computerService;
	}

	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

	public Display getDisplay() {
		return this.display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

}
