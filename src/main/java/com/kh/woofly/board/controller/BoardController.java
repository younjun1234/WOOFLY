package com.kh.woofly.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		// 검색기능 //
		@GetMapping("/board/lost/search")
		public String searchLostBoard(@RequestParam(value = "searchType", required = false) String searchType,
									  @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
									  Model model){
			List<LostBoard> searchResults = bService.searchLostBoards(searchType, searchKeyword);
			model.addAttribute("list", searchResults);
			return "lostBoardSearch";
		}
		
		
		// 글 목록
		//	첨부파일 게시글 조회 //
		// 게시글 목록 페이지로 이동할 때 필요한 데이터를 모델에 담아 뷰로 전달하는 역할
//		@GetMapping("/board/lost")
//		public String lostBoardView(@RequestParam(value="page", defaultValue="1") int page, 
//									Model model,
//									HttpServletRequest request) throws BoardException {
//			
//			int mListCount = bService.getMlistCount(1);
//			
//			PageInfo pi = Pagination.getPageInfo(page, mListCount, 10);
//			ArrayList<LostBoard> mList = bService.selectLostBoardList(pi, 1);		
//			model.addAttribute("m", mList);
//			ArrayList<Attachment> aList = bService.selectAttmLostBoardList(null);
////			ArrayList<Member> mbList = bService.selectMemberList(pi, 1);	
//			
//			if(mList != null) {
//				// 모델에 데이터 추가
//				model.addAttribute("pi", pi);
//				model.addAttribute("mList", mList);
//				model.addAttribute("aList", aList);
//				model.addAttribute("loc", request.getRequestURI());// 특정 페이지에서 다른 페이지로 이동할 때 사용
//				// 원래 있던 것들(위)에 더 추가(아래ㄱ)
//				model.addAttribute("mTitle", "글 제목");
//				model.addAttribute("mbNickName", "작성자 닉네임");
//				
//				
//				// 뷰 이름 반환
//				return "lostBoard";
//			} else {
//				throw new BoardException("게시글 조회 실패");
//			}
//		}
//		: 특정 페이지에 해당하는 게시글 목록을 가져와 모델에 추가하고, 
//		뷰로 전달하여 해당 페이지에 출력. 페이지당 게시글 수, 현재 페이지 등의 정보는 
//		Pagination 유틸리티 클래스를 통해 계산되고, 이를 기반으로 게시글 목록을 가져옴.
		
		
	    // 게시글 목록 조회
