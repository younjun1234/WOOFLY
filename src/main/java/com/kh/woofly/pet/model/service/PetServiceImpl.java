package com.kh.woofly.pet.model.service;

import java.util.ArrayList;

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

}
