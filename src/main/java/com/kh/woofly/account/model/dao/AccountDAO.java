package com.kh.woofly.account.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.member.model.vo.Member;

@Mapper
public interface AccountDAO {
	public Member login(Member m);
	
}
