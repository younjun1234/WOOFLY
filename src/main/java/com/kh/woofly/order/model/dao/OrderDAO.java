package com.kh.woofly.order.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.woofly.order.model.vo.Order;
import com.kh.woofly.order.model.vo.OrderDetail;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;

@Mapper
public interface OrderDAO {

	ArrayList<Order> selectMyBuying(String id);

	Order selectOrder(int orderNo);

	ArrayList<OrderDetail> selectOrderDetails(int orderNo);

	ProductAttm selectOrderProductsAttm(OrderDetail detail);

	ProductAttm selectOrderAttm(Order order);

	Product selectMostExpensive(Order order);

}
