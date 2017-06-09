package com.excilys.cdb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("com.excilys.cdb")
public class MainConfig extends WebMvcConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(MainConfig.class);
	private static final String PROP_FILE = "/hikari.properties";

	@Bean
	public HikariDataSource dataSource() {
		LOG.info("Datasource init");
		HikariConfig config = new HikariConfig(PROP_FILE);

		return new HikariDataSource(config);
	}

	@Bean
	public ViewResolver internalRessourceViewResolver() {
		LOG.info("ViewResolver init");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setSuffix(".jsp");

		return resolver;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		LOG.info("TransactionManager init");
		return new DataSourceTransactionManager(dataSource());
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		LOG.info("ResourceHandler init");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		LOG.info("RedirectViewController");
		registry.addRedirectViewController("/", "/dashboard");
	}
}
