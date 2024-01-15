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
public class LostBoard {
   private int mNo;
   private String mTitle;
   private String mContent;
   private int mCount;
   private Date mCreateDate;
   private String mStatus;
   private String mbId;
   private String mbNickName;
   
//   // 수정된 첨부파일 게시글 업로드
//	public void setLostBoardType(int i) {
//		// TODO Auto-generated method stub
//		
//	}
   
}





