package com.kh.woofly.pet.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.board.model.vo.Attachment;
import com.kh.woofly.pet.model.vo.Album;
import com.kh.woofly.pet.model.vo.Diary;
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

	int editPetPhoto(Pet p);

	int deletePetPhoto(int petId);

	int petDiaryWrite(Diary d);

	ArrayList<Diary> petDiaryList(String id);

	Diary petDiaryDetail(int drNo);

	int petDiaryEdit(Diary d);

	int petDiaryDelete(int drNo);

	int insertPetPhoto(Album a);

	int insertPetAlbum(Attachment att);

	ArrayList<Album> selectMyAlbum(String id);

	ArrayList<Attachment> selectMyAlbumAttm(HashMap<String, Object> map);

	ArrayList<Album> selectMyAlbums(HashMap<String, String> map);

	ArrayList<Album> petPhotoDetail(int abNo);

	ArrayList<Pet> petInfo(int abNo);

}
