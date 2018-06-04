package com.yash.tvcm.builder;

import com.yash.tvcm.config.BlackTeaConfiguration;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;

public class BlackTeaBuilder extends AbstractDrinkBuilder {

	public BlackTeaBuilder() {
		setDrinkConfigurer(BlackTeaConfiguration.getDrinkConfigurer());
	}

	@Override
	public int prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		if (checkForDrinkType(order)) {
			int makeOrder=super.prepareDrink(order);
			return makeOrder;
		} else {
			throw new IllegalArgumentException(
					"Wrong Drink Type, required " + Drink.BLACK_TEA + " and found " + order.getDrink());
		}
	}

	private boolean checkForDrinkType(Order order) {
		return order.getDrink() == Drink.BLACK_TEA;
	}

	public static IDrinkBuilder getDrinkBuilder() {
		return new BlackTeaBuilder();
	}

}
