package com.kh.woofly.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.member.model.vo.Member;

public interface MemberService {

	ArrayList<Member> getBlackList(String id);

	int removeBlock(HashMap<String, String> map);

	int editNickName(Member loginUser);

	int editName(Member loginUser);

	int editIntro(Member loginUser);

	int editIsPrivate(Member loginUser);

	int editMbPhoto(Member loginUser);

	int updatePwd(Member loginUser);

	int updateEmail(Member loginUser);

}
