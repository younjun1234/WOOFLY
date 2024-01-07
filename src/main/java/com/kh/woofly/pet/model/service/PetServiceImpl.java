package com.kh.woofly.pet.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.pet.model.dao.PetDAO;
import com.kh.woofly.pet.model.vo.Pet;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetDAO pDAO;
	
	@Override
	public int petAdd(Pet p) {
		return pDAO.petAdd(p);
	}

	@Override
	public ArrayList<Pet> petInfoList(String id) {
		return pDAO.petInfoList(id);
	}

	@Override
	public Pet petDetail(int petId) {
		return pDAO.petDetail(petId);
	}


	@Override
	public int petEditName(HashMap<String, String> petInfo) {
		return pDAO.petEditName(petInfo);
	}

	@Override
	public int petEditIntro(HashMap<String, String> petInfo) {
		return pDAO.petEditIntro(petInfo);
	}

	@Override
	public int petEditBirth(HashMap<String, String> petInfo) {
		return pDAO.petEditBirth(petInfo);
	}

	@Override
	public int petEditBreed(HashMap<String, String> petInfo) {
		return pDAO.petEditBreed(petInfo);
	}

	@Override
	public int petEditWeight(HashMap<String, String> petInfo) {
		return pDAO.petEditWeight(petInfo);
	}

	@Override
	public int petEditGender(HashMap<String, String> petInfo) {
		return pDAO.petEditGender(petInfo);
	}

	@Override
	public int petEditMemo(HashMap<String, String> petInfo) {
		return pDAO.petEditMemo(petInfo);
	}

	@Override
	public int petDelete(int petId) {
		return pDAO.petDelete(petId);
	}

	@Override
	public int editPetPhoto(Pet p) {
		return pDAO.editPetPhoto(p);
	}

	@Override
	public int deletePetPhoto(int petId) {
		return pDAO.deletePetPhoto(petId);
	}

	@Override
	public int petDiaryWrite(String id) {
		return pDAO.petDiaryWrite(id);
	}
	 

}
