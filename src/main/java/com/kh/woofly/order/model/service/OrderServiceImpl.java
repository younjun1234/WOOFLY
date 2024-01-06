package com.kh.woofly.order.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.order.model.dao.OrderDAO;
import com.kh.woofly.order.model.vo.Order;
import com.kh.woofly.order.model.vo.OrderDetail;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO oDAO;

	@Override
	public ArrayList<Order> selectMyBuying(String id) {
		return oDAO.selectMyBuying(id);
	}

	@Override
	public Order selectOrder(int orderNo) {
		return oDAO.selectOrder(orderNo);
	}

	@Override
	public ArrayList<OrderDetail> selectOrderDetails(int orderNo) {
		return oDAO.selectOrderDetails(orderNo);
	}

	@Override
	public ProductAttm selectOrderProductsAttm(OrderDetail detail) {
		return oDAO.selectOrderProductsAttm(detail);
	}

	@Override
	public ProductAttm selectOrderAttm(Order order) {
		return oDAO.selectOrderAttm(order);
	}

	@Override
	public Product selectMostExpensive(Order order) {
		return oDAO.selectMostExpensive(order);
	}

}