//	    @GetMapping("/board/lost")
//	    public String lostBoardView(@RequestParam(value="page", defaultValue="1") int page, 
//	                                Model model,
//	                                HttpServletRequest request) throws BoardException {
//	        
//	        int listCount = bService.getMlistCount(1);
//	        
//	        PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
//	        ArrayList<LostBoard> mList = bService.selectLostBoardList(pi, 1);      
//	        ArrayList<Attachment> aList = bService.selectAttmLostBoardList(null);
//	        
//	        if(mList.isEmpty()) {
//	            model.addAttribute("message", "게시글이 없습니다.");
//	        } else {
//	            model.addAttribute("mList", mList); // 게시글 목록을 'mList'라는 이름으로 모델에 추가
//	            model.addAttribute("aList", aList); // 첨부파일 목록 추가
//	        }
//	        
//	        model.addAttribute("pi", pi);
//	        model.addAttribute("loc", request.getRequestURI());
//	        
//	        return "lostBoard";
//	    }
		
		// 게시글 목록 조회
	    @GetMapping("/board/lost")
	    public String lostBoardView(@RequestParam(value="page", defaultValue="1") int page, 
	                                Model model,
	                                HttpServletRequest request) throws BoardException {
	        
	        int listCount = bService.getMlistCount(1);
	        
	        PageInfo pi = Pagination.getPageInfo(page, listCount, 10);
	        ArrayList<LostBoard> mList = bService.selectLostBoardList(pi, 1);      
	        ArrayList<Attachment> aList = bService.selectAttmLostBoardList(null);
	        
	        if(mList.isEmpty()) {
	            model.addAttribute("message", "게시글이 없습니다.");
	        } else {
	            model.addAttribute("mList", mList); // 게시글 목록을 'mList'라는 이름으로 모델에 추가
	            model.addAttribute("aList", aList); // 첨부파일 목록 추가
	        }
	        
	        model.addAttribute("pi", pi);
	        model.addAttribute("loc", request.getRequestURI());
	        
	        return "lostBoard";
	    }


		
		
		//	첨부파일 게시글 작성 //
		// 글쓰기
		// 글작성 버튼 눌렀을 때 게시글 작성 페이지로 이동하는 역할.
		@GetMapping("/board/lost/write")
		public String lostBoardWrite(@RequestParam(value="page", defaultValue="1") int page, 
									 Model model,
									 HttpServletRequest request,
									 HttpSession session) throws BoardException {
			Member loginUser = (Member) session.getAttribute("loginUser");//사용자의 로그인 정보
			int mListCount = bService.getMlistCount(1); // 글 목록 총 개수

			PageInfo pi = Pagination.getPageInfo(page, mListCount, 10);
			ArrayList<LostBoard> mList = bService.selectLostBoardList(pi, 1); // 현재 페이지에 해당하는 게시글 목록을 가져옴		
			ArrayList<Attachment> aList = bService.selectAttmLostBoardList(null);//bService 객체의 selectAttmLostBoardList 메서드를 호출하여 첨부 파일 목록을 가져옴
			
			// LostBoard 객체 생성
			LostBoard lostBoard = new LostBoard();
			lostBoard.setMTitle("글 제목");
			lostBoard.setMbNickName("작성자 닉네임");
			
			if(mList != null) {
				// 모델에 데이터 추가
				model.addAttribute("pi", pi); // 페이징 정보
				model.addAttribute("mList", mList);// 게시글 목록
				model.addAttribute("aList", aList);//첨부파일 목록
				model.addAttribute("loc", request.getRequestURI());// 특정 페이지에서 다른 페이지로 이동할 때 사용
				model.addAttribute("loginUser", loginUser); //로그인한 사용자 정보
				model.addAttribute("lostBoard", lostBoard);
				model.addAttribute("mTitle", "글 제목"); // 임시로 사용되는 글제목
				model.addAttribute("mbNickName", "작성자 닉네임"); // 작성자 닉네임
				
				// 뷰 이름 반환(게시글 작성 페이지로 이동할 때 필요한 데이터를 모델에 담아 뷰로 전달하는 역할)
				return "lostBoardWrite";
				
			} else {
				throw new BoardException("글 작성 페이지로 이동 실패");
			}
		}
		
//		//	첨부파일 게시글 작성(등록) //
//		@PostMapping("/board/lost/insertLostBoard")
//		public String insertLostBoard(@ModelAttribute LostBoard m, @RequestParam("file") ArrayList<MultipartFile> files, HttpSession session, HttpServletRequest request) throws BoardException {
//			String LostboardWriter = ((Member)session.getAttribute("loginUser")).getMbId();
//			m.setMbId(LostboardWriter);
//			int result = bService.insertLostBoard(m);
//			
//			if(result > 0) {
//				return "redirect:list.at";
//			} else {
//				throw new BoardException("게시글 작성을 실패하였습니다.");
//			}
//		}
//		

//		@PostMapping("/board/lost/insertLostBoard")
//		public String insertLostBoard(@ModelAttribute LostBoard m, 
//									  @RequestParam("file") ArrayList<MultipartFile> files, 
//									  HttpSession session, 
//									  HttpServletRequest request) throws BoardException {
////			String LostboardWriter = ((Member)session.getAttribute("loginUser")).getMbId();
//			Member loginUser = (Member) session.getAttribute("loginUser");
//			System.out.println(files);
//			if(loginUser != null) {
//				m.setMbId(loginUser.getMbId());
//				m.setMbNickName(loginUser.getMbNickName());
//				
//				
//				
//				int result = bService.insertLostBoard(m);
//				
//				if(result > 0) {
//					return "redirect:/board/lost";
//				} else {
//					throw new BoardException("게시글 작성을 실패하였습니다.");
//				}
//			} else {
//				throw new BoardException("로그인이 필요합니다.");
//			}
//			
//		}	
		
