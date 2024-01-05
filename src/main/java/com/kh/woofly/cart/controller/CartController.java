package com.kh.woofly.cart.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.woofly.cart.model.service.CartService;
import com.kh.woofly.cart.model.vo.Cart;
import com.kh.woofly.member.model.vo.Member;
import com.kh.woofly.shop.model.vo.ProductAttm;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@Autowired
	private CartService cService;
	
	@GetMapping("my/cart")
	public String cardView(HttpSession session, Model model) {
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		ArrayList<Cart> cList = cService.selectMyCart(id);
		ArrayList<ProductAttm> paList = new ArrayList<>();
		int total = 0;
		boolean selectedAll = true;
		int selectedCount = 0;
		for (Cart c : cList) {
			paList.add(cService.selectCartAttm(c));
			if(c.getSelected().equals("N")) {
				selectedAll = false;
			} else {
				total += c.getPrice();
				selectedCount++;
			}
		}
	
		System.out.println(cList);
		System.out.println(paList);
		System.out.println(total);
		model.addAttribute("selectedAll", selectedAll);
		model.addAttribute("selectedCount", selectedCount);
		model.addAttribute("total", total);
		model.addAttribute("paList", paList);
		model.addAttribute("cList", cList);
		return "myCart";
	}
	
	@GetMapping("updateMyCart.yj")
	@ResponseBody
	public String updateMyCart(HttpSession session, @RequestParam(value="all", required=false) String all,
							   @RequestParam(value="selected", required=false) String selected, 
							   @RequestParam(value="cartId", required=false) String cartId,
							   @RequestParam(value="quantity", required=false) Integer quantity) {
		HashMap<String, Object> map = new HashMap<>();
		String id = ((Member)session.getAttribute("loginUser")).getMbId();
		if(all != null) {
			map.put("all", all.equals("Y") ? "N" : "Y");
			map.put("id", id);
		} else if (selected != null) {
			map.put("selected", selected);
			map.put("cartId", cartId);
		} else if (quantity != null){
			map.put("quantity", quantity);
			map.put("cartId", cartId);
			
		}
		
		int result = cService.updateMyCart(map);
		if(result > 0) {
			return "good";
		} else {
			return "bad";
		}
	}
	
	@GetMapping("my/cart/checkout/{cartId}")
	public String checkOut(@PathVariable(value="cartId") int cartId) {
		Cart cart = cService.selectCart(cartId);
		System.out.println(cart);
		return "checkOut";
	}
}
























