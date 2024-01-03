package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.board.model.vo.Reply;

public interface BoardService {

	int getListCount(int i);

	ArrayList<Board> selectFreeBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmFreeBoardList(Integer bId);

	Board selectFreeBoard(int bNo);

	int insertFreeBoard(Board b);

	int insertFreeAttm(ArrayList<Attachment> attachments);

	int deleteFreeBoard(int bNo);

	int statusNAttm(int bNo);

	int insertFreeReply(Reply r);

	ArrayList<Reply> selectFreeReply(int bNo);




}
