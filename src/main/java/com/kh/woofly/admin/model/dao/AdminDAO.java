package com.kh.woofly.admin.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.member.model.vo.MemberAddress;

@Mapper
public interface AdminDAO {

	HashMap<String, ArrayList<Object>> selectAllBoard();

	int getReportCount(String searchId);

	ArrayList<Report> selectReportList(RowBounds rowBounds, String search);

	ArrayList<HashMap<String, Object>> selectReportRank();

	Report selectReportDetail(int rNo);

	ArrayList<Report> selectTargetList(String rAccused);

	int updateStopDate(String rAccused);

	int updateReportSit(Report r);

	int selectWarningCount(Report r);

	ArrayList<Member> selectAllMembers(RowBounds rowBounds, String mbId);

	ArrayList<MemberAddress> selectAllAddress();

	int getMembersCount(String mbId);

}
