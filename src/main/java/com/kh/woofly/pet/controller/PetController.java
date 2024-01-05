package com.kh.woofly.pet.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.kh.woofly.member.model.exception.MemberException;
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
			return "petInfo";
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
		
		petInfo.put("newPetWeight", newPetWeight);
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
	public String petEditGender(Model model, @RequestParam("petGender") String newPetGender, @RequestParam("petId") String petId) {
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetGender", newPetGender);
		petInfo.put("petId", petId);
		
		int result = pService.petEditGender(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/petEditMemo.dw")
	public String petEditMemo(Model model, @RequestParam("petMemo") String newPetMemo, @RequestParam("petId") String petId) {
		HashMap<String,String> petInfo = new HashMap<String,String>(); 
		
		petInfo.put("newPetMemo", newPetMemo);
		petInfo.put("petId", petId);
		
		int result = pService.petEditMemo(petInfo);
		if(result>0) {
			model.addAttribute("p", result);
			return "redirect:pet/petDetail/"+petId;
		} else {
			throw new PetException("마이펫 수정에 실패하였습니다.");
		}
	}
	
	@PostMapping("/editPetPhoto.dw")
	public String editPetPhoto(@RequestParam("file") ArrayList<MultipartFile> file, @RequestParam("petId") int petId) {
		 
		 MultipartFile upload = file.get(0); //file에서 첫 번째 파일 가져오기
		 
		 Pet p = pService.petDetail(petId); //petId 정보 가져오기
		 
		 if(!upload.getOriginalFilename().equals("")) { //업로드된 파일이 존재하면서
			 if(!p.getPetProfile().equals("default_petprofile.jpg")) { //디폴트 사진과 파일명이 같다면 
				 deleteFile(p.getPetProfile());  //기존 사진 삭제
			 }
			 
			 String renameName = saveFile(upload); //사용자가 업로드된 파일 저장
			 if(renameName != null) { 
				 p.setPetProfile(renameName); //새 파일명을 petPhoto에 세팅
			 }
		 }
		 
		 int result = pService.editPetPhoto(p);
		 
		 if(result > 0) {
			 return "redirect:pet/petDetail/"+petId;
		 } else {
			 throw new PetException("펫 프로필 수정에 실패하였습니다.");
		 }
	}

	private String saveFile(MultipartFile file) {
		String os = System.getProperty("os.name").toLowerCase();
		String savePath = null;
		if (os.contains("win")) {
			savePath = "C:\\" + "\\uploadFiles\\woofly";
		} else if (os.contains("mac")) {
			savePath = "/Users/younjun/Desktop/WorkStation/uploadFiles/woofly";
		}
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		// 2. 저장된 file rename 
		Date time = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		
		String originFileName = file.getOriginalFilename();
		String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		// 3. rename된 파일을 저장소에 저장
		String renamePath = folder + "/" + renameFileName;
		try {
			file.transferTo(new File(renamePath));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return renameFileName;
	}

	private void deleteFile(String fileName) {
		String os = System.getProperty("os.name").toLowerCase();
		String savePath = null;
		if (os.contains("win")) {
			savePath = "C:\\" + "\\uploadFiles\\woofly";
		} else if(os.contains("mac")) {
			savePath = "/Users/younjun/Desktop/WorkStation/uploadFiles/woofly/";
		}
		File f = new File(savePath + fileName);
		if(f.exists()) {
			f.delete();
		}
	}

	@GetMapping("/deletePetPhoto.dw")
	public String deletePetPhoto(@RequestParam("petId") int petId) {
		
		Pet p = pService.petDetail(petId);
		System.out.println(p);
		if(!p.getPetProfile().equals("default_petprofile.jpg")) {
			deleteFile(p.getPetProfile());
			
			int result = pService.deletePetPhoto(petId);
			
			if(result == 0) {
				throw new PetException("펫 프로필 사진 삭제에 실패하였습니다");
			} else {
				p.setPetProfile("default_petprofile.jpg");
			}
			
		}
		return "redirect:pet/petDetail/"+petId;
	}
	
//	if(!upload.getOriginalFilename().equals("")) { //업로드된 파일이 존재하는지 않고
//		 if(!p.getPetProfile().equals("default_petprofile.jpg")) { //디폴트 사진과 파일명이 같다면 
//			 deleteFile(p.getPetProfile());  //기존 사진 삭제
//		 }
//		 
//		 String renameName = saveFile(upload); //사용자가 업로드된 파일 저장
//		 if(renameName != null) { 
//			 p.setPetProfile(renameName); //새 파일명을 petPhoto에 세팅
//		 }
//	 }
//	 
//	 int result = pService.editPetPhoto(p);
//	 
//	 if(result > 0) {
//		 return "redirect:pet/petDetail/"+petId;
//	 } else {
//		 throw new PetException("펫 프로필 수정에 실패하였습니다.");
//	 }
//}
	
//	public String deleteMbPhoto(HttpSession session) {
//		Member loginUser = ((Member)session.getAttribute("loginUser"));
//		if (!loginUser.getMbPhoto().equals("default_profile.png")) {
//			deleteFile(loginUser.getMbPhoto());
//			int result = mService.deleteMbPhoto(loginUser);
//			
//			if (result == 0) {
//				throw new MemberException("프로필 사진 삭제에 실패하였습니다");
//			} else {
//				loginUser.setMbPhoto("default_profile.png");
//			}
//		}
//		return "redirect:/my/profile-edit";
//	}
	
	

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
	
	@GetMapping("pet/petDelete/{petId}")
	public String petDelete(@PathVariable("petId") int petId) {
		
		int result = pService.petDelete(petId);
		
		if(result > 0) {
			return "redirect:/pet/petInfo";	
		} else {
			throw new PetException("마이펫 삭제에 실패하였습니다.");
		}
	}
}