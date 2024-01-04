package com.kh.woofly.board.model.vo;

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

public class Reply {

	private int rNo;
	private String bType;
	private int bNo;
	private String reContent;
	private Date reDate;
	private int reLike;
	private String reDStatus;
	private String mbId;
	private String mbNickname;
}

