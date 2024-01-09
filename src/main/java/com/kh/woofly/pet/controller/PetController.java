package com.kh.woofly.pet.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.kh.woofly.pet.model.vo.Diary;
import com.kh.woofly.pet.model.vo.Pet;

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
	public String petDiaryView(@ModelAttribute Diary d, @ModelAttribute Pet p, Model model, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		d.setWriterId(id);
		
		ArrayList<Diary> list = pService.petDiaryList(id);
		ArrayList<Pet> pet = pService.petInfoList(id);
		
		model.addAttribute("list", list);
		model.addAttribute("pet", pet);
		
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
	public String petAdd(@ModelAttribute Pet p, HttpSession session, @RequestParam("file") ArrayList<MultipartFile> file) {
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
	
	@GetMapping("pet/petDelete/{petId}")
	public String petDelete(@PathVariable("petId") int petId) {
		
		int result = pService.petDelete(petId);
		
		if(result > 0) {
			return "redirect:/pet/petInfo";	
		} else {
			throw new PetException("마이펫 삭제에 실패하였습니다.");
		}
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
	public String petDiaryWriteView(Model model, HttpSession session) { 
		//보유 강아지 리스트 뿌리기
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		ArrayList<Pet> list = pService.petInfoList(id);
		model.addAttribute("list", list);
		
		return "petDiaryWrite";
	}
	
	@PostMapping("/petDiaryWrite.dw")
	public String petDiaryWrite(@ModelAttribute Diary d, @RequestParam("date") Date date, @RequestParam("petName") int petId, HttpSession session) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		d.setDrDate(date);
	    d.setWriterId(id);
	    d.setPetId(petId);
	    
	    System.out.println(d);
	    int result = pService.petDiaryWrite(d);
	    int drNo = d.getDrNo();
	    
	    if(result > 0) {
	    	return "redirect:pet/petDiaryDetail/"+drNo;
	    } else {
	    	throw new PetException("마이펫 다이어리 등록에 실패하였습니다.");
	    }
	}
	

	@GetMapping("pet/petDiaryDetail")
	public String petDiaryDetailView(@ModelAttribute Diary d, @RequestParam("petId") int petId, HttpSession session) {
//		String id = ((Member)session.getAttribute("loginUser")).getMbId();
//		d.setPetId(petId);
//		System.out.println(petId);
		return "petDiaryDetail";
	}
	
	@GetMapping("pet/petDiaryDetail/{drNo}")
	public String petDiaryDetail(@PathVariable("drNo") int drNo, Model model) {
		Diary list = pService.petDiaryDetail(drNo);
		
		if(list != null) {
			model.addAttribute("d", list);
			return "petDiaryDetail";
		} else {
			throw new PetException("마이펫 다이어리 조회에 실패하였습니다.");
		}
	}
	
	@GetMapping("pet/petDiaryEdit/{drNo}")
	public String petDiaryEditView(@PathVariable("drNo") int drNo, Model model, HttpSession session) {
		//수정화면 뿌려주는 곳
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		
		Diary list = pService.petDiaryDetail(drNo);
		ArrayList<Pet> pet = pService.petInfoList(id); //가지고 있는 pet리스트 뿌려줘야해서

		if(list != null) {
			model.addAttribute("d", list);
			model.addAttribute("pet", pet);
			return "petDiaryEdit";
		} else {
			throw new PetException("마이펫 다이어리 조회에 실패하였습니다.");
		}
	}
	
//	@GetMapping("pet/petDetail/{petId}")
//	public String petDetail(@PathVariable("petId") int petId, Model model) {
//		Pet pet = pService.petDetail(petId);
//		
//		if(pet != null) {
//			model.addAttribute("p", pet);
//			return "petDetail";
//		} else {
//			throw new PetException("마이펫 상세조회에 실패하였습니다.");
//		}
//	}
	
	@PostMapping("/petDiaryEdit.dw")
	public String petDiaryEdit(@ModelAttribute Diary d, @RequestParam("date") Date date) {
		d.setDrDate(date);
		
		int result = pService.petDiaryEdit(d);
		int drNo = d.getDrNo();
		
		if(result > 0) {
			return "redirect:pet/petDiaryDetail/"+drNo;
		} else {
			throw new PetException("마이펫 다이어리 수정에 실패하였습니다.");
		}
	}
	
	@GetMapping("pet/petDiaryDelete/{drNo}")
	public String petDiaryDelete(@PathVariable("drNo") int drNo) {
		int result = pService.petDiaryDelete(drNo);
		
		if(result > 0) {
			return "redirect:/pet/petDiary";	
		} else {
			throw new PetException("마이펫 다이어리 삭제에 실패하였습니다.");
		}
	}
	
}