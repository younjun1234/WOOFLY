package com.kh.woofly.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
	
	@GetMapping("/account/login")
	public String loginView() {
		return "login";
	}
	
	@GetMapping("/account/signUpSns")
	public String signUpSnsView() {
		return "signUpSns";
	}
	
	@GetMapping("/account/findId")
	public String findIdView() {
		return "findId";
	}
	
	@GetMapping("/account/findPwd")
	public String findPwdView() {
		return "findPwd";
	}
	
	@GetMapping("/account/signUp")
	public String signUpView() {
		return "signUp";
	}

}
