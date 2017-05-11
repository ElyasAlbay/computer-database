package com.excilys.cdb.ui;


public class Main {

	public static void main(String[] args) {
		/*List<Computer> computersList = null;
		ComputerDaoImpl computerDao = new ComputerDaoImpl();
		
		computersList = computerDao.listRequest();
		for(Computer computer : computersList) {
			System.out.println(computer.getName());
		}
			CompanyDaoImpl companyDao = new CompanyDaoImpl();
			companyDao.listRequest();
			ComputerDaoImpl computerDao = new ComputerDaoImpl();
			
			computersList = computerDao.listRequest();
			for(Computer computer : computersList) {
				System.out.println(computer.getName());
			}
			computerDao.listRequest();
			computerDao.showDetails(5);*/
		
		UserInterface instance = UserInterface.getInstance();
		instance.getUserInput();
		
		
	}
}
