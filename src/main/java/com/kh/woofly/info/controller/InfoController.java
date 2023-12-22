package com.kh.woofly.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.woofly.info.model.service.InfoService;

@Controller
public class InfoController {

	@Autowired
	private InfoService iService;
	
	@GetMapping("/info/notice")
	   public String notice() {
		
	      return "noticeList";
	   }
	
	@GetMapping("/info/QNA")
	   public String QNA() {
		
	      return "FAQList";
	   }
	
	@GetMapping("/info/company")
	   public String company() {
		
	      return "companyList";
	   }
	
	
}
