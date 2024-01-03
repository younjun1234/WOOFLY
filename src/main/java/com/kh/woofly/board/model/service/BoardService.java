package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.board.model.vo.Reply;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.LostBoard;

public interface BoardService {

//	자유게시판 "/board/free"
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

//	수정된 첨부파일 게시글 업로드 //
//	int deleteLostAttm(ArrayList<String> delRename);
	


	
	
	
	


}
