package com.kh.woofly.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.DwBoard;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.UsedBoard;
import com.kh.woofly.board.model.vo.WmBoard;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.common.Reply;

public interface BoardService {
	
	int getReplyListCount(int i, int dwNo, String bType);

//	자유게시판 "/board/free"	
	
	ArrayList<Board> searchFreeBoards(HashMap<String, String> map);
		
	int getListCount(int i);

	ArrayList<Board> selectFreeBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmFreeBoardList(Integer bId);

	Board selectFreeBoard(int bNo, String id);

	int insertFreeBoard(Board b);

	int insertFreeAttm(ArrayList<Attachment> attachments);
	
	int updateFreeBoard(Board b);

	int deleteFreeBoard(int bNo);
	
	int deleteFreeAttm(ArrayList<String> delRename);
	
	void updateAttmLevel(int bNo);
	
	int statusNAttm(int bNo);

	int insertFreeReply(Reply r);
	
	int deleteFreeReply(Reply r);
	
	int updateFreeReply(Reply r);

	ArrayList<Reply> selectFreeReply(int bNo);
	
//======// 도그워커  //===============================
	
	ArrayList<DwBoard> searchDwBoards(HashMap<String, String> map);

	int getDwListCount(int i);
	
	ArrayList<DwBoard> selectDwBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmDwBoardList(Object object);
	
	//후기
	ArrayList<DwBoard> searchDwRvBoards(HashMap<String, String> map);
	
	int getDwRvListCount(int i);

	ArrayList<DwBoard> selectDwRvBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmDwRvBoardList(Object object);
	
	
	DwBoard selectDwBoard(int dwNo, String id);

	ArrayList<Reply> selectDwReply(PageInfo pi, int dwNo);
	
	int insertDwBoard(DwBoard dw);
	
	int insertDwAttm(ArrayList<Attachment> attachments);
	
	int updateDwBoard(DwBoard dw);
	
	int deleteDwBoard(int dwNo);
	
	int deleteDwAttm(ArrayList<String> delRename);
	
	int insertDwReply(Reply r);

	int deleteDwReply(Reply r);
	
	int updateDwReply(Reply r);
	
	int selectBoardReport(Report rep);

	int BoardReport(Report rep);
	
	int checkReplyResult(Report rep);

	int insertReplyReport(Report rep);
	
	
	
//======// 워킹메이트  //===============================
	
	ArrayList<WmBoard> searchWmBoards(HashMap<String, String> map);

	ArrayList<WmBoard> searchWmRvBoards(HashMap<String, String> map);
	
	int getWmListCount(int i);

	ArrayList<WmBoard> selectWmBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmWmBoardList(Object object);
	
	int getWmRvListCount(int i);

	ArrayList<WmBoard> selectWmRvBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmWmRvBoardList(Object object);
	
	int insertWmBoard(WmBoard wm);

	int insertWmAttm(ArrayList<Attachment> attachments);
	
	WmBoard selectWmBoard(int wmNo, String id);
	
	int deleteWmAttm(ArrayList<String> delRename);

	int updateWmBoard(WmBoard wm);
	
	int deleteWmBoard(int wmNo);

	ArrayList<Reply> selectWmReply(int wmNo);
	
	int inserWmReply(Reply r);

	int deletWmReply(Reply r);
	
	
	




	
	//연준이꺼

	ArrayList<UsedBoard> selectMyUsedBuying(PageInfo pi, HashMap<String, Object> map);

	int selectMyUsedBuyingCount(String id);

	int selectMySellingCount(String id);

	ArrayList<UsedBoard> selectMySelling(PageInfo pi, HashMap<String, Object> map);

	

	
	/* 실종신고 게시판 "/board/lost" */
	int getMlistCount(int i);

	ArrayList<LostBoard> selectLostBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmLostBoardList(Integer bId); 
	
//	첨부파일 게시글 작성 //
//	"/board/lost/detail"
	// 글쓰기
	

//	첨부파일 게시글 작성(등록) //
//	/board/lost/insertLostBoard

	
	// 실종 검색 //

	int insertLostBoard(LostBoard m);

	int insertLostAttm(ArrayList<Attachment> attachments);

	ArrayList<LostBoard> searchLostBoards(HashMap<String, String> map);

	LostBoard selectLostBoard(Integer mNo, String mbId);

	LostBoard editLostBoard(int bId, Object object);

	int deleteLostBoard(int bId);

	int deleteLostBoardAttm(int bId);

	int editLostBoard(LostBoard lb);

//	Reply selectReplyLostBoard(Integer rNo);

//	수정된 첨부파일 게시글 업로드 //
//	int deleteLostAttm(ArrayList<String> delRename);
//	ArrayList<Member> selectMemberList(PageInfo pi, int i);
	
	
	// 중고게시판 //
	int getUlistCount(int i);

	ArrayList<UsedBoard> selectUsedBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmUsedBoardList(Object object);

	ArrayList<UsedBoard> searchUsedBoards(HashMap<String, String> map);

	UsedBoard selectUsedBoard(Integer uNo);

	Reply selectReply(Integer rNo);

	int insertUsedBoard(UsedBoard u);

	int insertUsedAttm(ArrayList<Attachment> attachments);


	

	

	

	

	

	

	

	

	

	

	

	

	

	

	


	

	



	

	

	

	

	

	

	



	

	



	

	

	


	


	
	
	
	


}
