package com.kh.woofly.contest.model.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.contest.model.vo.Contest;
import com.kh.woofly.contest.model.vo.ContestAttm;
import com.kh.woofly.contest.model.vo.ContestItem;
import com.kh.woofly.contest.model.vo.Participants;

@Mapper
public interface ContestDAO {
	
	int contestOpen(Contest c);
	
	Contest contestNewList();
	
	ArrayList<Contest> contestList();

	int contestUpdate(Contest c);

	void updateContestStatus(LocalDate today);

	ArrayList<Contest> updateBestContest(LocalDate today);

	int contestEnroll(Participants p);

	Contest contestId(LocalDate today);

	ArrayList<ContestItem> itemList(String id);

	ArrayList<ContestItem> searchItem(String pSearch);

	int getListCount(String id);

	ArrayList<ContestItem> itemList(String id, RowBounds rowBounds);

	int insertAttm(ArrayList<ContestAttm> list);




	
	
}
