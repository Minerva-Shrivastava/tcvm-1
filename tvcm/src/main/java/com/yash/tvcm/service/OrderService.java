package com.yash.tvcm.service;

import java.util.List;
import java.util.Map;

import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;

public interface OrderService {

	public int makeOrder(Order order) throws NullFieldException;

	public List<Order> getAllOrders();

	public Map<Drink, Integer> checkTotalSaleDrinkWise();

}
