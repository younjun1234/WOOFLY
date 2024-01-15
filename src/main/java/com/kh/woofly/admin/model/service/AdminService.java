package com.kh.woofly.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.member.model.vo.MemberAddress;

public interface AdminService {

	HashMap<String, ArrayList<Object>> selectAllBoard();

	int getReportCount(String searchId);

	ArrayList<Report> selectReportList(PageInfo pi, String searchId);

	ArrayList<HashMap<String, Object>> selectReportRank();

	Report selectReportDetail(int rNo);

	ArrayList<Report> selectTargetList(String rAccused);

	int updateStopDate(String rAccused);

	int updateReportSit(Report r);

	int selectWarningCount(Report r);

	ArrayList<Member> selectAllMembers(PageInfo pi, String mbId);

	ArrayList<MemberAddress> selectAllAddress();

	int getMembersCount(String mbId);
	

}
