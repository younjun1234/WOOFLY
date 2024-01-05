package com.kh.woofly.cart.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.cart.model.vo.Cart;
import com.kh.woofly.shop.model.vo.ProductAttm;

@Mapper
public interface CartDAO {

	ArrayList<Cart> selectMyCart(String id);

	ProductAttm selectCartAttm(Cart c);

	int updateMyCart(HashMap<String, Object> map);

	Cart selectCart(int cartId);


}
