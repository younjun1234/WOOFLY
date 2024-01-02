package com.kh.woofly.pet.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.pet.model.vo.Pet;

@Mapper
public interface PetDAO {

	int petAdd(Pet p);

}
