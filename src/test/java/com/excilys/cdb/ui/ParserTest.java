package com.excilys.cdb.ui;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excilys.cdb.Exceptions.InvalidCommandException;


public class ParserTest {
	
	@Test
	public void testParseLine() throws InvalidCommandException {
		Parser parser = new Parser();
		String str;
		boolean bool;
		
		str = "list computer";
		bool = true;
		assertTrue("str list", parser.parseLine(str) == bool);
		
		str = "show 5";
		bool = true;
		assertTrue("str show", parser.parseLine(str) == bool);
		
		str = "create";
		bool = true;
		assertTrue("str create", parser.parseLine(str) == bool);
		
		str = "update";
		bool = true;
		assertTrue("str update", parser.parseLine(str) == bool);
		
		str = "delete";
		bool = true;
		assertTrue("str delete", parser.parseLine(str) == bool);
		
		str = "quit";
		bool = false;
		assertTrue("str quit", parser.parseLine(str) == bool);
	}
	
	@Test
	public void testParseLineTooLong() throws InvalidCommandException {
		Parser parser = new Parser();
		String str;
		boolean bool;
		
		str = "list computer test";
		bool = true;
		assertTrue("str list", parser.parseLine(str) == bool);
	}
	
	@Test
	public void testParseLineEmptyOrNull() throws InvalidCommandException {
		Parser parser = new Parser();
		String str;
		boolean bool;
		
		str = "";
		bool = true;
		assertTrue("str empty", parser.parseLine(str) == bool);
		
		str = null;
		bool = true;
		assertTrue("str null", parser.parseLine(str) == bool);
	}
	
	@Test
	public void testParseLineInvalidCommand() {
		Parser parser = new Parser();
		String str;
		boolean bool;
		
		str = "invalid";
		bool = true;
		try {
			assertTrue("str invalid", parser.parseLine(str) == bool);
			fail("Exception not caught if command invalid.");
		} catch (InvalidCommandException e) {
			assertTrue(e.getMessage().contains("Unknown command"));
		}
	}

}
