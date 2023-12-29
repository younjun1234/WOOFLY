package com.kh.woofly.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.board.model.dao.BoardDAO;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{

	   @Autowired
	   private BoardDAO bDAO;

	   //	게시글 수 가져오기? //
	   @Override 
	   public int getListCount(int i) {
		   return bDAO.getListCount(i);
	   }
	   
	// 게시글 목록 보기 위해 게시글 목록 선택(아마) //
	   @Override
	   public ArrayList<LostBoard> selectBoardList(PageInfo pi, int i) {
	      int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
	      RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
	      return bDAO.selectBoardList(i, rowBounds);  //pi는 위에서 사용하고  rowBounds가 넘어감
	   }
	   @Override
		public ArrayList<Attachment> selectAttmBoardList(String mbNickName) {
			return bDAO.selectAttmBoardList(mbNickName);
		}


}
