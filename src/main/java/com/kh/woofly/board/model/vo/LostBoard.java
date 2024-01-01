package com.kh.woofly.board.model.vo;

import java.sql.Clob;
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
   private Clob mContent;
   private int mCount;
   private Date mCreateDate;
   private String mStatus;
   private String mbId;
   private String mbNickName;
}





