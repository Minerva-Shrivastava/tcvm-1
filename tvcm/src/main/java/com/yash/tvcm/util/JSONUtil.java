package com.yash.tvcm.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yash.tvcm.model.Container;
import com.yash.tvcm.model.Order;

public class JSONUtil {

	private static Logger logger = Logger.getLogger(JSONUtil.class.getName());
	private static final File orderFile = new File("src/main/resources/files/jsonFiles/order.json");
	private static final File containerFile = new File("src/main/resources/files/jsonFiles/container.json");

	private JSONUtil() {

	}

	static {
		createFiles();
	}

	private static void createFiles() {
		try {
			checkForExistenceOfOrderFileAndCreateIfNotExists();
			checkForExistenceOfContainerFileAndCreateIfNotExists();
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}
	}

	private static void checkForExistenceOfContainerFileAndCreateIfNotExists() throws IOException {
		if (!containerFile.exists())
			containerFile.createNewFile();
	}

	private static void checkForExistenceOfOrderFileAndCreateIfNotExists() throws IOException {
		if (!orderFile.exists())
			orderFile.createNewFile();
	}

	public static int saveObject(Object object) {
		int rowsAffected = 0;
		try {
			rowsAffected = saveOrder(object, rowsAffected);
			rowsAffected = saveContainer(object, rowsAffected);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
			return rowsAffected;
		}
		return rowsAffected;
	}

	private static int saveContainer(Object object, int rowsAffected) throws IOException {
		BufferedWriter objectToFileWriter;
		if (checkWetherObjectIsInstanceOfContainer(object)) {
			List<Container> containers = checkWetherContainersExistsOrNot();
			objectToFileWriter = addNewContainerToExistingContainersList(object, containers);
			saveContainersListToJsonFile(objectToFileWriter, containers);
			rowsAffected = 1;
			return rowsAffected;
		}
		return rowsAffected;
	}

	private static void saveContainersListToJsonFile(BufferedWriter objectToFileWriter, List<Container> containers)
			throws IOException {
		Gson gson;
		gson = new Gson();
		String containerJson = gson.toJson(containers);
		objectToFileWriter.write(containerJson);
		objectToFileWriter.flush();
		objectToFileWriter.close();
	}

	private static BufferedWriter addNewContainerToExistingContainersList(Object object, List<Container> containers)
			throws IOException {
		BufferedWriter objectToFileWriter;
		objectToFileWriter = new BufferedWriter(new FileWriter(containerFile));
		Container container = (Container) object;
		containers.add(container);
		return objectToFileWriter;
	}

	private static List<Container> checkWetherContainersExistsOrNot() {
		List<Container> containers = retrieveListOfContainers();
		if (containers == null)
			containers = new ArrayList<Container>();
		return containers;
	}

	private static boolean checkWetherObjectIsInstanceOfContainer(Object object) {
		return object instanceof Container;
	}

	private static int saveOrder(Object object, int rowsAffected) throws IOException {
		BufferedWriter objectToFileWriter;
		if (checkWetherObjectIsInstanceOfOrder(object)) {
			List<Order> orders = checkWetherOrdersExistsOrNot();
			objectToFileWriter = addNewOrderToExistingOrdersList(object, orders);
			saveOrdersListToJsonFile(objectToFileWriter, orders);
			rowsAffected = 1;
			return rowsAffected;
		}
		return rowsAffected;
	}

	private static void saveOrdersListToJsonFile(BufferedWriter objectToFileWriter, List<Order> orders)
			throws IOException {
		Gson gson;
		gson = new Gson();
		String orderJson = gson.toJson(orders);
		objectToFileWriter.write(orderJson);
		objectToFileWriter.flush();
		objectToFileWriter.close();
	}

	private static BufferedWriter addNewOrderToExistingOrdersList(Object object, List<Order> orders)
			throws IOException {
		BufferedWriter objectToFileWriter;
		objectToFileWriter = new BufferedWriter(new FileWriter(orderFile));
		Order order = (Order) object;
		orders.add(order);
		return objectToFileWriter;
	}

	private static List<Order> checkWetherOrdersExistsOrNot() {
		List<Order> orders = retrieveListOfOrders();
		if (orders == null)
			orders = new ArrayList<Order>();
		return orders;
	}

	private static boolean checkWetherObjectIsInstanceOfOrder(Object object) {
		return object instanceof Order;
	}

	public static List<Order> retrieveListOfOrders() {
		List<Order> orders = new ArrayList<Order>();
		try {
			BufferedReader orderBufferedReader = new BufferedReader(new FileReader(orderFile));
			Gson orderGson = new Gson();
			Type orderType = new TypeToken<ArrayList<Order>>() {
			}.getType();
			orders = orderGson.fromJson(orderBufferedReader, orderType);
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage());
		}
		return orders;
	}

	public static List<Container> retrieveListOfContainers() {
		List<Container> containers = new ArrayList<Container>();
		try {
			BufferedReader orderBufferedReader = new BufferedReader(new FileReader(containerFile));
			Gson containerGson = new Gson();
			Type containerType = new TypeToken<ArrayList<Container>>() {
			}.getType();
			containers = containerGson.fromJson(orderBufferedReader, containerType);
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage());
		}
		return containers;
	}

	public static int updateContainer(List<Container> containers) {
		int rowsAffected = 0;
		BufferedWriter objectToFileWriter = null;
		try {
			objectToFileWriter = new BufferedWriter(new FileWriter(containerFile));
			saveUpdatedContainerList(containers, objectToFileWriter);
			rowsAffected = 1;
			return rowsAffected;
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
			return rowsAffected;
		}
	}

	private static void saveUpdatedContainerList(List<Container> containers, BufferedWriter objectToFileWriter)
			throws IOException {
		Gson gson = new Gson();
		String containerJson = gson.toJson(containers);
		objectToFileWriter.write(containerJson);
		objectToFileWriter.flush();
		objectToFileWriter.close();
	}
}
