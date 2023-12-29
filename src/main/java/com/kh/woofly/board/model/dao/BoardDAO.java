package com.kh.woofly.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.LostBoard;

@Mapper
public interface BoardDAO {
	
	//	게시글 수 가져오기? //
	int getListCount(int i);
	
	//  게시글 목록 보기 위해 게시글 목록 선택(아마) //
	ArrayList<LostBoard> selectBoardList(int i, RowBounds rowBounds);
	
	ArrayList<Attachment> selectAttmBoardList(String mbNickName);

}
