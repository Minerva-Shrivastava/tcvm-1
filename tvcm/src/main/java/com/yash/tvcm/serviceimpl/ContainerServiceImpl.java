package com.yash.tvcm.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.yash.tvcm.dao.ContainerDAO;
import com.yash.tvcm.enums.ContainerMaxCapacity;
import com.yash.tvcm.enums.Ingredient;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;
import com.yash.tvcm.service.ContainerService;

public class ContainerServiceImpl implements ContainerService {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ContainerDAO containerDAO;

	public ContainerServiceImpl(ContainerDAO containerDAO) {
		super();
		this.containerDAO = containerDAO;
		initializeContainers();
	}

	private void initializeContainers() {
		List<Container> containers = getAllContainers();
		if (checkForContainersAlreadyPresent(containers)) {
			for (Ingredient ingredient : Ingredient.values()) {
				for (ContainerMaxCapacity containerMaxCapacity : ContainerMaxCapacity.values()) {
					addContainerToListOfContainers(ingredient, containerMaxCapacity);
				}
			}
		}
	}

	private void addContainerToListOfContainers(Ingredient ingredient, ContainerMaxCapacity containerMaxCapacity) {
		if (ingredient.name().equalsIgnoreCase(containerMaxCapacity.name())) {
			Container container = new Container(ingredient, containerMaxCapacity.getMaxCapacity(),
					containerMaxCapacity.getMaxCapacity());
			try {
				addContainer(container);
			} catch (NullFieldException nfe) {
				logger.error(nfe.getMessage());
			}
		}
	}

	private boolean checkForContainersAlreadyPresent(List<Container> containers) {
		return containers == null;
	}

	public int addContainer(Container container) throws NullFieldException {
		checkForNullContainer(container);
		int containerAdded = containerDAO.save(container);
		return containerAdded;
	}

	private void checkForNullContainer(Container container) throws NullFieldException {
		if (container == null)
			throw new NullFieldException("Container cannot be null");
	}

	public List<Container> getAllContainers() {
		return containerDAO.retrieve();
	}

	public Container getContainerByIngredient(Ingredient ingredient) throws NullFieldException {
		checkIfIngredientIsNull(ingredient);
		Container containerToBeSearched = null;
		List<Container> containers = getAllContainers();
		for (Container container : containers) {
			if (checkWetherContainerWithGivenIngredientIsPresent(ingredient, container)) {
				containerToBeSearched = container;
			}
		}
		return containerToBeSearched;
	}

	private boolean checkWetherContainerWithGivenIngredientIsPresent(Ingredient ingredient, Container container) {
		return container.getIngredient().compareTo(ingredient) == 0;
	}

	private void checkIfIngredientIsNull(Ingredient ingredient) throws NullFieldException {
		if (ingredient == null)
			throw new NullFieldException("Ingredient is Null");
	}

	public int refillContainer(Container container) throws NullFieldException {
		checkForNullContainer(container);
		int result = containerDAO.update(container);
		return result;
	}

	public void showContainerStatus() {
		List<Container> containers = getAllContainers();
		System.out.println("Container Status");
		System.out.println("---------------------------------------------");
		for (Container container : containers) {
			System.out.println("Container:" + container.getIngredient() + "\t\tMax Capacity:" + container.getMaxCapacity()
					+ "\t\tCurrent Capacity:" + container.getCurrentCapacity());
		}
	}
}
