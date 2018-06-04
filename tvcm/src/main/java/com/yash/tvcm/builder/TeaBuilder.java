package com.yash.tvcm.builder;

import com.yash.tvcm.config.TeaConfiguration;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;

public class TeaBuilder extends AbstractDrinkBuilder {

	public TeaBuilder() {
		setDrinkConfigurer(TeaConfiguration.getDrinkConfigurer());
	}

	@Override
	public int prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		if (checkForDrinkTypeTea(order)) {
			int makeOrder=super.prepareDrink(order);
			return makeOrder;
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.TEA + " and found " + order.getDrink());
		}
	}

	private boolean checkForDrinkTypeTea(Order order) {
		return order.getDrink() == Drink.TEA;
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new TeaBuilder();
	}

}