//		----------------------------------------------------------
//		mPhotoPath에 사진 경로 저장한 컨트롤러 //
//		@PostMapping("/board/lost/insertLostBoard")
//		public String insertLostBoard(@ModelAttribute LostBoard m,
//		                              @RequestParam("file") ArrayList<MultipartFile> files,
//		                              HttpSession session,
//		                              HttpServletRequest request) throws BoardException {
//
//		    // 로그인 사용자 정보 가져오기
//		    Member loginUser = (Member) session.getAttribute("loginUser");
//		    if (loginUser == null) {
//		        // 로그인 사용자가 없는 경우 예외 발생
//		        throw new BoardException("로그인이 필요합니다.");
//		    }
//
//		    // 로그인 정보로부터 사용자 ID와 닉네임을 LostBoard 객체에 설정
//		    m.setMbId(loginUser.getMbId());
//		    m.setMbNickName(loginUser.getMbNickName());
//
//		    // 파일 처리 로직
//		    if (files != null && !files.isEmpty()) {
//		        for (MultipartFile file : files) {
//		            if (!file.isEmpty()) {
//		                // 파일 저장 로직 호출
//		                String[] fileInfo = saveFile(file);
//		                String savePath = fileInfo[0];   // 저장 경로
//		                String renameFileName = fileInfo[1]; // 저장된 파일명
//
//		                // LostBoard 객체에 사진 경로 설정
//		                m.setMPhotoPath(savePath + "\\" + renameFileName);
//		            }
//		        }
//		    }
//
//		    // 서비스 계층을 통해 LostBoard 객체를 데이터베이스에 저장
//		    int result = bService.insertLostBoard(m);
//
//		    // 결과에 따른 처리
//		    if (result > 0) {
//		        return "redirect:/board/lost";
//		    } else {
//		        throw new BoardException("게시글 작성에 실패하였습니다.");
//		    }
//		}
//
//		// 파일 저장 로직
//		public String[] saveFile(MultipartFile file) {
//		    String root = "C:\\";
//		    String savePath = root + "\\uploadFiles";
//
//		    // 디렉토리 생성
//		    File folder = new File(savePath);
//		    if (!folder.exists()) {
//		        folder.mkdirs();
//		    }
//
//		    // 파일명 재정의
//		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		    String originalFileName = file.getOriginalFilename();
//		    String renameFileName = sdf.format(new Date()) + (int)(Math.random() * 100000) +
//		                            originalFileName.substring(originalFileName.lastIndexOf("."));
//
//		    // 파일 저장
//		    String renamePath = folder + "\\" + renameFileName;
//		    try {
//		        file.transferTo(new File(renamePath));
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//
//		    return new String[]{savePath, renameFileName};
//		}
//		----------------------------------------------------------

