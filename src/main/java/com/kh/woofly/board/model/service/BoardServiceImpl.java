package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.board.model.dao.BoardDAO;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO bDAO;

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

	@Override
	public Board selectBoard(int bNo) {
		return bDAO.selectBoard(bNo);
	}

	@Override
	public int insertBoard(Board b) {
		return bDAO.insertBoard(b);
	}

}
