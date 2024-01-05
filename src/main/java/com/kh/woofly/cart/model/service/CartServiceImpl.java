package com.kh.woofly.cart.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.cart.model.dao.CartDAO;
import com.kh.woofly.cart.model.vo.Cart;
import com.kh.woofly.shop.model.vo.ProductAttm;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDAO cDAO;

	@Override
	public ArrayList<Cart> selectMyCart(String id) {
		return cDAO.selectMyCart(id);
	}

	@Override
	public ProductAttm selectCartAttm(Cart c) {
		return cDAO.selectCartAttm(c);
	}

	@Override
	public int updateMyCart(HashMap<String, Object> map) {
		return cDAO.updateMyCart(map);
	}

	@Override
	public Cart selectCart(int cartId) {
		return cDAO.selectCart(cartId);
	}

}
