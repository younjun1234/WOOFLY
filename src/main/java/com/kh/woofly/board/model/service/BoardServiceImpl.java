package com.kh.woofly.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.board.model.dao.BoardDAO;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.board.model.vo.Board;
import com.kh.woofly.board.model.vo.DwBoard;
import com.kh.woofly.board.model.vo.LostBoard;
import com.kh.woofly.board.model.vo.UsedBoard;
import com.kh.woofly.board.model.vo.WmBoard;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.common.Reply;

@Service
public class BoardServiceImpl implements BoardService{

   @Autowired
   private BoardDAO bDAO;
   
   @Override
	public int getReplyListCount(int dwNo) {
	   return bDAO.getReplyListCount(dwNo);
	}

   
 //	자유게시판 "/board/free"
   
    @Override
	public ArrayList<Board> searchFreeBoards(HashMap<String, String> map) {
    	return bDAO.searchFreeBoards(map);
	}
   
	@Override
	public int getListCount(int i) {
		return bDAO.getListCount(i);
	}
	
	@Override
	public ArrayList<Board> selectFreeBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectFreeBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmFreeBoardList(Integer bId) {
		return bDAO.selectAttmFreeBoardList(bId);
	}

	@Override
	public Board selectFreeBoard(int bNo, String id) {
		Board b = bDAO.selectFreeBoard(bNo);
		if(b != null) {
			if(id !=null && !b.getMbId().equals(id)) {
				int result = bDAO.updateFreeCount(bNo);
				if(result >0) {
					b.setBCount(b.getBCount() + 1);
				}
			}
		}
		return b;
	}

	@Override
	public int insertFreeBoard(Board b) {
		return bDAO.insertFreeBoard(b);
	}

	@Override
	public int insertFreeAttm(ArrayList<Attachment> attachments) {
		return bDAO.insertFreeAttm(attachments);
	}
	
	@Override
	public int updateFreeBoard(Board b) {
		return bDAO.updateFreeBoard(b);
	}

	@Override
	public int deleteFreeBoard(int bNo) {
		return bDAO.deleteFreeBoard(bNo);
	}
	
	@Override
	public int deleteFreeAttm(ArrayList<String> delRename) {
		return bDAO.deleteFreeAttm(delRename);
	}

	@Override
	public void updateAttmLevel(int bNo) {
		bDAO.updateAttmLevel(bNo);
		
	}
	
	@Override
	public int statusNAttm(int bNo) {
		return bDAO.statusNAttm(bNo);
	}

	@Override
	public int insertFreeReply(Reply r) {
		return bDAO.insertFreeReply(r);
	}
	
	@Override
	public int deleteFreeReply(Reply r) {
		return bDAO.deleteFreeReply(r);
	}
	
	@Override
	public int updateFreeReply(Reply r) {
		return bDAO.updateFreeReply(r);
	}

	@Override
	public ArrayList<Reply> selectFreeReply(int bNo) {
		return bDAO.selectFreeReply(bNo);
	}
	
	

	

	
//======// 도그워커  //===============================
	
	@Override
	public ArrayList<DwBoard> searchDwBoards(HashMap<String, String> map) {
		return bDAO.searchDwBoards(map);
	}
	
	@Override
	public int getDwListCount(int i) {
		return bDAO.getDwListCount(i);
	}
	
	@Override
	public ArrayList<DwBoard> selectDwBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectDwBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmDwBoardList(Object object) {
		return bDAO.selectAttmDwBoardList(object);
	}
	
	//후기
	
	@Override
	public ArrayList<DwBoard> searchDwRvBoards(HashMap<String, String> map) {
		return bDAO.searchDwRvBoards(map);
	}
	
	@Override
	public int getDwRvListCount(int i) {
		return bDAO.getDwRvListCount(i);
	}

	@Override
	public ArrayList<DwBoard> selectDwRvBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectDwRvBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmDwRvBoardList(Object object) {
		return bDAO.selectAttmDwRvBoardList(object);
	}
	
	@Override
	public DwBoard selectDwBoard(int dwNo, String id) {
		DwBoard dw = bDAO.selectDwBoard(dwNo);
		if(dw != null) {
			if(id !=null && !dw.getMbId().equals(id)) {
				int result = bDAO.updateDwCount(dwNo);
				if(result >0) {
					dw.setDwCount(dw.getDwCount() + 1);
				}
			}
		}
		return dw;
	}

	@Override
	public ArrayList<Reply> selectDwReply(PageInfo pi, int dwNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectDwReply(dwNo, rowBounds);
	}
	
	@Override
	public int insertDwBoard(DwBoard dw) {
		return bDAO.insertDwBoard(dw);
	}

	@Override
	public int insertDwAttm(ArrayList<Attachment> attachments) {
		return bDAO.insertDwAttm(attachments);
	}
	
	@Override
	public int updateDwBoard(DwBoard dw) {
		return bDAO.updateDwBoard(dw);
	}
	
	@Override
	public int deleteDwBoard(int dwNo) {
		return bDAO.deleteDwBoard(dwNo);
	}
	
