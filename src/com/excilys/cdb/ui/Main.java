package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.model.*;
import com.excilys.cdb.persistence.*;

public class Main {

	public static void main(String[] args) {
		List<Computer> computersList = null;
		
			//CompanyDaoImpl companyDao = new CompanyDaoImpl();
			ComputerDaoImpl computerDao = new ComputerDaoImpl();
			
			computersList = computerDao.listRequest();
			for(Computer computer : computersList) {
				System.out.println(computer.getName());
			}
			//computerDao.listRequest();
			//computerDao.showDetails(5);
	}
}
