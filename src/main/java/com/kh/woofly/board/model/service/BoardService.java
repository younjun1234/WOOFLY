package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.PageInfo;

public interface BoardService {

	int getListCount(int i);

	ArrayList<LostBoard> selectBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmBoardList(String mbNickName);


}
