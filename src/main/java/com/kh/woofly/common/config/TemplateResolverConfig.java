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
	
	@Bean
	public ClassLoaderTemplateResolver BoardResolver() {
		ClassLoaderTemplateResolver bo = new ClassLoaderTemplateResolver();
		bo.setPrefix("templates/views/board/");
		bo.setSuffix(".html");
		bo.setTemplateMode(TemplateMode.HTML);
		bo.setCharacterEncoding("UTF-8");
		bo.setOrder(3);
		bo.setCacheable(false);
		bo.setCheckExistence(true);
		
		return bo;
	}
	
	@Bean
	public ClassLoaderTemplateResolver PetResolver() {
		ClassLoaderTemplateResolver pet = new ClassLoaderTemplateResolver();
		pet.setPrefix("templates/views/pet/");
		pet.setSuffix(".html");
		pet.setTemplateMode(TemplateMode.HTML);
		pet.setCharacterEncoding("UTF-8");
		pet.setOrder(2);
		pet.setCacheable(false);
		pet.setCheckExistence(true);
		
		return pet;
	}
	
	@Bean
	public ClassLoaderTemplateResolver AccountResolver() {
		ClassLoaderTemplateResolver account = new ClassLoaderTemplateResolver();
		account.setPrefix("templates/views/account/");
		account.setSuffix(".html");
		account.setTemplateMode(TemplateMode.HTML);
		account.setCharacterEncoding("UTF-8");
		account.setOrder(2);
		account.setCacheable(false);
		account.setCheckExistence(true);
		
		return account;
	}
}
