package com.kh.woofly.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class TemplateResolverConfig {
	
	@Bean
	public ClassLoaderTemplateResolver DefaultResolver() {
		ClassLoaderTemplateResolver slash = new ClassLoaderTemplateResolver();
		slash.setPrefix("templates/views/");
		slash.setSuffix(".html");
		slash.setTemplateMode(TemplateMode.HTML);
		slash.setCharacterEncoding("UTF-8");
		slash.setOrder(1);
		slash.setCacheable(false);
		slash.setCheckExistence(true);
		
		return slash;
	}
	
	@Bean
	public ClassLoaderTemplateResolver MyResolver() {
		ClassLoaderTemplateResolver my = new ClassLoaderTemplateResolver();
		my.setPrefix("templates/views/my/");
		my.setSuffix(".html");
		my.setTemplateMode(TemplateMode.HTML);
		my.setCharacterEncoding("UTF-8");
		my.setOrder(2);
		my.setCacheable(false);
		my.setCheckExistence(true);
		
		return my;
	}
}
