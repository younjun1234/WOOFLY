package com.kh.woofly.contest.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.woofly.contest.model.service.ContestService;
import com.kh.woofly.contest.model.vo.Contest;
import com.kh.woofly.contest.model.vo.ContestItem;
import com.kh.woofly.contest.model.vo.Participants;

import jakarta.servlet.http.HttpSession;

@Controller
public class ContestController {
	
	@Autowired
	private ContestService cService;
	
	@Autowired
	private SchedulerApplication scheduler;
	
	@GetMapping("/contest/list")
	public String contestList() {
		
		return "contestList";
		
	}
	
	@GetMapping("/contest/open")
	public String contestOpen(Model model) {
		// 최신 콘테스트 1개값 가져옴
		Contest c = cService.contestNewList();
		
		model.addAttribute("c", c);
		
		return "contestOpen";
		
	}
	
	// 콘테스트 개최
	@GetMapping("/contest/contestOpen")
	public String contestOpen(@ModelAttribute Contest c, HttpSession session) {
//		String id = ((Member)session.getAttribute("loginUser")).getId();	
//		n.setBoardWriter(id);
		
		int result = cService.contestOpen(c);
		
		if(result > 0) {
			return "redirect:/contest/edit";
		}else {
			return null;
		}
	}
	
	// 콘테스트 수정페이지 이동
	@GetMapping("/contest/edit")
	public String contestEdit(Model model) {
		
		Contest c = cService.contestNewList();
		ArrayList<Contest> contestList = cService.contestList();
		
		model.addAttribute("c", c);
		model.addAttribute("contestList", contestList);
		
		return "contestEdit";
	}
	
	// 콘테스트 수정
	@GetMapping("/contest/contestUpdate")
	public String contestUpdate(@ModelAttribute Contest c, HttpSession session, Model model) {
//		String id = ((Member)session.getAttribute("loginUser")).getId();	
//		n.setBoardWriter(id);
		
		int result = cService.contestUpdate(c);
		ArrayList<Contest> contestList = cService.contestList();
		if(result > 0) {		
			model.addAttribute("c", c);
			model.addAttribute("contestList", contestList);
			return "contestEdit";
		}else {
			return null;
		}
	}
	
	// 콘테스트 참가페이지 이동
	@GetMapping("/contest/contestParticipation")
	public String contestParticipation(@ModelAttribute Contest c, HttpSession session, Model model) {
//		String id = ((Member)session.getAttribute("loginUser")).getId();	
//		n.setBoardWriter(id);
		
		String id = "younjun1234";
		
		ArrayList<ContestItem> itemList = cService.itemList(id);
		
		model.addAttribute("itemList", itemList);
		
		return "contestParticipation";
	}
	
	// 콘테스트 참가
	@PostMapping("/contest/contestEnroll")
	public String contestEnroll(@ModelAttribute Participants p, HttpSession session) {
//		String id = ((Member)session.getAttribute("loginUser")).getId();
//		n.setBoardWriter(id);
		LocalDate today = LocalDate.now();
		
		Contest c = cService.contestId(today);
		int cNo = c.getConNo();
		
		int result = cService.contestEnroll(p, cNo);
		
		return "redirect:/contest/list";
	}
	
	
	// 콘테스트 아이템 검색
	@GetMapping("/contest/searchItem")
	@ResponseBody
	public ArrayList<ContestItem> searchItem(@RequestParam("pSearch") String pSearch, Model model) {
		
		ArrayList<ContestItem> list = cService.searchItem(pSearch);
		
		System.out.println(list);
		
		return list;
		
	}
	
	
}