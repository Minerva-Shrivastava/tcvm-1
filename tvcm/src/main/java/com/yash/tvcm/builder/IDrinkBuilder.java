package com.yash.tvcm.builder;

import com.yash.tvcm.config.IDrinkConfigurer;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Order;

public interface IDrinkBuilder {

	void setDrinkConfigurer(IDrinkConfigurer drinkConfigurer);

	int prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException;

	void updateContainers(Order order) throws NullFieldException;
}
