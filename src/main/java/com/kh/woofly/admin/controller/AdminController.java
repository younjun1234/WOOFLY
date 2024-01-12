package com.kh.woofly.admin.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.woofly.admin.model.service.AdminService;
import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.common.Pagination;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService aService;
	
	@GetMapping("/adminMain.ad")
	public String moveToAdminMain() {
		// HashMap<String, ArrayList<Object>> lists =  aService.selectAllBoard();
		
		return "adminMain";
	}
	
	@GetMapping("/admin/reportList.ad")
	public String moveToReport(@RequestParam(value = "page", defaultValue="1") int page,
								Model model, HttpServletRequest request) {
		
		// 신고 전체리스트
		int listCount = aService.getReportCount();
		PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
		ArrayList<Report> rList = aService.selectReportList(pi);
		
		// 신고 랭킹리스트
		LocalDate now = LocalDate.now();
		
		
		
		return "reportList";
	}

}
