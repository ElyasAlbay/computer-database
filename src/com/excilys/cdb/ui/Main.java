package com.excilys.cdb.ui;

import com.excilys.cdb.persistence.*;

public class Main {

	public static void main(String[] args) {
		
			//CompanyDaoImpl companyDao = new CompanyDaoImpl();
			ComputerDaoImpl computerDao = new ComputerDaoImpl();
			
			//companyDao.listRequest();
			
			//computerDao.listRequest();
			computerDao.showDetails(5);
	}
}
