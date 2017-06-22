package com.excilys.cdb.console;

/**
 * Enumeration listing every command usable.
 * @author Elyas Albay
 *
 */
public enum Command {
	LIST("list"),
	SHOW("show"),
	CREATE("create"),
	UPDATE("update"),
	DELETE("delete"),
	HELP("help"),
	QUIT("quit");
	
	private String name = "";
	
	Command(String name) {
		this.name= name;
	}
	
	public String toString() {
		return name;
	}
}
