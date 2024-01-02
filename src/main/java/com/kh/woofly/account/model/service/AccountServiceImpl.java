package com.kh.woofly.account.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.account.model.dao.AccountDAO;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.member.model.vo.MemberAddress;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDAO aDAO;

	@Override
	public Member login(Member m) {
		return aDAO.login(m);
	}

	@Override
	public int idCheck(String mbId) {
		return aDAO.idCheck(mbId);
	}

	@Override
	public int nickCheck(String mbNickName) {
		return aDAO.nickCheck(mbNickName);
	}

	@Override
	public int signUpMember(Member m) {
		return aDAO.signUpMember(m);
	}

	@Override
	public int signUpMemberAddr(MemberAddress ma) {
		return aDAO.signUpMemberAddr(ma);
	}

}
