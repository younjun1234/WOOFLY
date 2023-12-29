package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.PageInfo;

public interface BoardService {

	int getListCount(int i);

	ArrayList<Board> selectBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmBoardList(Integer bId);

	Board selectBoard(int bNo);

	int insertBoard(Board b);


}
