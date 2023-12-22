package com.kh.woofly.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

	private String mbId;
	private String mbPwd;
	private String mbName;
	private String mbPhoto;
	private String mbIntro;
	private String mbNickname;
	private String mbEmail;
	private Date mbBirth;
	private String mbTel;
	private int mbPoint;
	private String mbStatus;
	private String isAdmin;
	private String isPrivate;
	private String isBanned;
}
