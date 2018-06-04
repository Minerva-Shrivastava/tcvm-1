package com.yash.tvcm.dao;

import java.util.List;

import com.yash.tvcm.model.Order;

public interface OrderDAO {

	public int save(Order order);

	public List<Order> retrieve();

}
