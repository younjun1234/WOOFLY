package com.kh.woofly.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;

public interface AdminService {

	HashMap<String, ArrayList<Object>> selectAllBoard();

	int getReportCount(String searchId);

	ArrayList<Report> selectReportList(PageInfo pi, String searchId);

	ArrayList<HashMap<String, Object>> selectReportRank();

	Report selectReportDetail(int rNo);

	ArrayList<Report> selectTargetList(String rAccused);

	int updateStopDate(String rAccused);

	int updateReportSit(int rNo);

	int selectWarningCount(Report r);
	

}
