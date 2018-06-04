package com.yash.tvcm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.yash.tvcm.dao.OrderDAO;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;
import com.yash.tvcm.service.OrderService;

public class OrderServiceImplTest {

	private OrderService orderService;
	private OrderDAO orderDAO;

	@Before
	public void setUp() throws Exception {
		this.orderDAO = mock(OrderDAO.class);
		this.orderService = new OrderServiceImpl(orderDAO);
	}

	@Test(expected = NullFieldException.class)
	public void orderDrink_quantityIsNull_ThrowNullFieldException() throws NullFieldException {
		Integer quantity = null;
		Drink tea = Drink.TEA;
		int cost = tea.getCost();
		Order order = new Order(quantity, tea, cost);
		orderService.makeOrder(order);
	}

	@Test(expected = NullFieldException.class)
	public void orderDrink_DrinkTypeIsNull_ThrowNullFieldException() throws NullFieldException {
		Integer quantity = 1;
		Drink tea = null;
		int cost = 0;
		Order order = new Order(quantity, tea, cost);
		orderService.makeOrder(order);
	}

	@Test
	public void orderDrink_DrinkTypeGiven_ShouldReturnOneWhenOrderGivenSuccessfully() throws NullFieldException {
		Integer quantity = 1;
		Drink tea = Drink.TEA;
		int cost = Drink.TEA.getCost();
		Order order = new Order(quantity, tea, cost);
		when(orderDAO.save(order)).thenReturn(1);
		int orderMade = orderService.makeOrder(order);
		assertEquals(1, orderMade);
	}

	@Test
	public void getListOfAllOrders_ShouldReturnListOfAllOrders() throws NullFieldException {
		List<Order> orders = Arrays.asList(new Order(2, Drink.TEA, 2 * Drink.TEA.getCost()),
				new Order(3, Drink.COFFEE, 3 * Drink.COFFEE.getCost()));
		when(orderDAO.retrieve()).thenReturn(orders);
		List<Order> ordersGiven = orderService.getAllOrders();
		assertEquals(2, ordersGiven.size());
	}

	 @Test
	 public void checkTotalDrinkWise_ShouldReturnSetOfDrinksAndItsTotalCost()
	 throws NullFieldException {
	 List<Order> orders=Arrays.asList(new Order(2, Drink.TEA,
	 2*Drink.TEA.getCost()),new Order(3, Drink.COFFEE,
	 3*Drink.COFFEE.getCost()));
	 when(orderDAO.retrieve()).thenReturn(orders);
	 Map<Drink, Integer> drinkAndItsTotalCost=orderService.checkTotalSaleDrinkWise();
	 assertEquals(2, drinkAndItsTotalCost.size());
	 }

}
