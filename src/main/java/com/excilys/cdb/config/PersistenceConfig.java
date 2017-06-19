package com.excilys.cdb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.excilys.cdb.persistence")
public class PersistenceConfig {
	private static final Logger LOG = LoggerFactory.getLogger(PersistenceConfig.class);
	private static final String PROP_FILE = "/hikari.properties";

	@Bean
	public HikariDataSource dataSource() {
		LOG.info("Datasource init");
		
		HikariConfig config = new HikariConfig(PROP_FILE);

		return new HikariDataSource(config);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		LOG.info("JdbcTemplate init");
		
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		LOG.info("NamedParameterJdbcTemplate init");
		
		return new NamedParameterJdbcTemplate(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		LOG.info("TransactionManager init");

		return new DataSourceTransactionManager(dataSource());
	}
	
}
