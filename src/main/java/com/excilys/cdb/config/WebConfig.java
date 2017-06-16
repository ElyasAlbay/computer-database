package com.excilys.cdb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.excilys.cdb.webui")
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	public ViewResolver internalRessourceViewResolver() {
		LOG.info("ViewResolver init");

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setSuffix(".jsp");

		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
//
//	@Bean
//	public LocaleResolver localeResolver() {
//		final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//		localeResolver.setDefaultLocale(new Locale("en"));
//		
//		return localeResolver;
//	}

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

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//		
//		interceptor.setParamName("locale");
//		registry.addInterceptor(interceptor);
//	}

}
