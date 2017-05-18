package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class DisplayTest {
	private Display display;
	
	
	@Before
	public void setUp() throws Exception {
		display = new Display();
	}
	
	@Test
	public void testDisplayListCompany() {
		Company cmpny1 = new Company(1, "test company 1");
		Company cmpny2 = new Company(2, "test company 2");
		Page<Company> companyPage = new Page<>();
		companyPage.getElements().add(cmpny1);
		companyPage.getElements().add(cmpny2);
		
		display.displayListCompany(companyPage);
	}
	
	@Test
	public void testDisplayListComputer() {
		Computer cmptr1 = new Computer(1, "test computer 1");
		Computer cmptr2 = new Computer(2, "test computer 2");
		Page<Computer> computerPage = new Page<>();
		computerPage.getElements().add(cmptr1);
		computerPage.getElements().add(cmptr2);
		
		display.displayListComputer(computerPage);
	}
	
	@Test
	public void testDisplayListCompanyNull() {
		
		display.displayListCompany(null);
	}
	
	@Test
	public void testDisplayListComputerNull() {
		
		display.displayListComputer(null);
	}
	
	@Test
	public void testDisplayShow() {
		Computer cmptr = new Computer(1, "test computer 1");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
		
		cmptr.setIntroduced(LocalDate.parse("2017-02-15", formatter));
		cmptr.setDiscontinued(LocalDate.parse("2017-03-15", formatter));
		cmptr.setCompanyId(5);
		
		display.displayShow(cmptr);
	}
	
	@Test
	public void testDisplayShowNull() {

		display.displayShow(null);
	}
	
	@Test
	public void testDisplayHelpl() {

		display.displayHelp();
	}
	
}
