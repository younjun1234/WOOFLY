package com.kh.woofly.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.board.model.exception.BoardException;
import com.kh.woofly.board.model.service.BoardService;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.board.model.vo.Reply;
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
			ArrayList<Board> list = bService.selectFreeBoardList(pi, 1);		
			ArrayList<Attachment> aList = bService.selectAttmFreeBoardList(null);
			
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
			Board b = bService.selectFreeBoard(bNo);
			ArrayList<Attachment> list = bService.selectAttmFreeBoardList((Integer)bNo); 
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
			
			int result = bService.insertFreeBoard(b); // 글 내용을 board 테이블에 저장
			
		    if (result > 0) {
		        if (files != null && !files.isEmpty()) {
		            ArrayList<Attachment> attachments = new ArrayList<>();

		            for (MultipartFile file : files) {
		                String savedFileName = saveFile(file); 
		                
		                Attachment attachment = new Attachment();
		                attachment.setAttmPath(savedFileName);
		                attachment.setAttmRefType("B"); 
		                attachment.setAttmRefNo(b.getBNo()); 
		                
		                attachments.add(attachment);
		            }

		            result = bService.insertFreeAttm(attachments);
//		            if (result <= 0) {
//		                // 첨부 파일 저장 실패 시 예외 처리 가능
//		            }
		        }
		        return "redirect:/board/free"; 
		    } else {
		        throw new BoardException("게시글 작성을 실패하였습니다.");
		    }
		}
			
		
		
		// 파일 저장소 파일 저장(copy)
		public String saveFile(MultipartFile file) {
			String os = System.getProperty("os.name").toLowerCase();
			String savePath = null;
			if (os.contains("win")) {
				savePath = "C:\\uploadFiles\\woolfy";
			} else if (os.contains("mac")) {
				savePath = "/Users/younjun/Desktop/WorkStation/uploadFiles/woofly";
			}
			
			File folder = new File(savePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			// 2. 저장된 file rename 
			Date time = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			int ranNum = (int)(Math.random()*100000);
			
			String originFileName = file.getOriginalFilename();
			String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
			
			// 3. rename된 파일을 저장소에 저장
			String renamePath = folder + "/" + renameFileName;
			try {
				file.transferTo(new File(renamePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			return renameFileName;
		}
		
		
		private void deleteFile(String fileName) {
			String os = System.getProperty("os.name").toLowerCase();
			String savePath = null;
			if (os.contains("win")) {
				savePath = "C:\\uploadFiles\\woolfy";
			} else if(os.contains("mac")) {
				savePath = "/Users/younjun/Desktop/WorkStation/uploadFiles/woofly/";
			}
			File f = new File(savePath + fileName);
			if(f.exists()) {
				f.delete();
			}
			
		}

		@GetMapping("/board/free/edit")
		public String freeBoardEdit() {
			
			return "freeBoardEdit";
		}
		
		@PostMapping("/board/free/delete")
		public String deleteBoard(@RequestParam("bNo") int bNo) throws BoardException {
			int result1 = bService.deleteFreeBoard(bNo);
			int result2 = bService.statusNAttm(bNo);
			System.out.println(bNo);
			if(result1 > 0 && result2> 0) {
				return "redirect:/board/free";
			} else {
				throw new BoardException("게시글 삭제 실패");
			}
		}
		
		@GetMapping(value="/insertFreeReply.yk", produces="application/json; charset=UTF-8")
		@ResponseBody
		public String insertFreeReply(@ModelAttribute Reply r, @RequestParam("bNo") int bNo) {
			int result = bService.insertFreeReply(r);
			ArrayList<Reply> list = bService.selectFreeReply(bNo);
			// list의 안의 여러개의 reply 중 하나하나의 reply는 json에 담고 그것들을 한곳에 담아주는 것은 jArr이 한다.
			
			JSONArray jArr = new JSONArray();  // list 방식
			for(Reply reply : list) {
				JSONObject json = new JSONObject();  // map(key-value) 맵 방식
				json.put("rNo", reply.getRNo());
				json.put("bType", reply.getBType());
				json.put("bNo", reply.getBNo());
				json.put("reContent", reply.getReContent());
				json.put("reDate", reply.getReDate());
				json.put("reLike", reply.getReLike());
				json.put("reDStatus", reply.getReDStatus());
				json.put("reRStatus", reply.getReRStatus());
				json.put("mbId", reply.getMbId());
				jArr.put(json);
			}
			
			
			return jArr.toString();
			
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
    