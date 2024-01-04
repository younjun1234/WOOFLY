package com.kh.woofly.order.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.kh.woofly.common.PageInfo;
import com.kh.woofly.order.model.vo.Order;
import com.kh.woofly.order.model.vo.OrderDetail;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;

public interface OrderService {

	ArrayList<Order> selectMyBuying(PageInfo pi, HashMap<String, Object> map);

	Order selectOrder(int orderNo);

	ArrayList<OrderDetail> selectOrderDetails(int orderNo);

	ProductAttm selectOrderProductsAttm(OrderDetail detail);

	ProductAttm selectOrderAttm(Order order);

	Product selectMostExpensive(Order order);

	int getBuyingCount(String id);
	
	
}
