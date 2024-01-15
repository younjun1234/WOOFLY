package com.kh.woofly.info.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.woofly.info.Pagination;
import com.kh.woofly.info.model.service.InfoService;
import com.kh.woofly.info.model.vo.Company;
import com.kh.woofly.info.model.vo.Notice;
import com.kh.woofly.info.model.vo.PageInfo;
import com.kh.woofly.info.model.vo.QNA;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class InfoController {
=======
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> refs/heads/develop

<<<<<<< HEAD
	@Autowired
	private InfoService iService;
	
// --------------------------    NOTICE     ----------------------------------- 
	
	//  공지사항 리스트 이동
	@GetMapping("/info/notice")
	public String noticeList(@RequestParam(value="value", defaultValue="") String value, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		int i = 3;
		
		value = null;
		
		int listCount = iService.getListCount(i);
		int currentPage = page;
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 7);
		ArrayList<Notice> list = iService.selectNoticeList(pi);
		
		if(list != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI()); //getRequestURI == contextpath 제외하고 가져옴
			model.addAttribute("value", value);
			return "noticeList";
		} else {
			return null;
		}
	}
	
	// 공지사항 작성페이지 이동
	@GetMapping("/info/noticeWrite")
	public String noticeWrite() {
		
		return "noticeWrite";
	}
	
  // 공지사항 등록
	@PostMapping("/info/insertNotice")
	public String insertNotice(@ModelAttribute Notice n, HttpSession session) {
		
//		String id = ((Member)session.getAttribute("loginUser")).getId();	
//		n.setBoardWriter(id);
		
		int result = iService.insertNotice(n);
		
		if(result > 0) {			
			return "redirect:/info/notice";
		}else {
			return null;
		}
	}
	
	// 공지사항 상세페이지 이동
	@GetMapping("/info/selectNotice")
	public String selectNotice(@RequestParam("nNo") int nNo, Model model, @RequestParam("page") int page) {						
		
//		Member loginUser = (Member)session.getAttribute("loginUser");
		
		String id = null;
		
//		if(loginUser != null) {
//			id = loginUser.getId();
//		}
		
		Notice n = iService.selectNotice(nNo, id);
		
		if(n != null) {
			model.addAttribute("n", n);
			model.addAttribute("page", page);
			return "noticeDetail";
		} else {
			return null;
		}
	}
	
	// 공지사항 수정페이지 이동
	@PostMapping("/info/NoticeEdit")
	public String NoticeEdit(@RequestParam("nNo") int nNo, Model model, @RequestParam("page") int page) {
		
		Notice n = iService.selectNotice(nNo, null);
		
		if(n != null) {
			model.addAttribute("n", n);
			model.addAttribute("page", page);
			
			return "NoticeEdit";
		} else {
			return null;
		}
	}
	
	// 공지사항 수정
	@PostMapping("/info/updateNotice")
	public String updateNotice(@ModelAttribute Notice n, @RequestParam("page") int page, RedirectAttributes re) {
		
		int result = iService.updateNotice(n);
		
		if(result > 0) {
			re.addAttribute("nNo", n.getNNo());
			re.addAttribute("page", page);
			return "redirect:/info/selectNotice";
		} else {
			return null;
		}
	}
	
	// 공지사항 삭제(상태 변경)
	@GetMapping("/info/deleteNotice")
	public String deleteNotice(@RequestParam("nNo") int nNo, @RequestParam("page") int page, RedirectAttributes re) {
		
		int result = iService.deleteNotice(nNo);
		
		if(result > 0) {
			re.addAttribute("page", page);
			return "redirect:/info/notice";
		} else {
			return null;
		}
	}
	
	//  공지사항 검색
	@GetMapping("/info/searchNotice")
	public String searchNotice(@RequestParam("value") String value, @RequestParam("Category") String Category, @RequestParam("search") String search, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		String table = "notice";
		String status = "n_status";
		String abc = "N_CATEGORY";
		String type = "";
		
		Properties prop = new Properties();
		int currentPage = page;
		int listCount = 0;
		
		if(Category.equals("all")) {
			
			if(value == "") {
				type = "1";
				String title = "n_Title";
				String content = "n_Content";
				
				prop.setProperty("value", value);
				prop.setProperty("type", type);
				prop.setProperty("table", table);
				prop.setProperty("search", search);
				prop.setProperty("Category", Category);
				prop.setProperty("status", status);
				prop.setProperty("title", title);
				prop.setProperty("content", content);
			}else {
				type = "4";
				String title = "n_Title";
				String content = "n_Content";
				
				prop.setProperty("abc", abc);
				prop.setProperty("value", value);
				prop.setProperty("type", type);
				prop.setProperty("table", table);
				prop.setProperty("search", search);
				prop.setProperty("Category", Category);
				prop.setProperty("status", status);
				prop.setProperty("title", title);
				prop.setProperty("content", content);
			}
			
			listCount = iService.searchCount(prop);
			
		} else {
			prop.setProperty("table", table);
			prop.setProperty("search", search);
			prop.setProperty("Category", Category);
			prop.setProperty("status", status);
			if(value == "") {
				type = "3";
				prop.setProperty("type", type);
			} else {
				type = "2";
				
				prop.setProperty("abc", abc);
				prop.setProperty("type", type);
				prop.setProperty("value", value);
			}
			listCount = iService.searchCount(prop);
			
		}
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 7);
		ArrayList<Notice> list;
		
		if(value == ""){
			if(Category.equals("all")) {
				type = "1";
			}else {
				type = "2";
			}
			prop.setProperty("type", type);
			
			list = iService.searchAllNotice(pi, prop);
		} else {
			if(Category.equals("all")) {
				type = "1";
				prop.setProperty("type", type);
			}else {
				type = "2";
				prop.setProperty("type", type);
			}
			
			list = iService.searchNotice(pi, prop);
		}
		
		if(list != null) {
			model.addAttribute("value", value);
			model.addAttribute("Category", Category);
			model.addAttribute("search", search);
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI());
			
			return "noticeList";
		} else {
			return null;
		}
	}
	
