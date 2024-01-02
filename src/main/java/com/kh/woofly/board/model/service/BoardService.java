package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.board.model.vo.Board;

public interface BoardService {

//	자유게시판 "/board/free"
	int getListCount(int i);

	ArrayList<Board> selectBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmBoardList(Integer bId);

//	/board/free/detail
	Board selectBoard(int bNo);

//	/board/free/insertFreeBoard
	int insertBoard(Board b);

	
	


}
