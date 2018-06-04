package com.yash.tvcm.dao;

import java.util.List;

import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;

public interface ContainerDAO {

	public int save(Container container);

	public List<Container> retrieve();

	public int update(Container container) throws NullFieldException;

}
