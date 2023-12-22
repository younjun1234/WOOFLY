package com.kh.woofly.contest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.woofly.contest.model.service.ContestService;

@Controller
public class ContestController {
	
	@Autowired
	private ContestService cService;	
	
	@GetMapping("/contest/list")
	   public String contestList() {
		
	      return "contestList";
	   }
	
	@GetMapping("/contest/open")
	   public String contestOpen() {
		
	      return "contestOpen";
	   }
	
	@GetMapping("/contest/edit")
	   public String contestEdit() {
		
	      return "contestEdit";
	   }

}
