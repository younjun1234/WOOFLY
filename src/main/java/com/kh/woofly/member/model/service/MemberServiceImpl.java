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

	@Override
	public int editMbPhoto(Member loginUser) {
		return mDAO.editMbPhoto(loginUser);
	}

	@Override
	public int updatePwd(Member loginUser) { 
		return mDAO.updatePwd(loginUser);
	}

	@Override
	public int updateEmail(Member loginUser) {
		return mDAO.updateEmail(loginUser);
	}

	@Override
	public int updatePhone(Member loginUser) {
		return mDAO.updatePhone(loginUser);
	}

	@Override
	public int updateMbStatus(Member member) {
		return mDAO.updateMbStatus(member);
	}

}
