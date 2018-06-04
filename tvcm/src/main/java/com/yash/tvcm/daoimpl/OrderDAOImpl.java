package com.yash.tvcm.daoimpl;

import java.util.List;

import com.yash.tvcm.dao.OrderDAO;
import com.yash.tvcm.model.Order;
import com.yash.tvcm.util.JSONUtil;

public class OrderDAOImpl implements OrderDAO {

	public int save(Order order) {
		int rowsAffected = JSONUtil.saveObject(order);
		return rowsAffected;

	}

	public List<Order> retrieve() {
		List<Order> totalOrders = JSONUtil.retrieveListOfOrders();
		return totalOrders;
	}

}
