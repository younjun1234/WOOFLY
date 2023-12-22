package com.kh.woofly.account.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.account.model.dao.AccountDAO;
import com.kh.woofly.member.model.vo.Member;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDAO aDAO;

	@Override
	public Member login(Member m) {
		return aDAO.login(m);
	}
}
