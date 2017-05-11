package com.excilys.cdb.ui;

/**
 * Enumeration listing every command usable.
 * @author excilys
 *
 */
public enum Command {
	LIST("list"),
	SHOW("show"),
	CREATE("create"),
	UPDATE("update"),
	DELETE("delete"),
	QUIT("quit");
	
	private String name = "";
	
	Command(String name) {
		this.name= name;
	}
	
	public String toString() {
		return name;
	}
}
