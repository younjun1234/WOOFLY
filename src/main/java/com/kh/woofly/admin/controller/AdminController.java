package com.kh.woofly.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.woofly.admin.model.service.AdminService;
import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.common.Pagination;
import com.kh.woofly.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
								@RequestParam(value = "mbId", required = false) String searchId,
								Model model, HttpServletRequest request
								) {
					//@RequestParam(value="mbId", required=false) String rAccused
		
		// 신고 전체리스트
		int listCount = aService.getReportCount(searchId);
		PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
		
		ArrayList<Report> rList = aService.selectReportList(pi, searchId);
		
		for(Report r : rList) {
			System.out.println(r);
		}
		
		// 랭킹 리스트 나중에 일자 별 검색으로 바꿀 때는 검색할 N 숫자 보내면 됨
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
	
	@GetMapping("/admin/reportDetail")
	public String moveToReportDetail(@RequestParam("rNo") int rNo, Model model) {
		
		// 현재 신고한 신고 정보(신고자, 게시글 구분, 카테고리, date, 내용, 피신고자 이름)
		Report r = aService.selectReportDetail(rNo);
		ArrayList<Report> rList = aService.selectTargetList(r.getRAccused());
		System.out.println(r);
		
		int pileWarning = 0;
		int pileBanded = 0;
		if(!rList.isEmpty()) {
			for(Report dr : rList) {
				if(dr.getRSituation().equalsIgnoreCase("W")) {
					pileWarning++;
				} else if(dr.getRSituation().equalsIgnoreCase("B")) {
					pileBanded++;
				}
			}
		}
		model.addAttribute("pileWarning", pileWarning);
		model.addAttribute("pileBanded", pileBanded);
		model.addAttribute("r", r);
		model.addAttribute("rList", rList);
		
		return "reportDetail";
	}
	
	@GetMapping("/admin/stopUser.ad")
	public String stopUser(@ModelAttribute Report r,
							HttpSession session,
							RedirectAttributes re,
							Model model) {
		Member admin = (Member)session.getAttribute("loginUser");
		if(admin == null || !admin.getIsAdmin().equals("Y")) {
			return "index";
		} 
		
		if(r != null) {
			r.setRSituation("B");
			int result1 = aService.updateStopDate(r.getRAccused());
			int result2 = aService.updateReportSit(r.getRNo());
			
			re.addAttribute("rNo", r.getRNo());
			
			return "redirect:/admin/reportDetail";
		} else {
			// 응답으로 바꿔야하나..
			re.addAttribute("msg", "필요한 데이터가 없습니다.");
			return "/adminMain.ad";
		}
	}
	
	@GetMapping("/admin/warningUser.ad")
	public String warningUser(@ModelAttribute Report r,
								HttpSession session) {
		
		Member admin = (Member)session.getAttribute("loginUser");
		if(admin == null || !admin.getIsAdmin().equals("Y")) {
			return "index";
		} 
		
		// 카운트 되는지 확인하고 DB에서 r_rContent 구분자 애들한테 정리하라고 해야함
		if(r != null) {
			r.setRSituation("W");
			int count = aService.selectWarningCount(r);
			System.out.println(count);
			//int result1 = aService.update(r);
		}
		return null;
	}
	

}