//  공지사항 카테고리 선택
	@GetMapping("/info/selectCategoryNotice")
	public String selectCategoryCompany(@RequestParam("value") String value, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		String table = "notice";
		String status = "n_status";
		String Category = "n_category";
		
		
		Properties prop = new Properties();
		prop.setProperty("value", value);
		prop.setProperty("table", table);
		prop.setProperty("status", status);
		prop.setProperty("Category", Category);
		
		int listCount = iService.selectCount(prop);
		int currentPage = page;
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 7);
		ArrayList<Notice> list = iService.selectNoticeCategory(pi, prop);
		
		if(list != null) {
			model.addAttribute("value", value);
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI());
			
			return "noticeList";
			
		} else {
			return null;
		}
	}
	
	
// --------------------------    QNA     ----------------------------------- 	
	
	// QNA(FAQ) 리스트 이동
	@GetMapping("/info/QNA")
	public String QNAList(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		int i = 2;
		
		int listCount = iService.getListCount(i);
		int currentPage = page;
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<QNA> list = iService.selectQNAList(pi);
		
		if(list != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI()); //getRequestURI == contextpath 제외하고 가져옴
			
			return "QNAList";
		} else {
			return null;
		}
	}
	
	// QNA(FAQ) 작성페이지 이동
	@GetMapping("/info/QNAWrite")
	public String QNAWrite() {
		
		return "QNAWrite";
	}
	
	// QNA(FAQ) 등록
	@PostMapping("/info/insertQNA")
	public String insertQNA(@ModelAttribute QNA q, HttpSession session) {
		
//		String id = ((Member)session.getAttribute("loginUser")).getId();
//		q.setBoardWriter(id);
		
		int result = iService.insertQNA(q);
		
		if(result > 0) {
			return "redirect:/info/QNA";
		} else {
			return null;
		}
	}
	
	// QNA(FAQ) 수정페이지 이동
	@PostMapping("/info/QNAEdit")
	public String noticeEdit(@RequestParam("qId") int qId, Model model, @RequestParam("page") int page) {						

		QNA qna = iService.selectQNA(qId);
		
		if(qna != null) {
			model.addAttribute("qna", qna);
			model.addAttribute("page", page);
			
			return "QNAEdit";
		} else {
			return null;
		}
	}	
	
	// QNA(FAQ) 수정
	@PostMapping("/info/updateQNA")
	public String updateQNA(@ModelAttribute QNA q, @RequestParam("page") int page, RedirectAttributes re) {
		
		int result = iService.updateQNA(q);
		
		if(result > 0) {
			re.addAttribute("page", page);
			return "redirect:/info/QNA";
		} else {
			return null;
		}
	}
	
	// QNA(FAQ) 삭제(상태 변경)
	@GetMapping("/info/deleteQNA")
	public String deleteQNA(@RequestParam("qId") int qId, @RequestParam("page") int page, RedirectAttributes re) {
		
		int result = iService.deleteQNA(qId);
		
		if(result > 0) {
			re.addAttribute("page", page);
			return "redirect:/info/QNA";
		} else {
			return null;
		}
	}
	
	//  QNA(FAQ) 검색
	@GetMapping("/info/searchQNA")
	public String searchQNA(@RequestParam("value") String value, @RequestParam("Category") String Category, @RequestParam("search") String search, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
	
		String table = "QNA";
		String status = "q_status";
		String id = "Q_ID";
		String abc = "Q_CATEGORY";
		String type = "";
		
		Properties prop = new Properties();
		int currentPage = page;
		int listCount = 0;
		
		if(Category.equals("all")) {
			
			if(value == "") {
				type = "1";
				String title = "q_Title";
				String content = "q_Content";
				
				prop.setProperty("value", value);
				prop.setProperty("type", type);
				prop.setProperty("table", table);
				prop.setProperty("search", search);
				prop.setProperty("Category", Category);
				prop.setProperty("status", status);
				prop.setProperty("title", title);
				prop.setProperty("content", content);
				prop.setProperty("id", id);
			}else{
				type = "4";
				String title = "q_Title";
				String content = "q_Content";
				
				prop.setProperty("abc", abc);
				prop.setProperty("value", value);
				prop.setProperty("type", type);
				prop.setProperty("table", table);
				prop.setProperty("search", search);
				prop.setProperty("Category", Category);
				prop.setProperty("status", status);
				prop.setProperty("title", title);
				prop.setProperty("content", content);
				prop.setProperty("id", id);
			}
			
			listCount = iService.searchCount(prop);
		} else {
			
			prop.setProperty("table", table);
			prop.setProperty("search", search);
			prop.setProperty("Category", Category);
			prop.setProperty("status", status);
			prop.setProperty("id", id);
			
			if(value == "") {
				type = "3";
				prop.setProperty("type", type);
			} else {
				type = "2";
				
				prop.setProperty("abc", abc);
				prop.setProperty("type", type);
				prop.setProperty("value", value);
			}
			
			listCount = iService.searchCount(prop);
		}
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		
		ArrayList<QNA> list; // = iService.searchQNA(pi, prop);
		
		if(value == ""){
			if(Category.equals("all")) {
				type = "1";
			}else {
				type = "2";
			}
			prop.setProperty("type", type);
			
			list = iService.searchAllQNA(pi, prop);
		} else {
			if(Category.equals("all")) {
				type = "1";
				prop.setProperty("type", type);
			}else {
				type = "2";
				prop.setProperty("type", type);
			}
			
			list = iService.searchQNA(pi, prop);
		}
		
		
		
		
		
		
		
		
		
		
		
		if(list != null) {
			model.addAttribute("value", value);
			model.addAttribute("Category", Category);
			model.addAttribute("search", search);
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI());
			
			return "QNAList";
			
		} else {
			return null;
		}
	}
	