//		Attachment 테이블에 ATTM_REF_TYPE에서 게시판 타입 지정하고 사진 파일은 ATTM_PATH에 저장하는 컨트롤러 //
		@PostMapping("/board/lost/insertLostBoard")
		public String insertLostBoard(@ModelAttribute LostBoard m,
		                              @RequestParam("file") ArrayList<MultipartFile> files,
		                              HttpSession session,
		                              HttpServletRequest request) throws BoardException {

		    Member loginUser = (Member) session.getAttribute("loginUser");
		    if (loginUser == null) {
		        throw new BoardException("로그인이 필요합니다.");
		    }

		    m.setMbId(loginUser.getMbId());
		    m.setMbNickName(loginUser.getMbNickName());

		    // LostBoard 정보를 데이터베이스에 저장하고 생성된 게시글 ID를 가져옵니다.
		    int lostBoardId = bService.insertLostBoard(m);

		    // 파일을 처리하고 Attachment 정보를 데이터베이스에 저장합니다.
		    if (files != null && !files.isEmpty()) {
		        for (MultipartFile file : files) {
		            if (!file.isEmpty()) {
		                // 파일 저장 로직 호출
		                String[] fileInfo = saveFile(file);
		                String savePath = fileInfo[0];   // 저장 경로
		                String renameFileName = fileInfo[1]; // 저장된 파일명

		                // Attachment 객체 생성 및 설정
		                Attachment attachment = new Attachment();
		                attachment.setOriginalName(file.getOriginalFilename());
		                attachment.setRenameName(renameFileName);
		                attachment.setAttmPath(savePath + "\\" + renameFileName); // 파일 저장 경로 설정
		                attachment.setAttmRefType("M"); // 실종게시판 타입
		                attachment.setAttmRefNo(lostBoardId); // 생성된 게시글 ID

		                // Attachment 정보를 데이터베이스에 저장
		                bService.insertAttachment(attachment);
		            }
		        }
		    }

		    // 결과에 따른 리다이렉션
		    if (lostBoardId > 0) {
		        return "redirect:/board/lost";
		    } else {
		        throw new BoardException("게시글 작성에 실패하였습니다.");
		    }
		}

		// 파일 저장 로직
		public String[] saveFile(MultipartFile file) {
		    String root = "C:\\";
		    String savePath = root + "\\uploadFiles";

		    File folder = new File(savePath);
		    if (!folder.exists()) {
		        folder.mkdirs();
		    }

		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		    String originalFileName = file.getOriginalFilename();
		    String renameFileName = sdf.format(new Date()) + (int)(Math.random() * 100000) +
		                            originalFileName.substring(originalFileName.lastIndexOf("."));

		    String renamePath = folder + "\\" + renameFileName;
		    try {
		        file.transferTo(new File(renamePath));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return new String[]{savePath, renameFileName};
		}

		
//		----------------------------------------------------------
		
//		@PostMapping("/board/lost/insertLostBoard")
//		   public String insertLostBoard(@ModelAttribute LostBoard m, 
//				   						 @RequestParam("file") ArrayList<MultipartFile> files,
//				   						 HttpSession session, HttpServletRequest request) throws BoardException {
//		    System.out.println(m);  
//			String lostBoardWriter = ((Member)session.getAttribute("loginUser")).getMbId();
//			System.out.println("mbId : " + m.getMbId());
//			Member loginUser = (Member) session.getAttribute("loginUser");
//			if(loginUser != null) {
//				lostBoardWriter = loginUser.getMbId(); 
//			} else {
//				
//			}
//			
//			if(loginUserObj != null && loginUserObj instanceof Memeber) {
//				String lostBoardWriter = ((Memeber) loginUserObj).getMbId();
//				m.setMbId(lostBoardWriter);
//			} else {
//				return "redirect:/loginPage";
//			}
//			
//			m.setMbId(lostBoardWriter);
//			
//			int result1 = bService.insertLostBoard(m); 
//			System.out.println(result1);
//		      ArrayList<Attachment> aList = new ArrayList<>(); 
//		      for(int i = 0; i < files.size(); i++) {
//		         MultipartFile upload = files.get(i);
//		         if(!upload.getOriginalFilename().equals("")) {
//		            String[] returnArr = saveFile(upload);
//		            if(returnArr[1] != null) {
//		               Attachment a = new Attachment();
//		               a.setOriginalName(upload.getOriginalFilename());
//		               a.setRenameName(returnArr[1]);
//		               a.setAttmPath(returnArr[0]);
//		               a.setAttmLevel(2);
//		               a.setAttmRefType("M");
//		               a.setAttmRefNo(m.getMNo());
//		               
//		               aList.add(a);
//		            }
//		         }
//		      }
//		      
//		    
//		      
//		      int result2 = bService.insertAttm(aList);
//		 
//		      if(mlist.isEmpty()) { 
//		         m.setLostBoardType(1);
//		         result1 = bService.insertLostBoard(m);
//		      } else {
//		         m.setLostBoardType(2);
//		         result1 = bService.insertLostBoard(m);
//		         
//		      
//		      if(result1 + result2 == aList.size()+1) {
//		         if(result2 == 0) {
//		            return "redirect:list.bo";
//		         } else {
//		            return "redirect:list.at";
//		         }
//		      } else {
//		         for(Attachment a : aList) {
//		            deleteFile(a.getRenameName());
//		         }
//		         throw new BoardException("게시판 작성을 실패하였습니다.");
//		      }
//		}
//		      String id = ((Member)request.getSession().getAttribute("loginUser")).getId();
//		      b.setBoardWriter(id);
//		      
//		     
//		
//		public String[] saveFile(MultipartFile upload) {
//		      String root = "C:\\";
//		      String savePath = root + "\\uploadFiles";
//		      
//		      File folder = new File(savePath);
//		      if(!folder.exists()) {
//		         folder.mkdirs();
//		      }
//		      
//		    
//		      Date time = new Date(System.currentTimeMillis());
//		      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		      int ranNum = (int)(Math.random()*100000);
//		      
//		      String originFileName = upload.getOriginalFilename();
//		      String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
//		      
//		   
//		      String renamePath = folder + "\\" + renameFileName;
//		      try {
//		         upload.transferTo(new File(renamePath));
//		      } catch (IllegalStateException e) {
//		         e.printStackTrace();
//		      } catch (IOException e) {
//		         e.printStackTrace();
//		      }
//		      
//		      String[] returnArr = new String[2];
//		      returnArr[0] = savePath;   
//		      returnArr[1] = renameFileName;
//		      
//		      return returnArr;
//		   }
//
//		private String[] saveFile(MultipartFile upload, HttpServletRequest request) {
//			String root = "C:\\";
//			String savePath = root + "\\uploadFiles";
//			
//			File folder = new File(savePath);
//			
//			if(!folder.exists()) {
//				folder.mkdirs();
//			}
//			
//			
//			Date time = new Date(System.currentTimeMillis());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//			int ranNum = (int)(Math.random()*100000);
//			
//			String originFileName = upload.getOriginalFilename();
//			String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
//																			
//			
//		
//			String renamePath = folder + "\\" + renameFileName;
//			try {
//				upload.transferTo(new File(renamePath));
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			String[] returnArr = new String[2];
//			returnArr[0] = savePath; 
//			returnArr[1] = renameFileName; 
//			
//			return returnArr;
//		}
//			
//		
//		public void deleteFile(String fileName) {
//			String root = "C:\\";
//			String savePath = root + "\\uploadFiles";
//			File f = new File(savePath + "\\" + fileName);
//			if(f.exists()) {
//				f.delete();
//			}
//		}
//		----------------------------------------------------------
//=================================================================
//		// 첨부파일 게시글 상세보기 //
//		@GetMapping("/board/lost/detail")
//		public String lostBoardDetail(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam("mNo") int mNo, HttpSession session, Model model) throws BoardException {
//		
//			Member loginUser = (Member)session.getAttribute("loginUser");
//			String id = null;
//			if(loginUser != null) {
//				id = loginUser.getMbId();
//			}
//			LostBoard m = bService.selectLostBoard(mNo);
//			ArrayList<Attachment> mList = bService.selectAttmLostBoardList((Integer)mNo); 
//			
//			if(m != null) {
//				model.addAttribute("m", m);
//				model.addAttribute("page", page);
//				model.addAttribute("mList", mList);
//				System.out.println(m);
//				return "lostBoardDetail";
//			} else {
//				throw new BoardException("게시글 상세보기를 실패하였습니다.");
//			}
//		}
		
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
						System.out.println(m);
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
		
//		//...하는 중...//
////		// 수정한 첨부파일 게시글을 업로드 //
//		@PostMapping("/board/lost/updateLostBoard") // 어노테이션 사용해 연결 요청(바인딩).//(HTTP 요청에서 전달된 데이터가, 메서드의 매개변수에 자동으로 연결되어(바인딩되어), 메서드에서 활용될 수 있도록 함)
//		   public String updateLostBoardAttm(@ModelAttribute LostBoard m,
//		                     @RequestParam("page") int page,
//		                     @RequestParam("deleteLostBoardAttm") String[] deleteLostBoardAttm,
//		                     @RequestParam("file") ArrayList<MultipartFile> files,
//		                     HttpServletRequest request, RedirectAttributes re) {
//		      // m 객체의 lostBoardType 필드에 2라는 값을 설정
//		    // m.setLostBoardType(2); // set다음에 오는 LostBoardType은 해당필드의 이름.(소괄호 안에는 필드에 설정할 값 담는다)
//		      // ㄴ우린 게시판이 다 첨부파일 게시판이니까 게시판 타입 설정해줄 필요 없지않나
//		      // 밑은 ArrayList 클래스를 이용하여 Attachment 타입의 객체를 저장하는 리스트를 생성하는 코드
//		      ArrayList<Attachment> mList = new ArrayList<>(); // mList는 Attachment타입의 객체들을 저장하고 관리하는 동적 배열.
//		      for(int i = 0; i < files.size(); i++) { // i의 초기값은 0; i가 파일사이즈보다 작을때; i는 1씩증가
//		         MultipartFile LostUpload = files.get(i); // 업로드된 파일들(files) 중에서 인덱스 i에 해당하는 파일을 가져와 MultipartFile 타입의 변수 LostUpload에 할당하는 코드. 
//		         // files는 @RequestParam("file") ArrayList<MultipartFile> files에서 전달된 파일들의 리스트.// get(i)는 리스트에서 인덱스 i에 해당하는 요소를 가져오는 메서드.
//		         if(!LostUpload.getOriginalFilename().equals("")) {
//		            String[] returnLostArr = saveLostFile(LostUpload);
//		            if(returnLostArr[1] != null) {
//		               Attachment a = new Attachment(); // a는 Attachment vo를 뜻한다.
//		               a.setOriginalName(LostUpload.getOriginalFilename());
//		               a.setRenameName(returnLostArr[1]);
//		               a.setAttmPath(returnLostArr[0]);
//		               
//		               mList.add(a);
//		            }//ㄴ업로드된 파일에 대한 정보가 Attachment 객체에 담겨 mList에 추가, mList는 업로드된 파일들의 정보를 담고 있는 리스트가 됨.
//		         }
//		      }
//		      
//		      ArrayList<String> delLostRename = new ArrayList<>();
//		      ArrayList<Integer> delLostLevel = new ArrayList<>();
//		      
//		      for(int i = 0; i < deleteLostBoardAttm.length; i++) {
//		         //System.out.println(deleteAttm[i]);
//		         String Lostrename = deleteLostBoardAttm[i];      // 취소바구니에 담긴 스트링 배열
//		         if(!Lostrename.equals("none")) {
//		            String[] split = Lostrename.split("/");
//		            delLostRename.add(split[0]);
//		            delLostLevel.add(Integer.parseInt(split[1]));
//		         }
//		      }// delLostRename 리스트에는 삭제할 첨부 파일의 리네임된 이름이, 
//		       // delLostLevel 리스트에는 해당 첨부 파일의 레벨이 저장.(파일에 레벨을 설정해 놓으면 파일의 중요도나 순서 표시를 할 때 용이.)
//		      
//		      int deleteLostAttmResult = 0;
//		      int updateLostResult = 0;
//		      boolean existBeforeLostAttm = true;
//		      if(!delLostRename.isEmpty()) {
//		    	  deleteLostAttmResult = bService.deleteLostAttm(delLostRename); // DB삭제
//		         if(deleteLostAttmResult > 0) {
//		            for(String rename : delLostRename) {
//		               deleteFile(rename);   // 서버 저장소에서 삭제
//		            }
//		         }
//		         
//		         // delRename : 삭제하고자 하는 명시된 파일의 개수만 담겨있음 
//		         // deleteAttm : 파일삭제를 명시 안해도 none값으로 들어오기 때문에
//		         //            기존파일 총량을 알 수 있음
//		         
//		         if(delLostRename.size() == deleteLostAttm.length) {
//		            //기존 파일 전부 삭제
//		        	 existBeforeLostAttm = false;
//		            if(mList.isEmpty()) {
//		               m.setLostBoardType(1);
//		            }
//		         } else {
//		            // 기존 파일 일부 삭제
//		            // 썸네일레벨을 가지고 있는 파일이 있는지 확인 후 조치
//		            for(int level : delLostLevel) {
//		               if(level == 0) {
//		                  bService.updateAttmLevel(m.getLostBoardId());
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
//		      int updateLostAttmResult = 0;
//		      if(!mList.isEmpty()) {
//		    	  updateLostAttmResult = bService.insertLostAttm(mList);
//		      }
//		      
//		      if(updateLostResult + updateLostAttmResult == mList.size() + 1) {
//		         if(delRename.size() == deleteLostAttm.length && updateLostAttmResult == 0) {
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
//		
//		
//		
//}
//
//		// 파일을 저장 경로 및 rename하여(이름 안겹치도록) 나중에 찾기 쉽게 함.
//		
//		public String[] saveLostFile(MultipartFile lostUpload) {
//		      String root = "C:\\";
//		      String savePath = root + "\\uploadLostFiles";
//		      
//		      File folder = new LostFile(savePath);
//		      if(!folder.exists()) {
//		         folder.mkdirs();
//		      }
//		      
//		      // 2. 저장될 파일 rename
//		      Date time = new Date(System.currentTimeMillis());
//		      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		      int ranNum = (int)(Math.random()*100000);
//		      
//		      String originFileName = upload.getOriginalFilename();
//		      String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
//		      
//		      // 3. rename된 파일을 저장소에 저장
//		      String renamePath = folder + "\\" + renameFileName;
//		      try {
//		         upload.transferTo(new File(renamePath));
//		      } catch (IllegalStateException e) {
//		         e.printStackTrace();
//		      } catch (IOException e) {
//		         e.printStackTrace();
//		      }
//		      
//		      String[] returnArr = new String[2];
//		      returnArr[0] = savePath;   // 경로 있음
//		      returnArr[1] = renameFileName; // renameName이 있음
//		      
//		      return returnArr;
//		   }
//		
//		public void deleteFile(String fileName) {
//			String root = "C:\\";
//			String savePath = root + "\\uploadFiles";
//			
//			File f = new File(savePath + "\\" + fileName);
//			if(f.exists()) { // f가 있으면 
//				f.delete();// 삭제
//			}
//		}
		
		
		
}
    