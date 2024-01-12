package com.kh.woofly.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.admin.model.dao.AdminDAO;
import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO aDAO;

	@Override
	public HashMap<String, ArrayList<Object>> selectAllBoard() {
		return aDAO.selectAllBoard();
	}

	@Override
	public int getReportCount() {
		return aDAO.getReportCount();
	}

	@Override
	public ArrayList<Report> selectReportList(PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return aDAO.selectReportList(rowBounds, pi);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectReportRank() {
		return aDAO.selectReportRank();
	}

}
