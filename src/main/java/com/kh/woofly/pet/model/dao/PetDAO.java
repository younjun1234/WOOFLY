package com.kh.woofly.pet.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.pet.model.vo.Pet;

@Mapper
public interface PetDAO {

	int petAdd(Pet p);

	ArrayList<Pet> petInfoList(String id);

	Pet petDetail(int petId);

}
