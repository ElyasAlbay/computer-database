package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;


/**
 * Display class. This class displays appropriate information to the user.
 * @author excilys
 *
 * @param <T>
 */
public class Display<T> {
	
	
	public void displayList(List<T> objectList) {
		for(T t : objectList) {
			if (t instanceof Company) {
				System.out.println(((Company) t).getName() );
				
			} else if (t instanceof Computer) {
				System.out.println(((Computer) t).getName() );
			}
		}
	}
	
	
	public void displayShow(Computer computer) {
		if (computer != null) {
			System.out.println(computer.toString());
		} else {
			System.out.println("This computer does not exist in the database.");
		}
		
	}
	
	
	public static void displayHelp() {
		System.out.println("Here is a list of all valid commands :");
		for(Command comm : Command.values()){
			if(comm.equals(Command.LIST)) {
				System.out.println(comm.toString() + " 'table'");
			} else if(comm.equals(Command.SHOW)) {
				System.out.println(comm.toString() + " 'id'");
			} else if(comm.equals(Command.CREATE)) {
				System.out.println(comm.toString());
			} else if(comm.equals(Command.UPDATE)) {
				System.out.println(comm.toString() + " 'id'");
			} else if(comm.equals(Command.DELETE)) {
				System.out.println(comm.toString() + " 'id'");
			} else {
				System.out.println(comm.toString());
			}
		}
	}
	
}
