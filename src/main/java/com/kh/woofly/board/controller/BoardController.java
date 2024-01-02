package com.kh.woofly.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.board.model.exception.BoardException;
import com.kh.woofly.board.model.service.BoardServiceImpl;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.common.Pagination;
import com.kh.woofly.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl bService;
	
		//<< 글형식 >>
		// 
		// 1. 자유게시판 //
		
		@GetMapping("/board/free")
		public String freeBoardView(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			model.addAttribute("page", page);
			return "freeBoard";
		}
		
		@GetMapping("/board/free/detail")
		public String freeBoardDetail(@RequestParam(value="page", defaultValue="1") String page, Model model) {
			
			return "freeBoardDetail";
		}
		
		@GetMapping("/board/free/write")
		public String freeBoardWrite() {
			
			return "freeBoardWrite";
		}
		
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
		// 글 목록
		//	첨부파일 게시글 조회 //
		@GetMapping("/board/lost")
		public String selectlostBoardView() {
			return "lostBoard";
		}
		
//		@GetMapping("/board/lost")
//		public String selectlostBoardView(@RequestParam(value = "page", defaultValue = "1") int page, 
//											Model model, 
//											HttpServletRequest request) {
//			int listCount = bService.getListCount(2);
//			PageInfo pi = Pagination.getPageInfo(page, listCount, 9);
//			ArrayList<LostBoard> bList = bService.selectBoardList(pi, 2);
//			ArrayList<Attachment> aList = bService.selectAttmBoardList(null);
//			
//			if(bList != null) {
//				model.addAttribute("pi", pi);
//				model.addAttribute("bList", bList);
//				model.addAttribute("aList", aList);
//				model.addAttribute("loc", request.getRequestURI());
//				
//				/* lostBoard */
//				return "lostBoard";
//			} else {
//				throw new BoardException("첨부파일 게시글 조회 실패");
//			}
//		}
		
		//	첨부파일 게시글 작성 //
		// 글쓰기
		@GetMapping("/board/lost/write")
		public String lostBoardWrite() {
			return "lostBoardWrite";
		}
			
		//	첨부파일 게시글 등록 //
		@PostMapping("/board/lost/write")
		public String insertAttm(@ModelAttribute LostBoard b, 
				@RequestParam("file") ArrayList<MultipartFile> files,
				HttpServletRequest request) {
			
			String id = ((Member)request.getSession().getAttribute("loginUser")).getMbNickName();
			b.setMbNickName(id);
			
			// saveFile을 거쳐 정제된 사진들이 담겨있는 listㄱ
			ArrayList<Attachment> list = new ArrayList<>();
			for(int i = 0; i < files.size(); i++) { // 나중에 밖에서 쓰려고 반복문 사용
				MultipartFile upload = files.get(i);
				if(!upload.getOriginalFilename().equals("")) {
					String[] returnArr = saveFile(upload);
					if(returnArr[1] != null) {
						Attachment a = new Attachment();
						a.setOriginalName(upload.getOriginalFilename());
						a.setRenameName(returnArr[1]);
						a.setAttmPath(returnArr[0]);
						
						list.add(a);
					}
				}
			}
			
			// 레벨 조정(썸네일 유무)
			for(int i = 0; i < list.size(); i++) {
				Attachment a = list.get(i);
				if(i == 0) {
					a.setAttmLevel(0);
				} else {
					a.setAttmLevel(1);
				}
			}
			
			int result1 = 0;   // 보드 결과값
			int result2 = 0;   // 사진 결과값
//			 if(list.isEmpty()|| !list.isEmpty()) { // 사진이 비어있거나 사진이 있을 때
////		         b.setBoardType(1);
////		         result1 = bService.insertBoard(b);
////		      } else {
////		         b.setBoardType(2);
////		         result1 = bService.insertBoard(b);
////		         
////		         // 각 사진들 해당 보드 번호 달아주기
//		         for(Attachment a : list) {
//		            //System.out.println(b);
//		            a.setRefBoardId(b.getBoardId());
//		         }
//		         result2 = bService.insertAttm(list);
//		      }
			
			
			// 보드 개수 + list.size의 길이 더해서 판별하기
			if(result1 + result2 == list.size()+1) {
				if(result2 == 0) {
//					return "redirect:list.bo";
//				} else {
					return "redirect:/board/lost";
				}
			} else {
				for(Attachment a : list) {
					deleteFile(a.getRenameName());
				}
				throw new BoardException("게시판 작성을 실패하였습니다.");
			}
		}
		

		// 글 상세보기
		@GetMapping("/board/lost/detail")
		public String lostBoardDetail() {
			
			return "lostBoardDetail";
		}
		
		// 글 수정
		@GetMapping("/board/lost/edit")
		public String lostBoardEdit() {
			
			return "lostBoardEdit";
		}
		
		
		// 파일을 저장 경로 및 rename하여(이름 안겹치도록) 나중에 찾기 쉽게 함.
		
		public String[] saveFile(MultipartFile upload) {
		      String root = "C:\\";
		      String savePath = root + "\\uploadFiles";
		      
		      File folder = new File(savePath);
		      if(!folder.exists()) {
		         folder.mkdirs();
		      }
		      
		      // 2. 저장될 파일 rename
		      Date time = new Date(System.currentTimeMillis());
		      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		      int ranNum = (int)(Math.random()*100000);
		      
		      String originFileName = upload.getOriginalFilename();
		      String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		      
		      // 3. rename된 파일을 저장소에 저장
		      String renamePath = folder + "\\" + renameFileName;
		      try {
		         upload.transferTo(new File(renamePath));
		      } catch (IllegalStateException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		      
		      String[] returnArr = new String[2];
		      returnArr[0] = savePath;   // 경로 있음
		      returnArr[1] = renameFileName; // renameName이 있음
		      
		      return returnArr;
		   }
		
		public void deleteFile(String fileName) {
			String root = "C:\\";
			String savePath = root + "\\uploadFiles";
			
			File f = new File(savePath + "\\" + fileName);
			if(f.exists()) { // f가 있으면 
				f.delete();// 삭제
			}
		}
		
		
//		// 글쓰기
//		@GetMapping("/board/lost/write")
//		public String lostBoardWrite() {
//			
//			
//			return "lostBoardWrite";
//		}
		
		
		
}
    