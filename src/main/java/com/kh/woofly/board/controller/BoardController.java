package com.kh.woofly.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.board.model.exception.BoardException;
import com.kh.woofly.board.model.service.BoardService;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.common.Pagination;
import com.kh.woofly.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
		@Autowired
		private BoardService bService;
	
		//<< 글형식 >>
		// 
		// 1. 자유게시판 //
		
		@GetMapping("/board/free")
		public String freeBoardView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) throws BoardException {
			
			int listCount = bService.getListCount(1);
			
			PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
			ArrayList<Board> list = bService.selectBoardList(pi, 1);		
			ArrayList<Attachment> aList = bService.selectAttmBoardList(null);
			
			if(list != null) {
				model.addAttribute("pi", pi);
				model.addAttribute("list", list);
				model.addAttribute("aList", aList);
				model.addAttribute("loc", request.getRequestURI());
				
				return "freeBoard";
			} else {
				throw new BoardException("게시글 조회 실패");
			}
	
				
		}
		
		@GetMapping("/board/free/detail")
		public String freeBoardDetail(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam("bNo") int bNo, HttpSession session, Model model) throws BoardException {
		
			Member loginUser = (Member)session.getAttribute("loginUser");
			String id = null;
			if(loginUser != null) {
				id = loginUser.getMbId();
			}
			Board b = bService.selectBoard(bNo);
			ArrayList<Attachment> list = bService.selectAttmBoardList((Integer)bNo); 
			if(b != null) {
				model.addAttribute("b", b);
				model.addAttribute("page", page);
				model.addAttribute("list", list);
				System.out.println(b);
				return "freeBoardDetail";
			} else {
				throw new BoardException("게시글 상세보기를 실패하였습니다.");
			}
		}
		
		@GetMapping("/board/free/write")
		public String freeBoardWrite() {
			
			return "freeBoardWrite";
		}
		
		@PostMapping("/board/free/insertFreeBoard")
		public String insertFreeBoard(@ModelAttribute Board b, @RequestParam("file") ArrayList<MultipartFile> files, HttpSession session, HttpServletRequest request) throws BoardException {
			String boardWriter = ((Member)session.getAttribute("loginUser")).getMbId();
			b.setMbId(boardWriter);
			
			int result = bService.insertBoard(b);
			
			if(result > 0) {
				return "redirect:list.bo";
			} else {
				throw new BoardException("게시글 작성을 실패하였습니다.");
			}
		}
		
		/*
		 * public String[] saveFile(MultipartFile upload) { String root = "C:\\"; String
		 * savePath = root + "\\finaluploadFiles";
		 * 
		 * File folder = new File(savePath); if(!folder.exists()) { folder.mkdir(); }
		 * //2. 저장될 파일 rename Date time = new Date(System.currentTimeMillis());
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); int ranNum
		 * = (int)(Math.random()*100000);
		 * 
		 * String originFileName = upload.getOriginalFilename(); String renameFileName =
		 * sdf.format(time) + ranNum +
		 * originFileName.substring(originFileName.lastIndexOf(".")); //substring는 일부를
		 * 추출해오는 것 //(학생랜덤.xslx)이면 .뒤의 xslx만 가져와 확장자를 추출해 낼 수 있음
		 * 
		 * //3. rename된 파일을 저장소에 저장
		 * 
		 * String renamePath = folder + "\\" + renameFileName; try {
		 * upload.transferTo(new File(renamePath)); } catch (IllegalStateException e) {
		 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * String[] returnArr = new String[2]; returnArr[0] = savePath;//파일 저장소 경로
		 * returnArr[1] = renameFileName;
		 * 
		 * return returnArr; }
		 * 
		 * 
		 * private void deleteFile(String fileName) { String root = "C:\\"; String
		 * savePath = root + "\\uploadFiles";
		 * 
		 * File f = new File(savePath + " \\" + fileName); if(f.exists()) { f.delete();
		 * }
		 * 
		 * 
		 * }
		 */

		@GetMapping("/board/free/edit")
		public String freeBoardEdit() {
			
			return "freeBoardEdit";
		}
		
		// 2. 도그워커 //

		@GetMapping("/board/dw")
		public String dwBoardView(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			model.addAttribute("page", page);
			return "dwBoard";
		}
		

		@GetMapping("/board/dwReview")
		public String dwReviewBoard(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			model.addAttribute("page", page);
			return "dwReviewBoard";
		}
		@GetMapping("/board/dw/detail")
		public String dwBoardDetail(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			return "dwBoardDetail";
		}
		
		@GetMapping("/board/dw/write")
		public String dwBoardWrite() {
			
			return "dwBoardWrite";
		}
		
		@GetMapping("/board/dw/edit")
		public String dwBoardEdit() {
			
			return "dwBoardEdit";
		}
			
		// 3. 산책메이트 //
		
		@GetMapping("/board/wm")
		public String wmBoardView(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			model.addAttribute("page", page);
			return "wmBoard";
		}
		
		@GetMapping("/board/wmReview")
		public String wmReviewBoard(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			model.addAttribute("page", page);
			return "wmReviewBoard";
		}
		
		@GetMapping("/board/wm/detail")
		public String wmBoardDetail(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			return "wmBoardDetail";
		}
		
		@GetMapping("/board/wm/write")
		public String wmBoardWrite() {
			
			return "wmBoardWrite";
		}
		
		@GetMapping("/board/wm/edit")
		public String wmBoardEdit() {
			
			return "wmBoardEdit";
		}
			
		
		//<< 카드댁 형식 >>
		// 
		// 1. 중고게시판 //
		
		@GetMapping("/board/used")
		public String usedBoardView() {
			
			return "usedBoard";
		}
		
		@GetMapping("/board/usedReview")
		public String usedReviewBoard(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			return "usedReviewBoard";
		}
		
		@GetMapping("/board/used/detail")
		public String usedBoardDetail() {
			
			return "usedBoardDetail";
		}
		
		@GetMapping("/board/usedReview/detail")
		public String usedReviewBoardDetail() {
			
			return "usedReviewBoardDetail";
		}
		
		@GetMapping("/board/used/edit")
		public String usedBoardEdit() {
			
			return "usedBoardEdit";
		}
		
		@GetMapping("/board/usedReview/edit")
		public String usedReviewBoardEdit() {
			
			return "usedReviewBoardEdit";
		}
		
		@GetMapping("/board/used/write")
		public String usedBoardWrite() {
			
			
			return "usedBoardWrite";
		}
		
		@GetMapping("/board/usedReview/write")
		public String usedReviewBoardWrite() {
			
			
			return "usedReviewBoardWrite";
		}
		
		// 2. 실종신고 //
		@GetMapping("/board/lost")
		public String lostBoardView() {
			
			return "lostBoard";
		}
		
		@GetMapping("/board/lost/detail")
		public String lostBoardDetail() {
			
			return "lostBoardDetail";
		}
		
		@GetMapping("/board/lost/edit")
		public String lostBoardEdit() {
			
			return "lostBoardEdit";
		}
		
		@GetMapping("/board/lost/write")
		public String lostBoardWrite() {
			
			
			return "lostBoardWrite";
		}
}
    