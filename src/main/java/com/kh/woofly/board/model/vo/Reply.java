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
	private int replyId;
	private String replyContent;
	private int refBoardId;
	private String replyWriter;
	private String nickName;
	private Date replyCreateDate;
	private Date replyModifyDate;
	private String replyStatus;
	
	private int rNo;
	
}

