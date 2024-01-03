package com.kh.woofly.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.Reply;
import com.kh.woofly.board.model.vo.Board;

@Mapper
public interface BoardDAO {

	int getListCount(int i);

	ArrayList<Board> selectFreeBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmFreeBoardList(Integer bId);

	Board selectFreeBoard(int bNo);

	int insertFreeBoard(Board b);

	int insertFreeAttm(ArrayList<Attachment> attachments);

	int deleteFreeBoard(int bNo);

	int statusNAttm(int bNo);

	int insertFreeReply(Reply r);

	ArrayList<Reply> selectFreeReply(int bNo);





}
