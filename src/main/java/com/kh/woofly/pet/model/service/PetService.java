package com.kh.woofly.pet.model.service;

import java.util.ArrayList;

import com.kh.woofly.pet.model.vo.Pet;

public interface PetService {

	int petAdd(Pet p);

	ArrayList<Pet> petInfoList(String id);

	Pet petDetail(int petId);

}
