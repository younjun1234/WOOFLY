package com.kh.woofly.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.woofly.account.model.exception.AccountException;
import com.kh.woofly.account.model.service.AccountService;
import com.kh.woofly.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class AccountController {
	
	@Autowired
	private AccountService aService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@GetMapping("/account/login")
	public String loginView() {
		return "login";
	}
	
	@PostMapping("login.dw")
	public String login(@ModelAttribute Member m, Model model, @RequestParam("beforeURL")String beforeURL) {
		Member loginUser = aService.login(m);
		if(bcrypt.matches(m.getMbPwd(), loginUser.getMbPwd())) {
			model.addAttribute("loginUser", loginUser);
			System.out.println(loginUser);
//			if (loginUser.getIsAdmin().equals("N")) {
//				//로그 추가
//				logger.info(loginUser.getMbId());
//				return "redirect:/";
//			} else {
//				return "redirect:admin.ad";
//			}
			return "redirect:/";
		} else {
			throw new AccountException("로그인을 실패하였습니다.");
		}
		
	}
	
	@GetMapping("/account/signUp")
	public String signUpView() {
		return "signUp";
	}
	
	@PostMapping("singUp.dw")
	public String signUp() {
		
		return null;
	}
	
	@GetMapping("/account/findId")
	public String findIdView() {
		return "findId";
	}
	
	@GetMapping("/account/findPwd")
	public String findPwdView() {
		return "findPwd";
	}
	
	
	
	@GetMapping("/account/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}

}
