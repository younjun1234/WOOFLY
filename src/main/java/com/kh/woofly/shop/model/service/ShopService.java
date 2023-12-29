package com.kh.woofly.shop.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;
import com.kh.woofly.shop.model.vo.ProductCategory;

public interface ShopService {

	int insertProduct(Product p);

	ArrayList<ProductCategory> selectCategory(Integer i);

	int insertAttm(ArrayList<ProductAttm> list);

	ArrayList<Product> selectProducts();

	ArrayList<ProductAttm> selectProductAttm(String string);

}
