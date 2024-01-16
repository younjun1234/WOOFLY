package com.kh.woofly.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.admin.model.dao.AdminDAO;
import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.member.model.vo.MemberAddress;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO aDAO;

	@Override
	public HashMap<String, ArrayList<Object>> selectAllBoard() {
		return aDAO.selectAllBoard();
	}

	@Override
	public int getReportCount(String searchId) {
		return aDAO.getReportCount(searchId);
	}

	@Override			
	public ArrayList<Report> selectReportList(PageInfo pi, String searchId) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return aDAO.selectReportList(rowBounds, searchId);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectReportRank() {
		return aDAO.selectReportRank();
	}

	@Override
	public Report selectReportDetail(int rNo) {
		return aDAO.selectReportDetail(rNo);
	}

	@Override
	public ArrayList<Report> selectTargetList(String rAccused) {
		return aDAO.selectTargetList(rAccused);
	}

	@Override
	public int updateStopDate(String rAccused) {
		return aDAO.updateStopDate(rAccused);
	}

	@Override
	public int updateReportSit(Report r) {
		return aDAO.updateReportSit(r);
	}

	@Override
	public int selectWarningCount(Report r) {
		return aDAO.selectWarningCount(r);
	}

	@Override
	public ArrayList<Member> selectAllMembers(PageInfo pi, String mbId) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return aDAO.selectAllMembers(rowBounds, mbId);
	}

	@Override
	public ArrayList<MemberAddress> selectAllAddress() {
		return aDAO.selectAllAddress();
	}

	@Override
	public int getMembersCount(String mbId) {
		return aDAO.getMembersCount(mbId);
	}

}
