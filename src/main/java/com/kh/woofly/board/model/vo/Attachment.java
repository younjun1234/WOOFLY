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
public class Attachment {
	private int attmId; 
	private String originalName; 
	private String renameName;
	private String attmPath;
	private String attmStatus;
	private int attmLevel;
//	private int refBoardId; <-DB에 없던데
	
	// DB ATTACHMENT엔 있는데 vo엔 없던 것들.
	private Date attmCreateDate;
	private Date attmModifyDate;
	private String attmRefType;
	private int attmRefNo;

}