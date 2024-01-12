package com.kh.woofly.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

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
								Model model, HttpServletRequest request
								) {
					//@RequestParam(value="mbId", required=false) String rAccused
		
		// 신고 전체리스트
		int listCount = aService.getReportCount();
		PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
		
		ArrayList<Report> rList = aService.selectReportList(pi);
		
		// 랭킹 리스트
		ArrayList<HashMap<String, Object>> rkList = aService.selectReportRank();
		for(int i = 0; i < rkList.size(); i ++) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(rkList.get(i).get("LAST_REPORT_DATE"));
			rkList.get(i).put("LAST_REPORT_DATE", date);
		}
		
		model.addAttribute("rkList", rkList);
		model.addAttribute("rList", rList);
		model.addAttribute("pi", pi);
		model.addAttribute("loc", request.getRequestURI());
		// R_ACCUSED
		
		return "reportList";
	}
	
	
	
	

}
