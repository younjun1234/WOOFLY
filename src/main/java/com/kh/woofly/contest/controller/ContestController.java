package com.kh.woofly.contest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContestController {
	
	@GetMapping("/contest/list")
	   public String contestList() {
		
	      return "contestAllList";
	   }
	

}
