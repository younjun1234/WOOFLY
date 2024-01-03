package com.kh.woofly.pet.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.pet.model.vo.Pet;

public interface PetService {

	int petAdd(Pet p);

	ArrayList<Pet> petInfoList(String id);

	Pet petDetail(int petId);

	int petEditName(HashMap<String, String> petInfo);

	int petEditIntro(HashMap<String, String> petInfo);

	int petEditBirth(HashMap<String, String> petInfo);

	int petEditBreed(HashMap<String, String> petInfo);

	int petEditWeight(HashMap<String, String> petInfo);

}
