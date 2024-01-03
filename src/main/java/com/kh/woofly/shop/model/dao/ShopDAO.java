package com.kh.woofly.shop.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;
import com.kh.woofly.shop.model.vo.ProductCategory;

@Mapper
public interface ShopDAO {

	int insertProduct(Product p);

	ArrayList<ProductCategory> selectCategory(Integer i);

	int insertAttm(ArrayList<ProductAttm> list);

	ArrayList<Product> selectProducts(RowBounds rowBounds, Integer cNumber);

	ArrayList<ProductAttm> selectProductAttm(String string);

	int getProductCount(String string);

	int getDetailCount(int cNumber);

	ArrayList<ProductCategory> selectedCategory(Integer cNo);

}
