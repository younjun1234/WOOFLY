package com.kh.woofly.shop.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.shop.model.dao.ShopDAO;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;
import com.kh.woofly.shop.model.vo.ProductCategory;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopDAO sDAO;

	@Override
	public int insertProduct(Product p) {
		return sDAO.insertProduct(p);
	}

	@Override
	public ArrayList<ProductCategory> selectCategory(Integer i) {
		return sDAO.selectCategory(i);
	}

	@Override
	public int insertAttm(ArrayList<ProductAttm> list) {
		return sDAO.insertAttm(list);
	}

	@Override
	public ArrayList<Product> selectProducts() {
		return sDAO.selectProducts();
	}

	@Override
	public ArrayList<ProductAttm> selectProductAttm(String string) {
		return sDAO.selectProductAttm(string);
	}

}
