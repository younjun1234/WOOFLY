package com.kh.woofly.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.board.model.vo.Attachment;

import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.member.model.vo.Member;

@Mapper
public interface BoardDAO {

//	자유게시판 "/board/free"
	int getListCount(int i);

	ArrayList<Board> selectBoardList(int i, RowBounds rowBounds);

	ArrayList<Attachment> selectAttmBoardList(Integer bId);

//	/board/free/detail
	Board selectBoard(int bNo);

//	/board/free/insertFreeBoard
	int insertBoard(Board b);
	
	// 실종신고
	ArrayList<LostBoard> selectLostBoardList(RowBounds rowBounds, int i);

	ArrayList<Attachment> selectAttmLostBoardList(Integer bId);

	LostBoard selectLostBoard(int mNo);

	int mListCount(int i);

	

	LostBoard selectLostBoard(int bId, Object object);

	
	
	// 실종 검색 //

	int insertLostBoard(LostBoard m);

	int insertLostAttm(ArrayList<Attachment> attachments);

	ArrayList<LostBoard> searchLostBoards(HashMap<String, String> map);


	


//	ArrayList<Member> selectMemberList(PageInfo pi, int i);

	
	
	
}
