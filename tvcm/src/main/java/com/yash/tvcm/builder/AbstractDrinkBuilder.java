package com.yash.tvcm.builder;

import java.util.Map;

import com.yash.tvcm.config.AbstractDrinkConfigurer;
import com.yash.tvcm.config.IDrinkConfigurer;
import com.yash.tvcm.dao.ContainerDAO;
import com.yash.tvcm.daoimpl.ContainerDAOImpl;
import com.yash.tvcm.daoimpl.OrderDAOImpl;
import com.yash.tvcm.enums.Ingredient;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;
import com.yash.tvcm.model.Order;
import com.yash.tvcm.service.ContainerService;
import com.yash.tvcm.service.OrderService;
import com.yash.tvcm.serviceimpl.ContainerServiceImpl;
import com.yash.tvcm.serviceimpl.OrderServiceImpl;

public abstract class AbstractDrinkBuilder implements IDrinkBuilder {

	private IDrinkConfigurer drinkConfigurer;
	private ContainerDAO containerDAO = new ContainerDAOImpl();
	private ContainerService containerService = new ContainerServiceImpl(this.containerDAO);
	private OrderService orderService = new OrderServiceImpl(new OrderDAOImpl());

	public void setDrinkConfigurer(IDrinkConfigurer drinkConfigurer) {
		this.drinkConfigurer = drinkConfigurer;
	}

	public void setContainerService(ContainerServiceImpl containerServiceImpl) {
		this.containerService = containerServiceImpl;
	}

	public int prepareDrink(Order order) throws ContainerUnderflowException, NullFieldException {
		checkUnderFlow(order);
		updateContainers(order);
		int makeOrder = orderService.makeOrder(order);
		return makeOrder;
	}

	private void checkUnderFlow(Order order) throws ContainerUnderflowException, NullFieldException {
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;

		Map<Ingredient, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<Ingredient, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();

		for (Map.Entry<Ingredient, Double> entry : consumption.entrySet()) {

			double qtyWasted = wastage.get(entry.getKey());
			double qtyConsumed = entry.getValue();
			double qtyAvailableInContainer = containerService.getContainerByIngredient(entry.getKey())
					.getCurrentCapacity();
			int noOfCups = order.getQuantity();

			if (isUnderFlowCondition(qtyWasted, qtyConsumed, qtyAvailableInContainer, noOfCups)) {
				throw new ContainerUnderflowException(entry.getKey() + " Insufficient");
			}
		}
	}

	private boolean isUnderFlowCondition(double qtyWasted, double qtyConsumed, double qtyAvailableInContainer,
			int noOfCups) {
		return (noOfCups * (qtyConsumed + qtyWasted)) > qtyAvailableInContainer;
	}

	public void updateContainers(Order order) throws NullFieldException {

		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<Ingredient, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<Ingredient, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();

		for (Map.Entry<Ingredient, Double> entry : consumption.entrySet()) {

			Container container = containerService.getContainerByIngredient(entry.getKey());

			double qtyWasted = wastage.get(entry.getKey());
			double qtyConsumed = entry.getValue();
			double qtyAvailableInContainer = container.getCurrentCapacity();
			int noOfCups = order.getQuantity();

			container.setCurrentCapacity(qtyAvailableInContainer - (noOfCups * (qtyConsumed + qtyWasted)));
			containerDAO.update(container);
		}
	}
}
