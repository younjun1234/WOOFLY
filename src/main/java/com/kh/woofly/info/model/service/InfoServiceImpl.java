package com.kh.woofly.info.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.info.model.dao.InfoDAO;

@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private InfoDAO iDAO;
	
	

}
