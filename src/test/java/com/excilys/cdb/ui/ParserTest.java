package com.excilys.cdb.ui;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.exceptions.InvalidCommandException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;


public class ParserTest {
	private CompanyService companyServiceMock;
	private ComputerService computerServiceMock;
	private Display displayMock;
	
	private Parser parser;
	
	
	@Before
	public void setUp() throws Exception {
		parser = new Parser();
		
		List<Company> listCmpny = new ArrayList<>();
		Page<Company> companyPage = new Page<>();
		Company cmpny = new Company(1,"Test company");
		listCmpny.add(cmpny);
		
		List<Computer> listCmptr = new ArrayList<>();
		Page<Computer> computerPage = new Page<>();
		Computer cmptr = new Computer(1,"Test computer");
		listCmptr.add(cmptr);
		
		
		// Mocking class fields.
		companyServiceMock = Mockito.mock(CompanyService.class);
		parser.setCompanyService(companyServiceMock);
		
		computerServiceMock = Mockito.mock(ComputerService.class);
		parser.setComputerService(computerServiceMock);
		
		displayMock = Mockito.mock(Display.class);
		parser.setDisplay(displayMock);
		
		
		// Moking methods
		Mockito.when(companyServiceMock.getAll(companyPage)).thenReturn(companyPage);
		Mockito.when(computerServiceMock.getAll(computerPage)).thenReturn(computerPage);
		Mockito.when(computerServiceMock.getById(5)).thenReturn(cmptr);
		Mockito.when(computerServiceMock.create(cmptr)).thenReturn(cmptr);
		Mockito.when(computerServiceMock.update(cmptr)).thenReturn(cmptr);
		Mockito.doNothing().when(computerServiceMock).delete(1);;
		
		Mockito.doNothing().when(displayMock).displayShow(cmptr);
		Mockito.doNothing().when(displayMock).displayListComputer(computerPage);
		Mockito.doNothing().when(displayMock).displayListCompany(companyPage);
		
	}
	
	
	@Test
	public void testParseLine() throws InvalidCommandException {
				
		assertTrue("str list company", parser.parseLine("list company") == true);
		assertTrue("str list computer", parser.parseLine("list computer") == true);
		assertTrue("str show", parser.parseLine("show 5") == true);
		assertTrue("str create", parser.parseLine("create") == true);
		assertTrue("str update", parser.parseLine("update 1") == true);
		assertTrue("str delete", parser.parseLine("delete 1") == true);
		assertTrue("str help", parser.parseLine("help") == true);
		assertTrue("str quit", parser.parseLine("quit") == false);
	}
	
	@Test
	public void testParseLineTooLong() throws InvalidCommandException {
		
		assertTrue("str list company_long", parser.parseLine("list company test") == true);
		assertTrue("str list computer_long", parser.parseLine("list computer test") == true);
		assertTrue("str create long", parser.parseLine("create test") == true);
		assertTrue("str update id long", parser.parseLine("update 1 test") == true);
		assertTrue("str delete id long", parser.parseLine("delete 1 test") == true);
		assertTrue("str help long", parser.parseLine("help test") == true);
		assertTrue("str quit long", parser.parseLine("quit test") == false);
	}
	
	@Test
	public void testParseLineEmptyOrNull() throws InvalidCommandException {
		
		assertTrue("str empty", parser.parseLine("") == true);
		assertTrue("str null", parser.parseLine(null) == true);
	}
	
	@Test
	public void testParseLineInvalidCommand() {		
		
		try {
			assertTrue("str invalid", parser.parseLine("invalid") == true);
			fail("Exception not caught if command invalid.");
		} catch (InvalidCommandException e) {
			assertTrue(e.getMessage().contains("Unknown command"));
		}
		
		try {
			assertTrue("str list invalid", parser.parseLine("list invalid_table") == true);
			fail("Exception not caught if table not exists.");
		} catch (InvalidCommandException e) {
			assertTrue(e.getMessage().contains("Unknown table"));
		}
	}

}
