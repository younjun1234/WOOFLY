package com.kh.woofly.order.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.order.model.service.OrderService;
import com.kh.woofly.order.model.vo.Order;
import com.kh.woofly.order.model.vo.OrderDetail;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	private OrderService oService;
	
	@GetMapping("/my")
	public String profileHomeView(HttpSession session, Model model) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		ArrayList<Order> oList = oService.selectMyBuying(id);
		ArrayList<ProductAttm> paList = new ArrayList<>();
		ArrayList<Product> pList = new ArrayList<>();
		
		for(Order order : oList) {
			paList.add(oService.selectOrderAttm(order));
			pList.add(oService.selectMostExpensive(order));
		}
		System.out.println(pList);
		System.out.println(paList);
		System.out.println(oList);
		model.addAttribute("pList", pList);
		model.addAttribute("paList", paList);
		model.addAttribute("oList", oList);
		return "myHome";
	}
	
	@GetMapping("my/buying")
	public String buyingView() {
		return "myBuying";
	}
	
	@GetMapping("my/buying/{orderNo}")
	public String buyingDetailView(@PathVariable("orderNo") int orderNo, Model model) {
		System.out.println(orderNo);
		Order order = oService.selectOrder(orderNo);
		ArrayList<OrderDetail> oDetail = oService.selectOrderDetails(orderNo);
		ArrayList<ProductAttm> paList = new ArrayList<>();
		for(OrderDetail detail : oDetail) {
			paList.add(oService.selectOrderProductsAttm(detail));
		}
		System.out.println(paList);
		
		model.addAttribute("paList", paList);
		model.addAttribute("order", order);
		model.addAttribute("details", oDetail);
		return "myBuyingDetail";
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