//  QNA(FAQ) 카테고리 선택
	@GetMapping("/info/selectCategoryQNA")
	public String selectCategory(@RequestParam("value") String value , @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		String table = "QNA";
		String status = "q_status";
		String Category = "q_category";
		
		Properties prop = new Properties();
		prop.setProperty("value", value);
		prop.setProperty("table", table);
		prop.setProperty("status", status);
		prop.setProperty("Category", Category);
		
		int listCount = iService.selectCount(prop);
		int currentPage = page;
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<QNA> list = iService.selectQNACategory(pi, prop);
		
		if(list != null) {
			model.addAttribute("value", value);
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI());
			
			return "QNAList";
			
		} else {
			return null;
		}
	}
	
	
// --------------------------    COMPANY     ----------------------------------- 	
	
	// 협력업체 리스트 이동
	@GetMapping("/info/company")
	public String company(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		int i = 1;
		
		int listCount = iService.getListCount(i);
		int currentPage = page;
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 7);
		ArrayList<Company> list = iService.selectCompanyList(pi);
				
		if(list != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI()); //getRequestURI == contextpath 제외하고 가져옴
			
			return "companyList";
		} else {
			return null;
		}				
	}
	
	// 협력업체 등록 페이지
	@GetMapping("/info/companyEnroll")
	public String companyEnroll() {
		
		return "companyEnroll";
	}
	
	// 협력업체 등록
	@PostMapping("/info/insertCompany")
	public String insertCompany(@ModelAttribute Company c, @RequestParam("sample6_postcode")String postcode,@RequestParam("sample6_address")String address,
			@RequestParam("sample6_detailAddress")String detailAddress,@RequestParam("sample6_extraAddress")String extraAddress, @RequestParam("tel1") String t1, @RequestParam("tel2") String t2, @RequestParam("tel3") String t3) {
		
		String subAddress = postcode +"@"+address+"@"+detailAddress+"@"+extraAddress;
		String phone = t1+"-"+t2+"-"+t3;
		
		c.setComAddr(subAddress);
		c.setComPhone(phone);
		
		int result = iService.insertCompany(c);
		
		if(result > 0) {
			
			return "redirect:/info/company";
		}else {
			return null;
		}		
	}
	
	// 협력업체 상세페이지 이동
	@GetMapping("/info/selectCompany")
	public String selectCompany(@RequestParam("comNo") int comNo, Model model, @RequestParam("page") int page) {						
		
		Company c = iService.selectCompany(comNo);
		
		if(c != null) {
			model.addAttribute("c", c);
			model.addAttribute("page", page);
			return "companyDetail";
		} else {
			return null;
		}
	}
	
	// 협력업체 수정페이지 이동
	@PostMapping("/info/CompanyEdit")
	public String CompanyEdit(@RequestParam("comNo") int comNo, Model model, @RequestParam("page") int page) {						
		
		Company c = iService.selectCompany(comNo);
		
		if(c != null) {
			model.addAttribute("c", c);
			model.addAttribute("page", page);
			
			return "CompanyEdit";
		} else {
			return null;
		}
	}	
	
	// 협력업체 수정
	@PostMapping("/info/updateCompany")
	public String updateCompany(@ModelAttribute Company c, @RequestParam("page") int page, @RequestParam("sample6_postcode")String postcode,@RequestParam("sample6_address")String address,
			@RequestParam("sample6_detailAddress")String detailAddress,@RequestParam("sample6_extraAddress")String extraAddress, @RequestParam("tel1") String t1, @RequestParam("tel2") String t2, @RequestParam("tel3") String t3, RedirectAttributes re) {
		
		String subAddress = postcode +"@"+address+"@"+detailAddress+"@"+extraAddress;
		String phone = t1+"-"+t2+"-"+t3;
		c.setComAddr(subAddress);
		c.setComPhone(phone);
		
		int result = iService.updateCompany(c);
		
		if(result > 0) {
			re.addAttribute("comNo", c.getComNo());
			re.addAttribute("page", page);
			return "redirect:/info/selectCompany";
		} else {
			return null;
		}
	}
	
	// 협력업체 삭제(상태 변경)
	@GetMapping("/info/deleteCompany")
	public String deleteCompany(@RequestParam("comNo") int comNo, @RequestParam("page") int page, RedirectAttributes re) {
		
		int result = iService.deleteCompany(comNo);
			
		if(result > 0) {
			
			re.addAttribute("page", page);
			return "redirect:/info/company";
		} else {
			return null;
		}
	}
	
	// 협력업체 검색
	@GetMapping("/info/searchCompany")
	public String searchCompany(@RequestParam("comCategory") String comCategory, 
			@RequestParam("Category") String Category, @RequestParam("search") String search, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		String table = "Company";
		String status = "COM_STATUS";
		
		Properties prop = new Properties();
		ArrayList<Company> list;
		PageInfo pi;
		
		int listCount = 0;
		int currentPage = 0;
		
		if(comCategory.equals("all")) {
			
			String type = "3";
			
			prop.setProperty("type", type);
			prop.setProperty("table", table);
			prop.setProperty("search", search);
			prop.setProperty("status", status);
			prop.setProperty("Category", Category);
			
			listCount = iService.searchCount(prop);
			currentPage = page;
		}else {
			
			String type = "5";
			
			prop.setProperty("comCategory", comCategory);
			prop.setProperty("type", type);
			prop.setProperty("table", table);
			prop.setProperty("search", search);
			prop.setProperty("status", status);
			prop.setProperty("Category", Category);
			
			listCount = iService.searchCount(prop);
			currentPage = page;
		}
		
		pi = Pagination.getPageInfo(currentPage, listCount, 7);
		list = iService.searchCompany(pi, prop);

		if(list != null) {
			model.addAttribute("comCategory", comCategory);
			model.addAttribute("Category", Category);
			model.addAttribute("search", search);
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI()); //getRequestURI == contextpath 제외하고 가져옴
			
			return "companyList";
		} else {
			return null;
		}
	}

}
=======
@Controller
public class InfoController {
	
	@GetMapping("map")
	public String mapView() {
		return "map";
	}
}
>>>>>>> refs/heads/develop
