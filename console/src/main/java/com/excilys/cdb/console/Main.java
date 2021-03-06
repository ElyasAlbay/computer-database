package com.excilys.cdb.console;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.console.config.AppConfig;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.register(AppConfig.class);
		appContext.refresh();
		
		UserInterface instance = new UserInterface(appContext.getBean(ComputerService.class), appContext.getBean(CompanyService.class));
		instance.getUserInput();
		
		appContext.close();
	}
}
