package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * @author excilys
 *
 */
public class Parser {
	CompanyService companyService;
	ComputerService computerService;
	
	
	/**
	 * Class constructor. Instantiates services.
	 */
	public Parser() {
		companyService = new CompanyServiceImpl();
		computerService = new ComputerServiceImpl();
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
			parseCreate(scanLine.next());
			
		} else if (token.equals(Command.UPDATE.toString())) {
			token = scanLine.next();
			System.out.println(token);
			
		} else if (token.equals(Command.DELETE.toString())) {
			token = scanLine.next();
			System.out.println(token);
			
		} else if (token.equals(Command.HELP.toString())) {
			Display.displayHelp();
			
		} else if (token.equals(Command.QUIT.toString())) {
			loop = false;
			
		} else {
			System.out.println("Incorrect command : command invalid");
		}
		
		scanLine.close();
		
		return loop;
	}
	
	/**
	 * Parses a single input word to display a list.
	 * @param token Input word.
	 */
	public void parseList(String token) {
		if (token.equals("company")) {
			List<Company> companyList = companyService.listRequest();
			Display<Company> displayCompany = new Display<>();
			displayCompany.displayList(companyList);
			
		} else if (token.equals("computer")) {
			List<Computer> computerList = computerService.listRequest();
			Display<Computer> displayComputer = new Display<>();
			displayComputer.displayList(computerList);
			
		} else {
			System.out.println("Incorrect command : this table does not exist");
		}
	}
	
	/**
	 * Parses id to display details of a computer.
	 * @param token Input id.
	 */
	public void parseShow(String token) {
		Computer computer = computerService.getById(Integer.parseInt(token));
		Display<Computer> displayComputer = new Display<>();
		displayComputer.displayShow(computer);

	}
	
	/**
	 * Parses line to create a computer.
	 * @param token Input name.
	 */
	public void parseCreate(String token) {
		Computer computer = new Computer (0, token);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
		Scanner scanner = new Scanner(System.in);
		
		String computerIntroDate;
		String computerDiscDate;
		int computerCompanyId;
		
		System.out.println("Date introduced : "); 
		if (scanner.hasNext()) {
			computerIntroDate = scanner.next();
			if (computerIntroDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				System.out.println("succes");
				computer.setIntroduced(LocalDate.parse(computerIntroDate, formatter));
			}
		}
		
		System.out.println("Date discontinued : "); 
		if (scanner.hasNext()) {
			computerDiscDate = scanner.next();
			if (computerDiscDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				computer.setDiscontinued(LocalDate.parse(computerDiscDate, formatter));
			}
		}
		
		System.out.println("Company id : "); 
		if (scanner.hasNext()) {
			computerCompanyId = Integer.parseInt(scanner.next());
			computer.setCompanyId(computerCompanyId);
		}
		
		computer = computerService.create(computer);
		Display<Computer> displayComputer = new Display<>();
		displayComputer.displayShow(computer);

		scanner.close();
	}
	
	/**
	 * Parses line to update a computer.
	 * @param token Input id.
	 */
	public void parseUpdate(String token) {
		Computer computer = new Computer (Integer.parseInt(token), "placeholder");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
		Scanner scanner = new Scanner(System.in);
		
		String computerName;
		String computerIntroDate;
		String computerDiscDate;
		int computerCompanyId;
		
		System.out.println("Name : "); 
		if (scanner.hasNext()) {
			computerName = scanner.next();
			computer.setName(computerName);
		}
		
		System.out.println("Date introduced : "); 
		if (scanner.hasNext()) {
			computerIntroDate = scanner.next();
			if (computerIntroDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				System.out.println("succes");
				computer.setIntroduced(LocalDate.parse(computerIntroDate, formatter));
			}
		}
		
		System.out.println("Date introduced : "); 
		if (scanner.hasNext()) {
			computerIntroDate = scanner.next();
			if (computerIntroDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				System.out.println("succes");
				computer.setIntroduced(LocalDate.parse(computerIntroDate, formatter));
			} else if (computerIntroDate.equals("")) {
				
			}
		}
		
		System.out.println("Date discontinued : "); 
		if (scanner.hasNext()) {
			computerDiscDate = scanner.next();
			if (computerDiscDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				computer.setDiscontinued(LocalDate.parse(computerDiscDate, formatter));
			}
		}
		
		System.out.println("Company id : "); 
		if (scanner.hasNext()) {
			computerCompanyId = Integer.parseInt(scanner.next());
			computer.setCompanyId(computerCompanyId);
		}
		
		computer = computerService.update(computer);
		Display<Computer> displayComputer = new Display<>();
		displayComputer.displayShow(computer);

		scanner.close();
	}
}
