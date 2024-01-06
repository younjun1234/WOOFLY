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
import com.kh.woofly.board.model.service.BoardService;
import com.kh.woofly.board.model.vo.Board;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
//	@Autowired
//	private BoardServiceImpl bService;
	
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
		@GetMapping("/board/lost")
		public String lostBoardView(@RequestParam(value="page", defaultValue="1") int page, 
									Model model,
									HttpServletRequest request) throws BoardException {
			
			int mListCount = bService.getMlistCount(1);
			
			PageInfo pi = Pagination.getPageInfo(page, mListCount, 10);
			ArrayList<LostBoard> mList = bService.selectLostBoardList(pi, 1);		
			ArrayList<Attachment> aList = bService.selectAttmLostBoardList(null);
			
			if(mList != null) {
				model.addAttribute("pi", pi);
				model.addAttribute("mList", mList);
				model.addAttribute("aList", aList);
				model.addAttribute("loc", request.getRequestURI());
				
				return "lostBoard";
			} else {
				throw new BoardException("게시글 조회 실패");
			}
		}
		
		//	첨부파일 게시글 작성 //
		// 글쓰기
		@GetMapping("/board/lost/write")
		public String lostBoardWrite() {
			return "lostBoardWrite";
		}
		
		// 첨부파일 게시글 상세보기 //
		@GetMapping("/board/lost/detail")
		public String lostBoardDetail(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam("mNo") int mNo, HttpSession session, Model model) throws BoardException {
		
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
				System.out.println(m);
				return "lostBoardDetail";
			} else {
				throw new BoardException("게시글 상세보기를 실패하였습니다.");
			}
		}
		
			
		//	첨부파일 게시글 작성(등록) //
		@PostMapping("/board/lost/insertLostBoard")
		public String insertLostBoard(@ModelAttribute LostBoard m, @RequestParam("file") ArrayList<MultipartFile> files, HttpSession session, HttpServletRequest request) throws BoardException {
			String LostboardWriter = ((Member)session.getAttribute("loginUser")).getMbId();
			m.setMbId(LostboardWriter);
			int result = bService.insertLostBoard(m);
			
			if(result > 0) {
				return "redirect:list.at";
			} else {
				throw new BoardException("게시글 작성을 실패하였습니다.");
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
		
//		수정한 첨부파일 게시글을 업로드 //
//		@PostMapping("/board/lost/updateLostBoard")
//		   public String updateLostBoardAttm(@ModelAttribute LostBoard m,
//		                     @RequestParam("page") int page,
//		                     @RequestParam("deleteLostBoardAttm") String[] deleteLostBoardAttm,
//		                     @RequestParam("file") ArrayList<MultipartFile> files,
//		                     HttpServletRequest request, RedirectAttributes re) {
//		      
//		      m.setLostBoardType(2);
//		      
//		      ArrayList<Attachment> mList = new ArrayList<>();
//		      for(int i = 0; i < files.size(); i++) {
//		         MultipartFile upload = files.get(i);
//		         
//		         if(!upload.getOriginalFilename().equals("")) {
//		            String[] returnArr = saveFile(upload);
//		            if(returnArr[1] != null) {
//		               Attachment a = new Attachment();
//		               a.setOriginalName(upload.getOriginalFilename());
//		               a.setRenameName(returnArr[1]);
//		               a.setAttmPath(returnArr[0]);
//		               
//		               mList.add(a);
//		            }
//		         }
//		      }
//		      
//		      ArrayList<String> delRename = new ArrayList<>();
//		      ArrayList<Integer> delLevel = new ArrayList<>();
//		      
//		      for(int i = 0; i < deleteLostBoardAttm.length; i++) {
//		         //System.out.println(deleteAttm[i]);
//		         String rename = deleteLostBoardAttm[i];      // 취소바구니에 담긴 스트링 배열
//		         if(!rename.equals("none")) {
//		            String[] split = rename.split("/");
//		            delRename.add(split[0]);
//		            delLevel.add(Integer.parseInt(split[1]));
//		         }
//		      }
//		      
//		      int deleteLostAttmResult = 0;
//		      int updateLostResult = 0;
//		      boolean existBeforeLostAttm = true;
//		      if(!delRename.isEmpty()) {
//		    	  deleteLostAttmResult = bService.deleteLostAttm(delRename); // DB삭제
//		         if(deleteLostAttmResult > 0) {
//		            for(String rename : delRename) {
//		               deleteFile(rename);   // 서버 저장소에서 삭제
//		            }
//		         }
//		         
//		         // delRename : 삭제하고자 하는 명시된 파일의 개수만 담겨있음 
//		         // deleteAttm : 파일삭제를 명시 안해도 none값으로 들어오기 때문에
//		         //            기존파일 총량을 알 수 있음
//		         
//		         if(delRename.size() == deleteLostAttm.length) {
//		            //기존 파일 전부 삭제
//		        	 existBeforeLostAttm = false;
//		            if(mList.isEmpty()) {
//		               m.setLostBoardType(1);
//		            }
//		         } else {
//		            // 기존 파일 일부 삭제
//		            // 썸네일레벨을 가지고 있는 파일이 있는지 확인 후 조치
//		            for(int level : delLevel) {
//		               if(level == 0) {
//		                  bService.updateAttmLevel(b.getBoardId());
//		                  break;
//		               }
//		            }
//		         }
//		      }
//		      
//		      for(int i = 0; i < mList.size(); i++) {
//		         Attachment a = mList.get(i);
//		         a.setRefBoardId(m.getLostBoardId());
//		         
//		         // 기존 첨부파일이 남아있을 때
//		         if(existBeforeLostAttm) {
//		            a.setAttmLevel(1);
//		         } else {
//		            // 기존 첨부파일이 남아있지 않으면
//		            if(i == 0) {
//		               a.setAttmLevel(0);
//		            } else {
//		               a.setAttmLevel(1);
//		            }
//		         }
//		      }
//		      
//		      updateLostResult = bService.updateLostBoard(m);
//		      int updateAttmResult = 0;
//		      if(!mList.isEmpty()) {
//		         updateAttmResult = bService.insertLostAttm(mList);
//		      }
//		      
//		      if(updateLostResult + updateAttmResult == mList.size() + 1) {
//		         if(delRename.size() == deleteLostAttm.length && updateAttmResult == 0) {
//		            return "redirect:list.bo";
//		         } else {
//		            re.addAttribute("bId", m.getLostBoardId());
//		            re.addAttribute("page", page);
//		            return "redirect:selectAttm.at";
//		         }
//		      } else {
//		         throw new BoardException("첨부파일 게시글 수정을 실패하였습니다.");
//		      }
//		   }
		
		
		
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
		
		
		
}
    