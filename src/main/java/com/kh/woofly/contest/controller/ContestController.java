package com.kh.woofly.contest.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.common.PageInfo;
import com.kh.woofly.common.Pagination;
import com.kh.woofly.contest.model.service.ContestService;
import com.kh.woofly.contest.model.vo.Contest;
import com.kh.woofly.contest.model.vo.ContestAttm;
import com.kh.woofly.contest.model.vo.ContestItem;
import com.kh.woofly.contest.model.vo.Participants;
import com.kh.woofly.shop.model.exception.ShopException;
import com.kh.woofly.shop.model.vo.ProductAttm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContestController {
	
	@Autowired
	private ContestService cService;
	
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
	public String contestParticipation(@ModelAttribute Contest c, HttpSession session, Model model,  HttpServletRequest request) {
//		String id = ((Member)session.getAttribute("loginUser")).getId();	
//		n.setBoardWriter(id);
		String id = "younjun1234";
		
		ArrayList<ContestItem> itemList = cService.itemList(id);
		
		model.addAttribute("itemList", itemList);
		
		if(itemList != null) {
			model.addAttribute("list", itemList);
			model.addAttribute("loc", request.getRequestURI()); //getRequestURI == contextpath 제외하고 가져옴
			return "contestParticipation";
		} else {
			return null;
		}
	}
	
	// 콘테스트 아이템 검색
	@GetMapping("/contest/searchItem")
	@ResponseBody
	public ArrayList<ContestItem> searchItem(@RequestParam("pSearch") String pSearch, Model model) {
		
		ArrayList<ContestItem> list = cService.searchItem(pSearch);
		
		return list;
	}
	
	// 콘테스트 참가
	@PostMapping("/contest/contestEnroll")
	public String contestEnroll(@ModelAttribute Participants p, HttpSession session, @RequestParam("thumbnailFile") ArrayList<MultipartFile> thumbFiles) {
//		String id = ((Member)session.getAttribute("loginUser")).getId();
//		n.setBoardWriter(id);
		
		LocalDate today = LocalDate.now();
		
		Contest c = cService.contestId(today);
		int cNo = c.getConNo();
		
		p.setContestId(cNo);
		System.out.println(p);
		
		int result1 = cService.contestEnroll(p);
		System.out.println(result1);
		
		// 썸네일 리네임 과정
		ArrayList<ContestAttm> list = new ArrayList<ContestAttm>();
		for(int i = 0; i < thumbFiles.size(); i++) {
			MultipartFile upload = thumbFiles.get(i);
			if(!upload.getOriginalFilename().equals("")) {
				String[] returnArr = saveFile(upload);
				if(returnArr[1] != null) {
					ContestAttm t = new ContestAttm();
					t.setOriginalName(upload.getOriginalFilename());
					t.setRenameName(returnArr[1]);
					t.setAttmPath(returnArr[0]);
					t.setAttmRefNo(p.getPNo());
					t.setAttmLevel(1);
					
					list.add(t);
				}
			}
		}

		int result2 = cService.insertAttm(list);
		
		if( result1 + result2 == list.size() + 1) {
			return "redirect:/shopMain";
		} else {
			for(ContestAttm a : list) {
				deleteFile(a.getRenameName());
			}
			throw new ShopException("상품 등록을 실패하였습니다.");
		}
		
	}
	
	private String[] saveFile(MultipartFile upload) {
		
		String root = "C:\\woofly\\";
		String savePath = root + "\\contestFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		Date time = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		
		String originFileName = upload.getOriginalFilename();
		String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		
		try {
			upload.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] returnArr = new String[2];
		returnArr[0] = savePath;
		returnArr[1] = renameFileName;
		
		return returnArr;
	}
	
	private void deleteFile(String renameName) {
		String root = "C:\\woofly\\";
		String savePath = root + "\\contestFiles";
		
		File f = new File(savePath + "\\" + renameName);
		if(f.exists()) {
			f.delete();
		}
	}
	
	
}