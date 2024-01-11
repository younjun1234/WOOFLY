package com.kh.woofly.admin.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.board.model.vo.PageInfo;

@Mapper
public interface AdminDAO {

	HashMap<String, ArrayList<Object>> selectAllBoard();

	int getReportCount();

	ArrayList<Report> selectReportList(PageInfo pi);

}
