package com.kh.woofly.account.model.service;

import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.member.model.vo.MemberAddress;

public interface AccountService {

	public Member login(Member m);

	public int idCheck(String mbId);

	public int nickCheck(String mbNickName);

	public int signUpMember(Member m);

	public int signUpMemberAddr(MemberAddress ma);

	public int checkNameEmail(Member m);

	public Member sendId(Member info);

	public int checkIdEmail(Member m);

}
