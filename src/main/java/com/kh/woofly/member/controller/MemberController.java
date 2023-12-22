package com.kh.woofly.member.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.woofly.member.model.exception.MemberException;
import com.kh.woofly.member.model.service.MemberService;
import com.kh.woofly.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private MemberService mService;

	@GetMapping("/my")
	public String profileHomeView() {
		return "myHome";
	}

	@GetMapping("my/login-edit")
	public String loginView(Model model) {
		return "myLogin";
	}

	@GetMapping("my/profile-edit")
	public String profileView(HttpSession session, Model model) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		ArrayList<Member>  list = mService.getBlackList(id);
		model.addAttribute("list", list);
		return "myProfile";
	}

	@GetMapping("my/address")
	public String addressView() {
		return "myAddress";
	}

	@GetMapping("my/payment")
	public String paymentView() {
		return "myPayment";
	}

	@GetMapping("my/point")
	public String pointView() {
		return "myPoint";
	}

	@GetMapping("my/addPayment")
	public String addPayment(@RequestParam("authKey") String authKey, @RequestParam("customerKey") String customerKey) {
		System.out.println(authKey);
		System.out.println(customerKey);
		String billingKey = Base64.getEncoder().encodeToString("test_sk_kYG57Eba3G6AeDn45qa98pWDOxmA:".getBytes());
		System.out.println(billingKey);
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue"))
				.header("Authorization", "Basic " + billingKey).header("Content-Type", "application/json")
				.method("POST",
						BodyPublishers
								.ofString("{\"authKey\":\"" + authKey + "\",\"customerKey\":\"" + customerKey + "\"}"))
				.build();

		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
			System.out.println((String) response.body());
		} catch (InterruptedException | IOException var7) {
			var7.printStackTrace();
		}

		return "myPayment";
	}

	@GetMapping("my/profile")
	public String memberView() {
		return "profile";
	}

	@GetMapping("checkPwd.yj")
	@ResponseBody
	public String checkPwd(@RequestParam("currentPwd") String currentPwd, Model model) {
		String pwd = ((Member) model.getAttribute("loginUser")).getMbPwd();
		String result = "N";
		if (bcrypt.matches(currentPwd, pwd)) {
			result = "Y";
		}

		return result;
	}
	
	@PostMapping("removeBlock.yj")
	public String removeBlock(@RequestParam("blockedEntity") String blockedEntity, HttpSession session) {
		HashMap<String, String> map = new HashMap<>();
		String id = ((Member)session.getAttribute("loginUser")).getMbId();

		map.put("loginUser", id);
		map.put("blockedEntity", blockedEntity);
		int result = mService.removeBlock(map);
		
		if (result > 0) {
			return "redirect:/my/profile-edit";
		} else {
			throw new MemberException("차단 해제에 실패하였습니다");
		}
	}
	
	@PostMapping("editNickName.yj")
	public String editNickName(@RequestParam("newNickName") String newNickName, HttpSession session) {
		Member loginUser = ((Member)session.getAttribute("loginUser"));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		Date date = cal.getTime();
		loginUser.setNextChange(date);
		loginUser.setMbNickName(newNickName);
		int result = mService.editNickName(loginUser);
		
		if(result > 0) {
			return "redirect:/my/profile-edit";
		} else {
			throw new MemberException("프로필 이름 변경에 실패하였습니다.");
		}
	}
	
	@PostMapping("editName.yj")
	public String editName(@RequestParam("newName") String newName, HttpSession session) {
		Member loginUser = ((Member)session.getAttribute("loginUser"));
		loginUser.setMbName(newName);
		
		int result = mService.editName(loginUser);
		
		if(result > 0) {
			return "redirect:/my/profile-edit";
		} else {
			throw new MemberException("이름 변경에 실패하였습니다.");
		}
	}
	
	@PostMapping("editIntro.yj")
	public String editIntro(@RequestParam("newIntro") String newIntro, HttpSession session) {
		Member loginUser = ((Member)session.getAttribute("loginUser"));
		loginUser.setMbIntro(newIntro);
		
		int result = mService.editIntro(loginUser);
		
		if(result > 0) {
			return "redirect:/my/profile-edit";
		} else {
			throw new MemberException("소개글 변경에 실패하였습니다.");
		}
	}
	
	@GetMapping("editIsPrivate.yj")
	public String editIsPrivate(@RequestParam("isPrivate") boolean isPrivate, HttpSession session) {
		Member loginUser = ((Member)session.getAttribute("loginUser"));
		loginUser.setIsPrivate(isPrivate ? "Y" : "N");
		
		int result = mService.editIsPrivate(loginUser);
		
		if( result > 0) {
			return "redirect:/my/profile-edit";
		} else {
			throw new MemberException("공개 범위 변경에 실패하였습니다.");
		}
		
		
	}
	
	
	
	
}



















































