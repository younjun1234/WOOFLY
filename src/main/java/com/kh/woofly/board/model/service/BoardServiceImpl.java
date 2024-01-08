package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.board.model.dao.BoardDAO;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.Reply;
import com.kh.woofly.common.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{

   @Autowired
   private BoardDAO bDAO;
   
 //	자유게시판 "/board/free"
	@Override
	public int getListCount(int i) {
		return bDAO.getListCount(i);
	}
	
	@Override
	public ArrayList<Board> selectFreeBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectFreeBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmFreeBoardList(Integer bId) {
		return bDAO.selectAttmFreeBoardList(bId);
	}

	@Override
	public Board selectFreeBoard(int bNo, String id) {
		Board b = bDAO.selectFreeBoard(bNo);
		if(b != null) {
			if(id !=null && !b.getMbId().equals(id)) {
				int result = bDAO.updateFreeCount(bNo);
				if(result >0) {
					b.setBCount(b.getBCount() + 1);
				}
			}
		}
		return b;
	}

	@Override
	public int insertFreeBoard(Board b) {
		return bDAO.insertFreeBoard(b);
	}

	@Override
	public int insertFreeAttm(ArrayList<Attachment> attachments) {
		return bDAO.insertFreeAttm(attachments);
	}

	@Override
	public int deleteFreeBoard(int bNo) {
		return bDAO.deleteFreeBoard(bNo);
	}
	
	@Override
	public int statusNAttm(int bNo) {
		return bDAO.statusNAttm(bNo);
	}

	@Override
	public int insertFreeReply(Reply r) {
		return bDAO.insertFreeReply(r);
	}
	
	@Override
	public int deleteFreeReply(Reply r) {
		return bDAO.deleteFreeReply(r);
	}


	@Override
	public ArrayList<Reply> selectFreeReply(int bNo) {
		return bDAO.selectFreeReply(bNo);
	}

	
// 실종신고 게시판
	@Override
	public int getMlistCount(int i) {
		return bDAO.mListCount(i);
	}
	
	@Override
	public ArrayList<LostBoard> selectLostBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectLostBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmLostBoardList(Integer bId) {
		return bDAO.selectAttmLostBoardList(bId);
	}

	@Override
	public LostBoard selectLostBoard(int mNo) {
		return bDAO.selectLostBoard(mNo);
	}

	@Override
	public int insertLostBoard(LostBoard m) {
		return bDAO.insertLostBoard(m);
	}


	@Override
	public LostBoard selectLostBoard(int bId, Object object) {
		return bDAO.selectLostBoard(bId, object);
	}

	

	
	
	
	


}
