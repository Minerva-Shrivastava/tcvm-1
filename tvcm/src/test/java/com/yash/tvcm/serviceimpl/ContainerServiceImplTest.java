package com.yash.tvcm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tvcm.dao.ContainerDAO;
import com.yash.tvcm.enums.ContainerMaxCapacity;
import com.yash.tvcm.enums.Ingredient;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;
import com.yash.tvcm.service.ContainerService;

public class ContainerServiceImplTest {

	private ContainerService containerService;
	private ContainerDAO containerDAO;

	@Before
	public void setUp() throws Exception {
		this.containerDAO = mock(ContainerDAO.class);
		this.containerService = new ContainerServiceImpl(containerDAO);
	}

	@Test(expected = NullFieldException.class)
	public void addContainer_ContainerObjectIsNull_ShouldNullFieldException() throws NullFieldException {
		Container container = null;
		containerService.addContainer(container);
	}

	@Test
	public void addContainer_ContainerObjectGiven_ShouldReturnOneWhenContainerAdded() throws NullFieldException {
		Ingredient ingredient = Ingredient.SUGAR;
		double maxCapacity = ContainerMaxCapacity.SUGAR.getMaxCapacity();
		double currentCapacity = ContainerMaxCapacity.SUGAR.getMaxCapacity();
		Container container = new Container(ingredient, maxCapacity, currentCapacity);
		when(containerDAO.save(container)).thenReturn(1);
		int containerAdded = containerService.addContainer(container);
		assertEquals(1, containerAdded);
	}

	@Test
	public void getAllContainers_ShouldReturnListOfAllContainers() {
		List<Container> containers = Arrays.asList(
				new Container(Ingredient.COFFEE, ContainerMaxCapacity.COFFEE.getMaxCapacity(),
						ContainerMaxCapacity.COFFEE.getMaxCapacity()),
				new Container(Ingredient.MILK, ContainerMaxCapacity.MILK.getMaxCapacity(),
						ContainerMaxCapacity.MILK.getMaxCapacity()));
		when(containerDAO.retrieve()).thenReturn(containers);
		assertEquals(2, containerService.getAllContainers().size());
	}

	@Test(expected = NullFieldException.class)
	public void getContainerByIngredient_IngredientInputIsNull_ThrowNullFieldException() throws NullFieldException {
		Ingredient ingredient = null;
		Container containerToBeFound = containerService.getContainerByIngredient(ingredient);
	}

	@Test
	public void getContainerByIngredient_IngredientInputGiven_ShouldReturnContainerOfThatIngredient()
			throws NullFieldException {
		List<Container> containers = Arrays.asList(
				new Container(Ingredient.COFFEE, ContainerMaxCapacity.COFFEE.getMaxCapacity(),
						ContainerMaxCapacity.COFFEE.getMaxCapacity()),
				new Container(Ingredient.MILK, ContainerMaxCapacity.MILK.getMaxCapacity(),
						ContainerMaxCapacity.MILK.getMaxCapacity()));
		when(containerDAO.retrieve()).thenReturn(containers);
		Ingredient milk = Ingredient.MILK;
		Container containerToBeFound = containerService.getContainerByIngredient(milk);
		assertEquals(containers.get(1), containerToBeFound);
	}

	@Test(expected = NullFieldException.class)
	public void refillContainer_ContainerObjectIsNull_ThrowNullFieldException() throws NullFieldException {
		Container container = null;
		int result = containerService.refillContainer(container);
	}

	@Test
	public void refillContainer_ContainerObjectGiven_ShouldReturnOneWhenRefillSuccessful() throws NullFieldException {
		Container container = new Container(Ingredient.MILK, ContainerMaxCapacity.MILK.getMaxCapacity(),
				ContainerMaxCapacity.MILK.getMaxCapacity());
		when(containerDAO.update(container)).thenReturn(1);
		int result = containerService.refillContainer(container);
		assertEquals(1, result);
	}
	
	@Test
	public void refillContainer_ContainerObjectGiven_ShouldReturnZeroWhenRefillUnsuccessful() throws NullFieldException {
		Container container = new Container(Ingredient.MILK, ContainerMaxCapacity.MILK.getMaxCapacity(),
				ContainerMaxCapacity.MILK.getMaxCapacity());
		when(containerDAO.update(container)).thenReturn(0);
		int result = containerService.refillContainer(container);
		assertEquals(0, result);
	}

}
