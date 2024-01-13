package com.kh.woofly.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.common.PageInfo;

public interface AdminService {

	HashMap<String, ArrayList<Object>> selectAllBoard();

	int getReportCount();

	ArrayList<Report> selectReportList(PageInfo pi);
	

}
