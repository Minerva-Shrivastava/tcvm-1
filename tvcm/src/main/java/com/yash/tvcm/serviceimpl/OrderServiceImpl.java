package com.yash.tvcm.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yash.tvcm.dao.OrderDAO;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;
import com.yash.tvcm.service.OrderService;

public class OrderServiceImpl implements OrderService {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private OrderDAO orderDAO;

	public OrderServiceImpl(OrderDAO orderDAO) {
		super();
		this.orderDAO = orderDAO;
	}

	public int makeOrder(Order order) throws NullFieldException {
		checkForOrderQuantityIsNull(order);
		checkForOrderDrinkIsNull(order);
		int orderMade = orderDAO.save(order);
		return orderMade;

	}

	private void checkForOrderDrinkIsNull(Order order) throws NullFieldException {
		if (order.getDrink() == null)
			throw new NullFieldException("Drink cannot be null");
	}

	private void checkForOrderQuantityIsNull(Order order) throws NullFieldException {
		if (order.getQuantity() == null)
			throw new NullFieldException("Quantity cannot be null");
	}

	public List<Order> getAllOrders() {
		List<Order> ordersGiven = orderDAO.retrieve();
		return ordersGiven;
	}

	public Map<Drink, Integer> checkTotalSaleDrinkWise() {
		Map<Drink, Integer> drinkAndItsTotalCost = new HashMap<Drink, Integer>();
		List<Order> orders = getAllOrders();
		if (checkWetherAnyOrdersArePresentOrNot(orders)) {
			for (Order order : orders) {
				Drink drink = order.getDrink();
				int totalCost = order.getCost();
				if (checkWetherDrinkIsAlreadyPresentInMap(drinkAndItsTotalCost, drink)) {
					drinkAndItsTotalCost.put(drink, totalCost);
				} else {
					int cost = order.getCost();
					totalCost = drinkAndItsTotalCost.get(drink) + cost;
					drinkAndItsTotalCost.put(drink, totalCost);
				}
			}
		} else {
			logger.info("No orders present");
		}
		return drinkAndItsTotalCost;
	}

	private boolean checkWetherDrinkIsAlreadyPresentInMap(Map<Drink, Integer> drinkAndItsTotalCost, Drink drink) {
		return !drinkAndItsTotalCost.containsKey(drink);
	}

	private boolean checkWetherAnyOrdersArePresentOrNot(List<Order> orders) {
		return orders != null;
	}

}
