package com.kh.woofly.contest.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.contest.model.dao.ContestDAO;

@Service
public class ContestServiceImpl implements ContestService {
	
	@Autowired
	private ContestDAO cDAO;
	
	
	
	
}
