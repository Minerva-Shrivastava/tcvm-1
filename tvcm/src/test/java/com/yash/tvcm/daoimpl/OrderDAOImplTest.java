package com.yash.tvcm.daoimpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tvcm.dao.OrderDAO;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.model.Order;

public class OrderDAOImplTest {

	private OrderDAO orderDAO;

	@Before
	public void setUp() throws Exception {
		this.orderDAO = new OrderDAOImpl();
	}

	@Test
	public void save_OrderObjectIsGiven_ShouldReturnOneWhenOrderIsSaved() {
		Order order = new Order(1, Drink.TEA, Drink.TEA.getCost());
		int rowsAffected = orderDAO.save(order);
		assertEquals(1, rowsAffected);
	}

	@Test
	public void retrieve_ShouldReturnListOfOrders() {
		List<Order> orders = orderDAO.retrieve();
		assertEquals(1, orders.size());
	}

}
