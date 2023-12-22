package com.kh.woofly.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetController {
   
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