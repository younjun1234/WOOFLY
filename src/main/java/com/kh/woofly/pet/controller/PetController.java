package com.kh.woofly.pet.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.member.model.exception.MemberException;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.pet.model.service.PetService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PetController {

	@Autowired
	private PetService pService;

	@GetMapping("pet/petInfo")
	public String petInfoView() {
		return "petInfo";
	}

	@GetMapping("pet/petPhoto")
	public String petPhotoView() {
		return "petPhoto";
	}

	@GetMapping("pet/petDiary")
	public String petDiaryView() {
		return "petDiary";
	}

	@GetMapping("pet/petContest")
	public String petContestView() {
		return "petContest";
	}

	@GetMapping("pet/petDetail")
	public String petDetailtView() {
		return "petDetail";
	}

	@GetMapping("pet/petAdd")
	public String petAddView() {
		return "petAdd";
	}

	@PostMapping("/editPetPhoto.dw")
	public String editMbPhoto(@RequestParam("file") ArrayList<MultipartFile> file, HttpServletRequest request) {
		return null;
	}

	@PostMapping("/deletePetPhoto.dw")
	public String deletePetPhoto() {
		return null;
	}

	@GetMapping("pet/petPhotoDetail")
	public String petPhotoDetailView() {
		return "petPhotoDetail";
	}

	@GetMapping("pet/petPhotoWrite")
	public String petpetPhotoWriteView() {
		return "petPhotoWrite";
	}

	@GetMapping("pet/petDiaryWrite")
	public String petDiaryWriteView() {
		return "petDiaryWrite";
	}

	@GetMapping("pet/petDiaryDetail")
	public String petDiaryDetailView() {
		return "petDiaryDetail";
	}

}