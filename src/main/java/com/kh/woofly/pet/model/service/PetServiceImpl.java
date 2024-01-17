package com.kh.woofly.pet.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.admin.model.vo.Report;
import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.common.Reply;
import com.kh.woofly.pet.model.dao.PetDAO;
import com.kh.woofly.pet.model.vo.Album;
import com.kh.woofly.pet.model.vo.Diary;
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
	public int petDiaryWrite(Diary d) {
		return pDAO.petDiaryWrite(d);
	}

	@Override
	public ArrayList<Diary> petDiaryList(String id) {
		return pDAO.petDiaryList(id);
	}

	@Override
	public Diary petDiaryDetail(int drNo) {
		return pDAO.petDiaryDetail(drNo);
	}

	@Override
	public int petDiaryEdit(Diary d) {
		return pDAO.petDiaryEdit(d);
	}

	@Override
	public int petDiaryDelete(int drNo) {
		return pDAO.petDiaryDelete(drNo);
	}

	@Override
	public int insertPetPhoto(Album a) {
		return pDAO.insertPetPhoto(a);
	}

	@Override
	public int insertPetAlbum(Attachment att) {
		return pDAO.insertPetAlbum(att);
	}

	@Override
	public ArrayList<Album> selectMyAlbum(String id) {
		return pDAO.selectMyAlbum(id);
	}

	@Override
	public ArrayList<Attachment> selectMyAlbumAttm(HashMap<String, Object> map) {
		return pDAO.selectMyAlbumAttm(map);
	}

	@Override
	public ArrayList<Album> selectMyAlbums(HashMap<String, String> map) {
		return pDAO.selectMyAlbums(map);
	}

	@Override
	public ArrayList<Album> petPhotoDetail(int abNo) {
		return pDAO.petPhotoDetail(abNo);
	}

	@Override
	public ArrayList<Pet> petInfo(int abNo) {
		return pDAO.petInfo(abNo);
	}

	@Override
	public Album petAlbumDetail(int abNo) {
		return pDAO.petAlbumDetail(abNo);
	}

	@Override
	public ArrayList<Attachment> petAttmList(int abNo) {
		return pDAO.petAttmList(abNo);
	}

	@Override
	public int petPhotoDelete(int abNo) {
		return pDAO.petPhotoDelete(abNo);
	}

	@Override
	public int insertAlbumReply(Reply r) {
		return pDAO.insertAlbumReply(r);
	}

	@Override
	public ArrayList<Reply> replyList(int abNo) {
		return pDAO.replyList(abNo);
	}

	@Override
	public int updateReply(Reply r) {
		return pDAO.updateReply(r);
	}

	@Override
	public int deleteReply(Reply r) {
		return pDAO.deleteReply(r);
	}

	@Override
	public int insertReport(Report rt) {
		return pDAO.insertReport(rt);
	}

	@Override
	public int checkResult(Report rt) {
		return pDAO.checkResult(rt);
	}
	 

}
