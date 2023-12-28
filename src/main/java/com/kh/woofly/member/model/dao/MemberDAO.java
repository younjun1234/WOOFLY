package com.kh.woofly.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.member.model.vo.Member;

@Mapper
public interface MemberDAO {

	ArrayList<Member> getBlackList(String id);

	int removeBlock(HashMap<String, String> map);

	int editNickName(Member loginUser);

	int editName(Member loginUser);

	int editIntro(Member loginUser);

	int editIsPrivate(Member loginUser);

	int editMbPhoto(Member loginUser);

	int updatePwd(Member loginUser);

	int updateEmail(Member loginUser);

	int updatePhone(Member loginUser);

	int updateMbStatus(Member member);

}
