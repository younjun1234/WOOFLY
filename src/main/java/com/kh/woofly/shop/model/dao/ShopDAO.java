package com.kh.woofly.shop.model.dao;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.woofly.common.Reply;
import com.kh.woofly.shop.model.vo.Cart;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;
import com.kh.woofly.shop.model.vo.ProductCategory;

@Mapper
public interface ShopDAO {

	int insertProduct(Product p);

	ArrayList<ProductCategory> selectCategory(Integer i);

	int insertAttm(ArrayList<ProductAttm> list);

	ArrayList<Product> selectProducts(RowBounds rowBounds, Integer cNumber);

	ArrayList<ProductAttm> selectProductAttm(Integer productId);

	int getProductCount(String string);

	int getDetailCount(int cNumber);

	ArrayList<ProductCategory> selectedCategory(Integer cNo);

	Product selectDetailProduct(int productId);

	ProductCategory selectDetailCategory(int productDetailNo);

	int insertReply(Reply r);

	ArrayList<Reply> selectReply(Reply r);

	int insertCart(Cart selectC);

	ArrayList<Cart> selectUserCart(String mbId);

	int updateCartQuantity(Cart c);

}
