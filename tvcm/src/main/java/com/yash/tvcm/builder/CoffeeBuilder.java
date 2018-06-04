package com.yash.tvcm.builder;

import com.yash.tvcm.config.CoffeeConfiguration;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;

public class CoffeeBuilder extends AbstractDrinkBuilder {

	public CoffeeBuilder() {
		setDrinkConfigurer(CoffeeConfiguration.getDrinkConfigurer());
	}

	@Override
	public int prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		if (checkForDrinkType(order)) {
			int makeOrder=super.prepareDrink(order);
			return makeOrder;
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.COFFEE + " and found " + order.getDrink());
		}
	}

	private boolean checkForDrinkType(Order order) {
		return order.getDrink() == Drink.COFFEE;
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new CoffeeBuilder();
	}

}
