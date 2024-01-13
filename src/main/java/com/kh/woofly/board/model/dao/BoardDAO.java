package com.kh.woofly.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.board.model.vo.Attachment;

import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.DwBoard;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.common.Reply;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.WmBoard;

@Mapper
public interface BoardDAO {

//	자유게시판 "/board/free"
	
	ArrayList<Board> searchFreeBoards(HashMap<String, String> map);
	
	int getListCount(int i);

	ArrayList<Board> selectFreeBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmFreeBoardList(Integer bId);

	Board selectFreeBoard(int bNo);
	
	int updateFreeCount(int bNo);
	
	int insertFreeBoard(Board b);

	int insertFreeAttm(ArrayList<Attachment> attachments);
	
	int updateFreeBoard(Board b);

	int deleteFreeBoard(int bNo);
	
	int deleteFreeAttm(ArrayList<String> delRename);

	void updateAttmLevel(int bNo);
	
	int statusNAttm(int bNo);

	int insertFreeReply(Reply r);
	
	int deleteFreeReply(Reply r);

	ArrayList<Reply> selectFreeReply(int bNo);
	
	//======// 도그워커  //===============================	
	
	int getDwListCount(int i);

	ArrayList<DwBoard> selectDwBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmDwBoardList(Object object);
	
	//후기
	
	int getDwRvListCount(int i);

	ArrayList<DwBoard> selectDwRvBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmDwRvBoardList(Object object);
	
	
	DwBoard selectDwBoard(int dwNo);

	ArrayList<Reply> selectDwReply(int dwNo);
	
	int updateDwCount(int dwNo);

	int insertDwBoard(DwBoard dw);
	
	int insertDwAttm(ArrayList<Attachment> attachments);
	
	int updateDwBoard(DwBoard dw);
	
	int deleteDwBoard(int dwNo);
	
	int insertDwReply(Reply r);

	int deleteDwReply(Reply r);	
	
	int BoardReport(Report newRep);
	
	
//======// 산책메이트  //===============================
	
	int getWmListCount(int i);

	ArrayList<WmBoard> selectWmBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmWmBoardList(Object object);

	int getWmRvListCount(int i);

	ArrayList<WmBoard> selectWmRvBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmWmRvBoardList(Object object);

	int insertWmBoard(WmBoard wm);

	int insertWmAttm(ArrayList<Attachment> attachments);
	
	WmBoard selectWmBoard(@Param("wmNo") int wmNo, @Param("id") String id);

	ArrayList<Reply> selectWmReply(int wmNo);
	
	
	
	
	
	
	
	
	


	// 실종신고
	ArrayList<LostBoard> selectLostBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmLostBoardList(Integer bId);

	LostBoard selectLostBoard(int mNo);

	int mListCount(int i);

	int insertLostBoard(LostBoard m);

	LostBoard selectLostBoard(int bId, Object object);

	

	





	

	

	

	

	

	

	

	

	

	

	

	
	




	

	

	

	

	

	


	

	
	
	
}
