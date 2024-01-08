package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.LostBoard;

public interface BoardService {

//	자유게시판 "/board/free"
	int getListCount(int i);

	ArrayList<Board> selectBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmBoardList(Integer bId);

//	/board/free/detail
	Board selectBoard(int bNo);

//	/board/free/insertFreeBoard
	int insertBoard(Board b);

// 실종신고 게시판 "/board/lost"
	int getMlistCount(int i);

	ArrayList<LostBoard> selectLostBoardList(PageInfo pi, int i);

	ArrayList<Attachment> selectAttmLostBoardList(Integer bId); 
	
//	첨부파일 게시글 작성 //
//	"/board/lost/detail"
	// 글쓰기
	LostBoard selectLostBoard(int mNo);

//	첨부파일 게시글 작성(등록) //
//	/board/lost/insertLostBoard
	int insertLostBoard(LostBoard m);

	LostBoard selectLostBoard(int bId, Object object);

	int insertAttm(ArrayList<Attachment> aList);


//	수정된 첨부파일 게시글 업로드 //
//	int deleteLostAttm(ArrayList<String> delRename);
//	ArrayList<Member> selectMemberList(PageInfo pi, int i);
	


	
	
	
	


}
