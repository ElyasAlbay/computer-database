package com.excilys.cdb.console.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.excilys.cdb.service.config.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
public class AppConfig {
	
	
}
