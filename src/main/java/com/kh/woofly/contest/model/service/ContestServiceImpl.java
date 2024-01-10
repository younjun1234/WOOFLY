package com.kh.woofly.contest.model.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.contest.model.dao.ContestDAO;
import com.kh.woofly.contest.model.vo.Contest;
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
	public int contestEnroll(Participants p, int cNo) {
		
		return cDAO.contestEnroll(p, cNo);
	}

	@Override
	public Contest contestId(LocalDate today) {
		
		return cDAO.contestId(today);
	}

	@Override
	public ArrayList<ContestItem> itemList(String id) {
		
		return cDAO.itemList(id);
	}

	

	
	
	
	
	
	
	
	
}
