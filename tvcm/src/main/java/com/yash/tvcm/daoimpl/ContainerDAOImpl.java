package com.yash.tvcm.daoimpl;

import java.util.List;

import com.yash.tvcm.dao.ContainerDAO;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;
import com.yash.tvcm.util.JSONUtil;

public class ContainerDAOImpl implements ContainerDAO {

	public int save(Container container) {
		int rowsAffected = JSONUtil.saveObject(container);
		return rowsAffected;
	}

	public List<Container> retrieve() {
		List<Container> containersPresent = JSONUtil.retrieveListOfContainers();
		return containersPresent;
	}

	public int update(Container container) throws NullFieldException {
		checkForNullContainerValue(container);
		int rowsAffected = 0;
		List<Container> containers=retrieve();
		updateContainerBasedOnIngredient(container, rowsAffected, containers);
		rowsAffected=JSONUtil.updateContainer(containers);
		return rowsAffected;
	}

	private void updateContainerBasedOnIngredient(Container container, int rowsAffected, List<Container> containers) {
		if(containers!=null){
			for (Container containerToBeUpdated : containers) {
				if(containerToBeUpdated.getIngredient().compareTo(container.getIngredient())==rowsAffected){
					int indexOfContainerToBeUpdated=containers.indexOf(containerToBeUpdated);
					containerToBeUpdated=container;
					containers.set(indexOfContainerToBeUpdated, containerToBeUpdated);
				}
			}
		}
	}

	private void checkForNullContainerValue(Container container) throws NullFieldException {
		if(container==null)
			throw new NullFieldException("Container cannot be null.");
	}

}
