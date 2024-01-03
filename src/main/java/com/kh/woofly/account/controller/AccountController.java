package com.kh.woofly.account.controller;

import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.woofly.account.model.exception.AccountException;
import com.kh.woofly.account.model.service.AccountService;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.member.model.vo.MemberAddress;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@SessionAttributes("loginUser")
@Controller
public class AccountController {
	
	@Autowired
	private AccountService aService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	final DefaultMessageService messageService;

    public AccountController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCS8XEQOM4HOQA2T", "SXJCPAE5YMVCBQSKAJ4T48AYDSNHWKAU", "https://api.coolsms.co.kr");
    }
	
	@GetMapping("/account/login")
	public String loginView() {
		return "login";
	}
	
	@PostMapping("login.dw")
	public String login(@ModelAttribute Member m, Model model, @RequestParam("beforeURL")String beforeURL) {
		Member loginUser = aService.login(m);
		if(bcrypt.matches(m.getMbPwd(), loginUser.getMbPwd())) {
			model.addAttribute("loginUser", loginUser);
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
	
	@GetMapping("/idCheck.dw")
	@ResponseBody
	public String idCheck(@RequestParam("id") String mbId) {
		
		int result = aService.idCheck(mbId);
		
		if(result > 0) {
			return "false";
		} else {
			return "true";
		}
	}
	
	@GetMapping("/nickCheck.dw")
	@ResponseBody
	public String nickCheck(@RequestParam("nickName")String mbNickName) {
		
		int result = aService.nickCheck(mbNickName);
		
		if(result > 0) {
			return "false";
		} else {
			return "true";
		}
	}
	
	@GetMapping("/account/signUp")
	public String signUpView() {
		return "signUp";
	}
	
	@PostMapping("/signUp.dw")
	public String signUp(@ModelAttribute Member m, @ModelAttribute MemberAddress ma, @RequestParam("mbAddress1")String postcode,@RequestParam("mbAddress2")String address,
			@RequestParam("mbAddress3")String detailAddress,@RequestParam("mbAddress4")String extraAddress) {
		
		ma.setPostcode(postcode);
		ma.setAddr(address);
		ma.setAddrDetail(detailAddress);
		
		String encPwd = bcrypt.encode(m.getMbPwd());
		m.setMbPwd(encPwd);
		
		int result1 = aService.signUpMember(m);
		int result2 = aService.signUpMemberAddr(ma);
		
		if(result1 > 0 && result2 > 0) {
			return "redirect:/account/login";
		} else {
			throw new AccountException("회원가입을 실패하였습니다.");
		}
		
	}
	
	@GetMapping("/send-one")
	@ResponseBody
    public String sendOne(@RequestParam("pNum") String mbPhone) {
        Message message = new Message();
        Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111; // 난수 생성
		
        message.setFrom("01064954499");
        message.setTo(mbPhone);
        message.setText("인증코드는 " + checkNum + "입니다");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);
        
        if(response.getStatusCode().equals("2000")) {
        	return "" + checkNum; //정상 발송되었다는 모달창
        			
        }else {
        	return "bad"; //발송 오류 모달창
        }
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
