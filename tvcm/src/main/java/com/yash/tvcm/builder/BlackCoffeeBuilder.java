package com.yash.tvcm.builder;

import com.yash.tvcm.config.BlackCoffeeConfiguration;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;

public class BlackCoffeeBuilder extends AbstractDrinkBuilder {

	public BlackCoffeeBuilder() {
		setDrinkConfigurer(BlackCoffeeConfiguration.getDrinkConfigurer());
	}

	@Override
	public int prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		if (checkForDrinkTypeBlackCoffee(order)) {
			int makeOrder=super.prepareDrink(order);
			return makeOrder;
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.BLACK_COFFEE + " and found " + order.getDrink());
		}
	}

	private boolean checkForDrinkTypeBlackCoffee(Order order) {
		return order.getDrink() == Drink.BLACK_COFFEE;
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new BlackCoffeeBuilder();
	}

}
