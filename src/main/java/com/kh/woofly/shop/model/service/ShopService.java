package com.kh.woofly.shop.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.common.Reply;
import com.kh.woofly.common.ReplyLike;
import com.kh.woofly.shop.model.vo.Cart;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;
import com.kh.woofly.shop.model.vo.ProductCategory;

public interface ShopService {

	int insertProduct(Product p);

	ArrayList<ProductCategory> selectCategory(Integer i);

	int insertAttm(ArrayList<ProductAttm> list);

	ArrayList<Product> selectProducts(PageInfo pi, Integer cNo);

	ArrayList<ProductAttm> selectProductAttm(Integer productId);

	int getProductCount(String string);

	int getDetailCount(int cNo);

	ArrayList<ProductCategory> selectedCategory(Integer cNo);

	Product selectDetailProduct(int productId);

	ProductCategory selectDetailCategory(int productDetailNo);

	int insertReply(Reply r);

	ArrayList<Reply> selectReply(Reply r);

	int insertCart(Cart selectC);

	ArrayList<Cart> selectUserCart(String mbId);

	int updateCartQuantity(Cart c);

	int deleteAttm(ArrayList<String> delRename);

	int updateProduct(Product p);

	int updateStock(Product p);

	int deleteProduct(int pId);

	int attmStatusYN(int pId);

	int insertReplyCount(HashMap<String, Object> map);

	int downReplyCount(HashMap<String, Object> map);

	ArrayList<ReplyLike> selectReplyLike(ArrayList<Integer> rNos);

	int updateReply(Reply r);

	int deleteReply(int rNo);


}
