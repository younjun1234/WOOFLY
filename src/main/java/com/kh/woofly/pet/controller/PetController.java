package com.kh.woofly.pet.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.pet.model.exception.PetException;
import com.kh.woofly.pet.model.service.PetService;
import com.kh.woofly.pet.model.vo.Pet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PetController {

	@Autowired
	private PetService pService;
	
	//마이펫 리스트
	@GetMapping("pet/petInfo")
	public String petInfo(Model model, HttpSession session, @ModelAttribute Pet p) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		p.setOwnerId(id);
		ArrayList<Pet> list = pService.petInfoList(id);
		model.addAttribute("list", list);
			
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

	@GetMapping("pet/petDetail/{petId}")
	public String petDetail(@PathVariable("petId") int petId, Model model) {
		Pet pet = pService.petDetail(petId);
		
		if(pet != null) {
			model.addAttribute("p", pet);
			return "petDetail";
		} else {
			throw new PetException("마이펫 상세조회에 실패하였습니다.");
		}
	}

	@GetMapping("pet/petAdd")
	public String petAddView() {
		return "petAdd";
	}
	
	@PostMapping("/petAdd.dw")
	public String petAdd(@ModelAttribute Pet p, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		p.setOwnerId(id);
		
		int result = pService.petAdd(p);
		if(result > 0) {
			return "petDetail";
		} else {
			throw new PetException("마이펫 등록에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditName.dw")
	public String petEditName(Model model, @RequestParam("petName") String newPetName, @RequestParam("petId") String petId) {
		
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetName", newPetName);
		petInfo.put("petId", petId);
		
		int result = pService.petEditName(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditIntro.dw")
	public String petEditIntro(Model model, @RequestParam("petIntro") String newPetIntro, @RequestParam("petId") String petId) {
		
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetIntro", newPetIntro);
		petInfo.put("petId", petId);
		
		int result = pService.petEditIntro(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditBirth.dw")
	public String petEditBirth(Model model, @RequestParam("petBirth") String newPetBirth, @RequestParam("petId") String petId) {
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetBirth", newPetBirth);
		petInfo.put("petId", petId);
		
		int result = pService.petEditBirth(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditBreed.dw")
	public String petEditBreed(Model model, @RequestParam("petBreed") String newPetBreed, @RequestParam("petId") String petId) {
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetBreed", newPetBreed);
		petInfo.put("petId", petId);
		
		int result = pService.petEditBreed(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditWeight.dw")
	public String petEditWeight(Model model, @RequestParam("petWeight") String newPetWeight, @RequestParam("petId") String petId) {
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetBreed", newPetWeight);
		petInfo.put("petId", petId);
		
		int result = pService.petEditWeight(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditGender.dw")
	public String petEditGender() {
		return null;
	}
	
	@PostMapping("/petEditMemo.dw")
	public String petEditMemo() {
		return null;
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