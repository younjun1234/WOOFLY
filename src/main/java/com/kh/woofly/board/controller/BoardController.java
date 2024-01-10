package com.kh.woofly.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.woofly.board.model.exception.BoardException;
import com.kh.woofly.board.model.service.BoardService;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.common.Pagination;
import com.kh.woofly.member.model.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
//		@Autowired
//		private BoardService bService;
		
		private final BoardService bService;
		
		@Autowired
		public BoardController(BoardService bService) {
			this.bService = bService;
		}
		
	
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
				//System.out.println(b);
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
		
//		첨부파일 게시글 조회 //
//			@GetMapping("/board/used")
//			public String usedBoardView(@RequestParam(value="page", defaultValue="1") int page, 
//										Model model,
//										HttpServletRequest request) throws BoardException {
//				
//				int uListCount = bService.getUlistCount(1);
//				
//				PageInfo pi = Pagination.getPageInfo(page, uListCount, 10);
//				ArrayList<UsedBoard> mList = bService.selectUsedBoardList(pi, 1);		
//				ArrayList<Attachment> aList = bService.selectAttmUsedBoardList(null);
//				
//				if(uList != null) {
//					model.addAttribute("pi", pi);
//					model.addAttribute("uList", uList);
//					model.addAttribute("aList", aList);
//					model.addAttribute("loc", request.getRequestURI());
//					
//					return "usedBoard";
//				} else {
//					throw new BoardException("게시글 조회 실패");
//				}
//			}
		
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
		// 게시글 목록 페이지로 이동할 때 필요한 데이터를 모델에 담아 뷰로 전달하는 역할
		
		// 게시글 목록 조회
	    @GetMapping("/board/lost")
	    public String lostBoardView(@RequestParam(value="page", defaultValue="1") int page,
					    			@RequestParam(value = "searchType", required = false) String searchType,
					    			@RequestParam(value = "searchKeyword", required = false) String searchKeyword,
	                                Model model,
	                                HttpServletRequest request) throws BoardException {
	        if (searchType == null || searchKeyword == null) { // 게시글 검색을 하지 않을 때(=검색어가 없을 때)
	        	int listCount = bService.getMlistCount(1);
	        	
	        	PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
	        	ArrayList<LostBoard> mList = bService.selectLostBoardList(pi, 1);      
	        	ArrayList<Attachment> aList = bService.selectAttmLostBoardList(null);
	        	//System.out.println(mList);
	        	if(mList.isEmpty()) {
	        		model.addAttribute("message", "게시글이 없습니다.");
	        	} else {
	        		model.addAttribute("pi", pi);
	        		model.addAttribute("mList", mList); // 게시글 목록을 'mList'라는 이름으로 모델에 추가
	        		model.addAttribute("aList", aList); // 첨부파일 목록 추가
	        	}
	        } else { // 게시글 검색을 할 때(= 검색어가 있을 때// searchType(작성자, 글제목, 작성자+글제목), searchKeyword()
	        	HashMap<String, String> map = new HashMap<>();
				map.put("searchKeyword", searchKeyword);
				map.put("searchType", searchType);
				int listCount = bService.getMlistCount(1); // 추가
				PageInfo pi = Pagination.getPageInfo(page, listCount, 10); // 추가
				ArrayList<LostBoard> searchResults = bService.searchLostBoards(map);
				model.addAttribute("mList", searchResults);
				model.addAttribute("pi", pi);
	        }
	        
	        model.addAttribute("loc", request.getRequestURI());
	        
	        return "lostBoard";
	    }
		
//	    글작성 //
	    @GetMapping("/board/lost/write")
	      public String LostBoardWrite() {
	         
	         return "lostBoardWrite";
	      }
	    
//	      글 등록//
	      @PostMapping("/board/lost/insertLostBoard")
	      public String insertLostBoard(@ModelAttribute LostBoard m, 
	    		  						@RequestParam("file") ArrayList<MultipartFile> files, 
	    		  						HttpSession session, HttpServletRequest request) throws BoardException {
	         String boardWriter = ((Member)session.getAttribute("loginUser")).getMbId();
	         m.setMbId(boardWriter);
	         //System.out.println(boardWriter);
	         int result1 = bService.insertLostBoard(m); // 글 내용을 board 테이블에 저장
	         //System.out.println(b.getBNo());
	         
	         System.out.println(m);
	         ArrayList<Attachment> attachments = new ArrayList<>();
	         for(int i = 0; i<files.size(); i++) {
	            MultipartFile upload = files.get(i);
	            if(!upload.getOriginalFilename().equals("")) {
	               String[] returnArr = saveFile(upload);
	               if(returnArr[1] != null) {
	                  Attachment attachment = new Attachment();
	                  attachment.setOriginalName(upload.getOriginalFilename());
	                  attachment.setRenameName(returnArr[1]);
	                  attachment.setAttmPath(returnArr[0]);
	                  attachment.setAttmRefType("M");
	                  attachment.setAttmRefNo(m.getMNo());
	                  
	                  attachments.add(attachment);
//	                  System.out.println(attachment.getAttmRefType());
	               }
	            }
	         }
	         
	         for(int i=0; i < attachments.size(); i++) {
	            Attachment a = attachments.get(i);
	            if(i == 0) {
	               a.setAttmLevel(1);
	            } else {
	               a.setAttmLevel(2);
	            }
	         }
	         
	         
	         int result2 = bService.insertLostAttm(attachments);
	         System.out.println(attachments);
	         //System.out.println(result1);
	         //System.out.println(result2);
	         
	         System.out.println(attachments);
	         if(result1 + result2 > 0) {
	            return "redirect:/board/lost";
	         } else {
	            for(Attachment a : attachments) {
	               deleteFile(a.getRenameName());
	            }
	            throw new BoardException("게시글 작성을 실패하였습니다.");
	          }
	      }
	         
	      
	      
	      // 파일 저장소 파일 저장(copy)
	      private String[] saveFile(MultipartFile upload) {
	         
	         String root = "C:\\woofly\\";
	         String savePath = root + "\\board";
	         
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
	         String savePath = root + "\\board";
	         
	         File f = new File(savePath + "\\" + renameName);
	         if(f.exists()) {
	            f.delete();
	         }
	      }


		
		
		// 첨부파일 게시글 상세보기 //
				@GetMapping("/board/lost/detail")
				public String lostBoardDetail(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(name="mNo", required=false) Integer mNo, HttpSession session, Model model) throws BoardException {
				
					Member loginUser = (Member)session.getAttribute("loginUser");
					String id = null;
					if(loginUser != null) {
						id = loginUser.getMbId();
					}
					LostBoard m = bService.selectLostBoard(mNo);
					ArrayList<Attachment> mList = bService.selectAttmLostBoardList((Integer)mNo); 
					
					if(m != null) {
						model.addAttribute("m", m);
						model.addAttribute("page", page);
						model.addAttribute("mList", mList);
						//System.out.println(m);
						return "lostBoardDetail";
					} else {
						throw new BoardException("게시글 상세보기를 실패하였습니다.");
					}
				}
		
			

		// 글 수정
		@GetMapping("/board/lost/edit")
		public String updateLostBoardEdit(@RequestParam("boardId") int bId,
				 @RequestParam("page") int page,
				 Model model) {
			LostBoard m = bService.selectLostBoard(bId, null);
			ArrayList<Attachment> mList = bService.selectAttmLostBoardList(bId);
			model.addAttribute("m", m);
			model.addAttribute("page", page);
			model.addAttribute("mList", mList);
			
			return "lostBoardEdit";
		}
		
		
		
		
}
    