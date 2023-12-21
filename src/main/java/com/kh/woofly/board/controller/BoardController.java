package com.kh.woofly.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
   @RequestMapping({"/free"})
   public String freeBoardView(@RequestParam(value = "page",defaultValue = "1") String page, Model model) {
      model.addAttribute("page", page);
      return "freeBoard";
   }

   @RequestMapping({"/free/detail"})
   public String freeBoardDetail(@RequestParam(value = "page",defaultValue = "1") String page, Model model) {
      return "freeBoardDetail";
   }

   @RequestMapping({"/free/write"})
   public String freeBoardWrite() {
      return "freeBoardWrite";
   }

   @RequestMapping({"/free/edit"})
   public String freeBoardEdit() {
      return "freeBoardEdit";
   }

   @RequestMapping({"/used"})
   public String usedBoardView() {
      return "usedBoard";
   }

   @RequestMapping({"/used/detail"})
   public String usedBoardDetail() {
      return "usedBoardDetail";
   }

   @RequestMapping({"/used/edit"})
   public String usedBoardEdit() {
      return "usedBoardEdit";
   }

   @RequestMapping({"/used/write"})
   public String usedBoardWrite() {
      return "usedBoardWrite";
   }

   @GetMapping("my/saved/used")
   public String savedUsedView() {
      return "mySavedUsed";
   }
}
    