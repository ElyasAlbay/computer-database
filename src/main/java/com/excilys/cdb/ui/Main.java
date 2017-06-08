package com.excilys.cdb.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.config.MainConfig;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MainConfig.class);
		ctx.refresh();
		ctx.scan("com.excilys.cdb");
		
		UserInterface instance = new UserInterface(ctx.getBean(ComputerService.class), ctx.getBean(CompanyService.class));
		instance.getUserInput();
		
		ctx.close();
	}
}
