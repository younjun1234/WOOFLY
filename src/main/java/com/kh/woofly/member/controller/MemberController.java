package com.kh.woofly.member.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
   @GetMapping("/my")
   public String profileHomeView() {
      return "myHome";
   }

   @GetMapping("my/login-edit")
   public String loginView() {
      return "myLogin";
   }

   @GetMapping("my/profile-edit")
   public String profileView() {
      return "myProfile";
   }

   @GetMapping("my/address")
   public String addressView() {
      return "myAddress";
   }

   @GetMapping("my/payment")
   public String paymentView() {
      return "myPayment";
   }

   @GetMapping("my/point")
   public String pointView() {
      return "myPoint";
   }

   @GetMapping("my/addPayment")
   public String addPayment(@RequestParam("authKey") String authKey, @RequestParam("customerKey") String customerKey) {
      System.out.println(authKey);
      System.out.println(customerKey);
      String billingKey = Base64.getEncoder().encodeToString("test_sk_kYG57Eba3G6AeDn45qa98pWDOxmA:".getBytes());
      System.out.println(billingKey);
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.tosspayments.com/v1/billing/authorizations/issue")).header("Authorization", "Basic " + billingKey).header("Content-Type", "application/json").method("POST", BodyPublishers.ofString("{\"authKey\":\"" + authKey + "\",\"customerKey\":\"" + customerKey + "\"}")).build();

      try {
         HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
         System.out.println((String)response.body());
      } catch (InterruptedException | IOException var7) {
         var7.printStackTrace();
      }

      return "myPayment";
   }

   @GetMapping("my/profile")
   public String memberView() {
      return "profile";
   }
}