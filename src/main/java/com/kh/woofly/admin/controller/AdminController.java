package com.kh.woofly.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.woofly.admin.model.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	@GetMapping("/adminMain.ad")
	public String moveToAdminMain() {
		// HashMap<String, ArrayList<Object>> lists =  aService.selectAllBoard();
		
		return "adminMain";
	}

}