	@Override
	public int deleteDwAttm(ArrayList<String> delRename) {
		return bDAO.deleteDwAttm(delRename);
	}
	
	@Override
	public int insertDwReply(Reply r) {
		return bDAO.insertDwReply(r);
	}

	@Override
	public int deleteDwReply(Reply r) {
		return bDAO.deleteDwReply(r);
	}
	
	@Override
	public int updateDwReply(Reply r) {
		return bDAO.updateDwReply(r);
	}
	
	@Override
	public int selectBoardReport(Report rep) {
		return bDAO.selectBoardReport(rep);
	}
	
	@Override
	public int BoardReport(Report rep) {
		return bDAO.BoardReport(rep);
	}
	
	@Override
	public int checkReplyResult(Report rep) {
		return bDAO.checkReplyResult(rep);
	}

	@Override
	public int insertReplyReport(Report rep) {
		return bDAO.insertReplyReport(rep);
	}
	

//======// 산책메이트  //===============================
	
	@Override
	public ArrayList<WmBoard> searchWmBoards(HashMap<String, String> map) {
		return bDAO.searchWmBoards(map);
	}

	@Override
	public ArrayList<WmBoard> searchWmRvBoards(HashMap<String, String> map) {
		return bDAO.searchWmRvBoards(map);
	}

	@Override
	public int getWmListCount(int i) {
		return bDAO.getWmListCount(i);
	}

	@Override
	public ArrayList<WmBoard> selectWmBoardList(PageInfo pi, int i) {
		return bDAO.selectWmBoardList(pi, i);
	}

	@Override
	public ArrayList<Attachment> selectAttmWmBoardList(Object object) {
		return bDAO.selectAttmWmBoardList(object);
	}	
	
	@Override
	public int getWmRvListCount(int i) {
		return bDAO.getWmRvListCount(i);
	}

	@Override
	public ArrayList<WmBoard> selectWmRvBoardList(PageInfo pi, int i) {
		return bDAO.selectWmRvBoardList(pi, i);
	}

	@Override
	public ArrayList<Attachment> selectAttmWmRvBoardList(Object object) {
		return bDAO.selectAttmWmRvBoardList(object);
	}
	
	@Override
	public int insertWmBoard(WmBoard wm) {
		return bDAO.insertWmBoard(wm);
	}

	@Override
	public int insertWmAttm(ArrayList<Attachment> attachments) {
		return bDAO.insertWmAttm(attachments);
	}
	
	@Override
	public WmBoard selectWmBoard(@Param("wmNo") int wmNo, @Param("id") String id) {
		return bDAO.selectWmBoard(wmNo, id);
	}
	
	@Override
	public int deleteWmAttm(ArrayList<String> delRename) {
		return bDAO.deleteWmAttm(delRename);
	}

	@Override
	public int updateWmBoard(WmBoard wm) {
		return bDAO.updateWmBoard(wm);
	}	

	@Override
	public int deleteWmBoard(int wmNo) {
		return bDAO.deleteWmBoard(wmNo);
	}	
	
	@Override
	public int inserWmReply(Reply r) {
		return bDAO.inserWmReply(r);
	}

	@Override
	public int deletWmReply(Reply r) {
		return bDAO.deletWmReply(r);
	}

	@Override
	public ArrayList<Reply> selectWmReply(int wmNo) {
		return bDAO.selectWmReply(wmNo);
	}

	
	
	
	
	
	
	

	
// 실종신고 게시판
	@Override
	public int getMlistCount(int i) {
		return bDAO.mListCount(i);
	}
	
	@Override
	public ArrayList<LostBoard> selectLostBoardList(PageInfo pi, int i) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return bDAO.selectLostBoardList(i, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectAttmLostBoardList(Integer bId) {
		return bDAO.selectAttmLostBoardList(bId);
	}

	@Override
	public LostBoard selectLostBoard(int mNo) {
		return bDAO.selectLostBoard(mNo);
	}

	@Override
	public int insertLostBoard(LostBoard m) {
		return bDAO.insertLostBoard(m);
	}


	@Override
	public LostBoard selectLostBoard(int bId, Object object) {
		return bDAO.selectLostBoard(bId, object);
	}

	@Override
	public ArrayList<UsedBoard> selectMyUsedBuying(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() - 1) * pi.getPageLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getPageLimit());
		return bDAO.selectMyUsedBuying(rowbounds, map);
	}

	@Override
	public int selectMyUsedBuyingCount(String id) {
		return bDAO.selectMyUsedBuyingCount(id);
	}

	@Override
	public int selectMySellingCount(String id) {
		return bDAO.selectMySellingCount(id);
	}

	@Override
	public ArrayList<UsedBoard> selectMySelling(PageInfo pi, HashMap<String, Object> map) {
		int offset = (pi.getCurrentPage() - 1) * pi.getPageLimit();
		RowBounds rowbounds = new RowBounds(offset, pi.getPageLimit());
		return bDAO.selectMySelling(rowbounds, map);
	}

	
	



	

	
	

	



	

	
	

	

	
	

	


	

	

	
	

	
	
	

	

	

	

	

	

	

	

	

	

	
	
	
	


}
