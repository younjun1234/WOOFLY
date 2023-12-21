package com.kh.woofly.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
   @GetMapping("my/buying")
   public String buyingView() {
      return "myBuying";
   }

   @GetMapping("my/selling")
   public String sellingView() {
      return "mySelling";
   }

   @GetMapping("my/saved")
   public String savedView() {
      return "mySaved";
   }

   @GetMapping("my/cart")
   public String cardView() {
      return "myCart";
   }
}