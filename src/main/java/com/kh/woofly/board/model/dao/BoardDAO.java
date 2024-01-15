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
import com.kh.woofly.board.model.vo.UsedBoard;
import com.kh.woofly.common.Reply;
import com.kh.woofly.board.model.vo.WmBoard;

@Mapper
public interface BoardDAO {
	
	int getReplyListCount(int i, int dwNo, String bType);

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
	
	int updateFreeReply(Reply r);

	ArrayList<Reply> selectFreeReply(int bNo);
	
	//======// 도그워커  //===============================	
	
	ArrayList<DwBoard> searchDwBoards(HashMap<String, String> map);
	
	int getDwListCount(int i);

	ArrayList<DwBoard> selectDwBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmDwBoardList(Object object);
	
	//후기
	
	ArrayList<DwBoard> searchDwRvBoards(HashMap<String, String> map);
	
	int getDwRvListCount(int i);

	ArrayList<DwBoard> selectDwRvBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmDwRvBoardList(Object object);
	
	
	DwBoard selectDwBoard(int dwNo);
	
	ArrayList<Reply> selectDwReply(int dwNo, RowBounds rowBounds);
	
	int updateDwCount(int dwNo);

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

	
	
//======// 산책메이트  //===============================
	
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
	
	WmBoard selectWmBoard(@Param("wmNo") int wmNo, @Param("id") String id);
	
	int deleteWmAttm(ArrayList<String> delRename);

	int updateWmBoard(WmBoard wm);
	
	int deleteWmBoard(int wmNo);
	
	int inserWmReply(Reply r);

	int deletWmReply(Reply r);

	ArrayList<Reply> selectWmReply(int wmNo);
	
	
	
	
	
	
	
	
	


	// 실종신고
	ArrayList<LostBoard> selectLostBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmLostBoardList(Integer bId);

	LostBoard selectLostBoard(int mNo);

	int mListCount(int i);

	int insertLostBoard(LostBoard m);

	LostBoard selectLostBoard(int bId, Object object);
	
	// 연준이꺼
	
	ArrayList<UsedBoard> selectMyUsedBuying(RowBounds rowbounds, HashMap<String, Object> map);

	int selectMyUsedBuyingCount(String id);

	int selectMySellingCount(String id);

	ArrayList<UsedBoard> selectMySelling(RowBounds rowbounds, HashMap<String, Object> map);

	

	

	



	
	

	



	

	

	

	
	

	

	





	

	

	

	

	

	

	

	

	

	

	

	
	




	

	

	

	

	

	


	

	
	
	
}
