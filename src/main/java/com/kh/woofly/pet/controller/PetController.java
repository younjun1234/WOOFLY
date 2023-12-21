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
}