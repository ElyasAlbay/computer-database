package com.excilys.cdb.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/dashboard").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.POST, "/dashboard").hasAnyRole("ADMIN")
				.antMatchers("/addComputer").hasAnyRole("ADMIN")
				.antMatchers("/editComputer").hasAnyRole("ADMIN")
				.antMatchers("/computer/add**", "/computer/delete**", "/computer/update**").hasAnyRole("ADMIN")
				.antMatchers("/company/add**", "/company/delete**", "/company/update**").hasAnyRole("ADMIN")
					.and().formLogin().loginPage("/login").failureUrl("/login?error")
						.usernameParameter("username").passwordParameter("password")
						.loginProcessingUrl("/j_spring_security_check")
					.and().logout().logoutSuccessUrl("/login?logout").logoutUrl("/j_spring_security_logout")
					.and().csrf();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder;
	}
}
