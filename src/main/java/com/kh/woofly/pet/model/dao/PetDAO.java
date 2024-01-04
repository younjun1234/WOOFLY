package com.kh.woofly.pet.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.pet.model.vo.Pet;

@Mapper
public interface PetDAO {

	int petAdd(Pet p);

	ArrayList<Pet> petInfoList(String id);

	Pet petDetail(int petId);

	int petEditName(HashMap<String, String> petInfo);

	int petEditIntro(HashMap<String, String> petInfo);

	int petEditBirth(HashMap<String, String> petInfo);

	int petEditBreed(HashMap<String, String> petInfo);

	int petEditWeight(HashMap<String, String> petInfo);

	int petEditGender(HashMap<String, String> petInfo);

	int petEditMemo(HashMap<String, String> petInfo);

	int petDelete(int petId);

}
