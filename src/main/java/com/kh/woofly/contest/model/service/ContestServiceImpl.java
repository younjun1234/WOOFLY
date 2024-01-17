package com.kh.woofly.contest.model.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.common.PageInfo;
import com.kh.woofly.contest.model.dao.ContestDAO;
import com.kh.woofly.contest.model.vo.Contest;
import com.kh.woofly.contest.model.vo.ContestAttm;
import com.kh.woofly.contest.model.vo.ContestItem;
import com.kh.woofly.contest.model.vo.Participants;

@Service
public class ContestServiceImpl implements ContestService {
	
	@Autowired
	private ContestDAO cDAO;
	
	@Override
	public Contest contestNewList() {
		
		return cDAO.contestNewList();
	}
	
	@Override
	public int contestOpen(Contest c) {
		
		return cDAO.contestOpen(c);
	}

	@Override
	public ArrayList<Contest> contestList() {
		
		return cDAO.contestList();
	}

	@Override
	public int contestUpdate(Contest c) {
		
		return cDAO.contestUpdate(c);
	}

	@Override
	public void updateContestStatus(LocalDate today) {
		
		cDAO.updateContestStatus(today);
	}

	@Override
	public ArrayList<Contest> updateBestContest(LocalDate today) {
		
		return cDAO.updateBestContest(today);
	}

	@Override
	public int contestEnroll(Participants p) {
		
		return cDAO.contestEnroll(p);
	}

	@Override
	public Contest contestId(LocalDate today) {
		
		return cDAO.contestId(today);
	}

	@Override
	public ArrayList<ContestItem> searchItem(String pSearch) {
		
		return cDAO.searchItem(pSearch);
	}

	@Override
	public int getListCount(String id) {

		return cDAO.getListCount(id);
	}

	@Override
	public ArrayList<ContestItem> itemList(String id) {
		
		return cDAO.itemList(id);
	}

	@Override
	public int insertAttm(ArrayList<ContestAttm> list) {
		
		return cDAO.insertAttm(list);
	}

	@Override
	public String memberNick(String id) {
		
		return cDAO.memberNick(id);
	}

	@Override
	public ArrayList<String> petList(String id) {
		
		return cDAO.petList(id);
	}

	@Override
	public Participants thisParticipant(Integer id) {
		// TODO Auto-generated method stub
		return cDAO.thisParticipant(id);
	}

	@Override
	public String petName(Integer pId) {
		// TODO Auto-generated method stub
		return cDAO.petName(pId);
	}



	
	
	
}
