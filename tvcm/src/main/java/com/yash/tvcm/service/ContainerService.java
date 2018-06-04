package com.yash.tvcm.service;

import java.util.List;

import com.yash.tvcm.enums.Ingredient;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;

public interface ContainerService {

	public List<Container> getAllContainers();

	public int addContainer(Container container) throws NullFieldException;

	public Container getContainerByIngredient(Ingredient ingredient) throws NullFieldException;

	public int refillContainer(Container container) throws NullFieldException;
	
	public void showContainerStatus();

}
