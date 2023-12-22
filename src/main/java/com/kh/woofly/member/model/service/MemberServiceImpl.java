package com.kh.woofly.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.member.model.dao.MemberDAO;
import com.kh.woofly.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO mDAO;
	
	@Override
	public ArrayList<Member> getBlackList(String id) {
		return mDAO.getBlackList(id);
	}

	@Override
	public int removeBlock(HashMap<String, String> map) {
		return mDAO.removeBlock(map);
	}

	@Override
	public int editNickName(Member loginUser) {
		return mDAO.editNickName(loginUser);
	}

	@Override
	public int editName(Member loginUser) {
		return mDAO.editName(loginUser);
	}

	@Override
	public int editIntro(Member loginUser) {
		return mDAO.editIntro(loginUser);
	}

	@Override
	public int editIsPrivate(Member loginUser) {
		return mDAO.editIsPrivate(loginUser);
	}

}
