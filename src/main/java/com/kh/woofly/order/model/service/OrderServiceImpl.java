package com.kh.woofly.order.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.woofly.common.PageInfo;
import com.kh.woofly.member.model.vo.Point;
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
	public ArrayList<Order> selectMyBuying(PageInfo pi, HashMap<String, Object> map) {
		int offset;
		int limit;
		if (pi != null) {
			offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
			limit = pi.getBoardLimit();
		} else {
			offset = 0;
			limit = 5;
		}
		RowBounds rowbounds = new RowBounds(offset, limit);		
		
		return oDAO.selectMyBuying(rowbounds, map);
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

	@Override
	public int getBuyingCount(String id) {
		return oDAO.getBuyingCount(id);
	}

	@Override
	public int deletePoints(String id) {
		return oDAO.deletePoints(id);
	}

	@Override
	public ArrayList<Point> selectMyPoints(String id) {
		return oDAO.selectMyPoints(id);
	}

}
