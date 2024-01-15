package com.kh.woofly.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.board.model.dao.BoardDAO;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.board.model.vo.Reply;
import com.kh.woofly.board.model.vo.UsedBoard;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.LostBoard;

@Service
public class BoardServiceImpl implements BoardService{

   @Autowired
   private BoardDAO bDAO;
private BoardServiceImpl boardDAO;
   
   
 //	자유게시판 "/board/free"
	@Override
	public int getListCount(int i) {
		return bDAO.getListCount(i);
	}
	
	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmBoardList(Integer bId) {
		return bDAO.selectAttmBoardList(bId);
	}

//	/board/free/detail
	@Override
	public Board selectBoard(int bNo) {
		return bDAO.selectBoard(bNo);
	}

//	/board/free/insertFreeBoard
	@Override
	public int insertBoard(Board b) {
		return bDAO.insertBoard(b);
	}

// 실종신고 게시판 "/board/lost" //
	@Override
	public int getMlistCount(int i) {
		return bDAO.mListCount(i);
	}
	
	@Override
	public ArrayList<LostBoard> selectLostBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getPageLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getPageLimit());
		return bDAO.selectLostBoardList(rowBounds, i);
	}

	@Override
	public ArrayList<Attachment> selectAttmLostBoardList(Integer bId) {
		return bDAO.selectAttmLostBoardList(bId);
	}

	
	

	// 실종 검색 //
	

	@Override
	public int insertLostBoard(LostBoard m) {
		return bDAO.insertLostBoard(m);
	}

	@Override
	public int insertLostAttm(ArrayList<Attachment> attachments) {
		return bDAO.insertLostAttm(attachments);
	}

	@Override
	public ArrayList<LostBoard> searchLostBoards(HashMap<String, String> map) {
		return bDAO.searchLostBoards(map);
	}

	@Override
	public LostBoard selectLostBoard(Integer mNo) {
		return bDAO.selectLostBoard(mNo);
	}

	@Override
	public LostBoard editLostBoard(int bId, Object object) {
		return bDAO.editLostBoard(bId, object);
	}

	@Override
	public int deleteLostBoard(int bId) {
		return bDAO.deleteLostBoard(bId);
	}

	@Override
	public int deleteLostBoardAttm(int bId) {
		return bDAO.deleteLostBoardAttm(bId);
	}

	@Override
	public int editLostBoard(LostBoard lb) {
		return bDAO.editLostBoard(lb);
	}

//	@Override
//	public Reply selectReplyLostBoard(Integer rNo) {
//		// TODO Auto-generated method stub
//		return bDAO.selectReplyLostBoard(rNo);
//	}
	
// 중고게시판 "/board/used"//
	@Override
	public int getUlistCount(int i) {
		// TODO Auto-generated method stub
		return bDAO.uListCount(i);
	}

	@Override
	public ArrayList<UsedBoard> selectUsedBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getPageLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getPageLimit());
		return bDAO.selectUsedBoardList(rowBounds, i);
	}

	@Override
	public ArrayList<Attachment> selectAttmUsedBoardList(Object object) {
		return bDAO.selectAttmUsedBoardList(object);
	}

	@Override
	public ArrayList<UsedBoard> searchUsedBoards(HashMap<String, String> map) {
		return bDAO.searchUsedBoards(map);
	}

	@Override
	public UsedBoard selectUsedBoard(Integer uNo) {
		return bDAO.selectUsedBoard(uNo);
	}




	
	
	
	


}
