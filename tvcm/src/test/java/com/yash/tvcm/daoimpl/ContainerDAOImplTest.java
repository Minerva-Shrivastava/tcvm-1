package com.yash.tvcm.daoimpl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tvcm.dao.ContainerDAO;
import com.yash.tvcm.enums.ContainerMaxCapacity;
import com.yash.tvcm.enums.Ingredient;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;

public class ContainerDAOImplTest {

	private ContainerDAO containerDAO;

	@Before
	public void setUp() throws Exception {
		this.containerDAO = new ContainerDAOImpl();
	}

	@Test
	public void save_ContainerObjectGiven_ShouldReturnOneWhenContainerIsSavedToFile() {
		Ingredient ingredient = Ingredient.WATER;
		Container container = new Container(ingredient, ContainerMaxCapacity.WATER.getMaxCapacity(),
				ContainerMaxCapacity.WATER.getMaxCapacity());
		int rowsAffected = containerDAO.save(container);
		assertEquals(1, rowsAffected);
	}

	@Test
	public void retrieve_ShouldReturnListOfContainers() {
		List<Container> containers = containerDAO.retrieve();
		assertEquals(5, containers.size());
	}

	@Test(expected = NullFieldException.class)
	public void update_ContainerObjectIsNull_ThrowNullFieldException() throws NullFieldException {
		Container container = null;
		containerDAO.update(container);
	}

	@Test
	public void update_ContainerObjectGiven_ShouldReturnOneWhenUpdateSuccessful() throws NullFieldException {
		Container container = new Container(Ingredient.COFFEE, ContainerMaxCapacity.COFFEE.getMaxCapacity(), 1400);
		int rowsAffected = containerDAO.update(container);
		assertEquals(1, rowsAffected);
	}

}
