package com.kh.woofly.cart.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.cart.model.vo.Cart;
import com.kh.woofly.shop.model.vo.ProductAttm;

public interface CartService {

	ArrayList<Cart> selectMyCart(String id);

	ProductAttm selectCartAttm(Cart c);

	int updateMyCart(HashMap<String, Object> map);

	Cart selectCart(int cartId);

}
