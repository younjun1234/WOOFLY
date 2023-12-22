package com.kh.woofly.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.woofly.common.interceptor.CheckAdminInterceptor;
import com.kh.woofly.common.interceptor.CheckLoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:///C:/uploadFiles/");
	}
	
	@Override
	   public void addInterceptors(InterceptorRegistry registry) { // Interceptor 등록해주는 메소드
	    //일반계정용  
		registry.addInterceptor(new CheckLoginInterceptor())
//	      
	         .addPathPatterns("/myInfo.me", "/editMyInfo.me","/updateMember.me","/uodatePassword.me","/deleteMember.me")
	         .addPathPatterns("/*.bo","/*.at")
	         .excludePathPatterns("/list.bo","list.at");
	      
	      registry.addInterceptor(new CheckAdminInterceptor())
	         .addPathPatterns("/*.ad");
	   }
}
