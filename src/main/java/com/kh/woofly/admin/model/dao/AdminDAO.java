package com.kh.woofly.admin.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {

	HashMap<String, ArrayList<Object>> selectAllBoard();

}
