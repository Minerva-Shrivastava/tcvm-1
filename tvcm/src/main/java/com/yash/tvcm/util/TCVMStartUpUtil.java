package com.yash.tvcm.util;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.yash.tvcm.builder.BlackCoffeeBuilder;
import com.yash.tvcm.builder.BlackTeaBuilder;
import com.yash.tvcm.builder.CoffeeBuilder;
import com.yash.tvcm.builder.TeaBuilder;
import com.yash.tvcm.daoimpl.ContainerDAOImpl;
import com.yash.tvcm.daoimpl.OrderDAOImpl;
import com.yash.tvcm.enums.ContainerMaxCapacity;
import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.enums.Ingredient;
import com.yash.tvcm.exception.ContainerUnderflowException;
import com.yash.tvcm.exception.NullFieldException;
import com.yash.tvcm.model.Container;
import com.yash.tvcm.model.Order;
import com.yash.tvcm.service.ContainerService;
import com.yash.tvcm.service.OrderService;
import com.yash.tvcm.serviceimpl.ContainerServiceImpl;
import com.yash.tvcm.serviceimpl.OrderServiceImpl;

public class TCVMStartUpUtil {

	private static Logger logger = Logger.getLogger(TCVMStartUpUtil.class);
	private static ContainerService containerService = new ContainerServiceImpl(new ContainerDAOImpl());
	private static OrderService orderService = new OrderServiceImpl(new OrderDAOImpl());

	private TCVMStartUpUtil() {
	}

	public static void start() {
		int choice = 0;
		String cont = null;
		do {
			Scanner userInput = new Scanner(System.in);
			try {
				TCVMMenuUtil.getMenu();
				choice = userInput.nextInt();
				try {
					switch (choice) {
					case 1:
						makeCoffee(userInput);
						break;
					case 2:
						makeTea(userInput);
						break;
					case 3:
						makeBlackCoffee(userInput);
						break;
					case 4:
						makeBlackTea(userInput);
						break;
					case 5:
						System.out.println(
								"Select Container to be refilled: 1.COFFEE \t2.TEA \t3.WATER \t4.SUGAR \t5.MILK");
						int choiceForRefilling = userInput.nextInt();
						checkWhichContainerIsToBeFilled(choiceForRefilling);
						break;
					case 6:
						Map<Drink, Integer> drinkAndItsTotalCost = orderService.checkTotalSaleDrinkWise();
						Set<Drink> drinks = drinkAndItsTotalCost.keySet();
						System.out.println("Total sale of each drink:");
						System.out.println("-------------------------------");
						for (Drink drink : drinks) {
							System.out.println(drink + "\t:\t" + drinkAndItsTotalCost.get(drink));
						}
						break;
					case 7:
						containerService.showContainerStatus();
						break;
					case 8:
						System.exit(0);
						break;
					default:
						logger.error("Invalid choice!");
						break;
					}
				} catch (ContainerUnderflowException cue) {
					logger.error(cue.getMessage());
				} catch (NullFieldException nfe) {
					logger.error(nfe.getMessage());
				}
				System.out.println("Press 'Y' to continue..");
				cont = userInput.next();
			} catch (FileNotFoundException fnfe) {
				logger.error(fnfe.getMessage());
			}
		} while (cont.equalsIgnoreCase("y"));

	}

	private static void checkWhichContainerIsToBeFilled(int choiceForRefilling) throws NullFieldException {
		if (choiceForRefilling == 1) {
			refillCoffeeContainer();
		} else if (choiceForRefilling == 2) {
			refillTeaContainer();
		} else if (choiceForRefilling == 3) {
			refillWaterContainer();
		} else if (choiceForRefilling == 4) {
			refillSugarContainer();
		} else if (choiceForRefilling == 5) {
			refillMilkContainer();
		} else {
			logger.error("Invalid choice!");
		}
	}

	private static void refillMilkContainer() throws NullFieldException {
		int result;
		Container container = new Container(Ingredient.MILK, ContainerMaxCapacity.MILK.getMaxCapacity(),
				ContainerMaxCapacity.MILK.getMaxCapacity());
		result = containerService.refillContainer(container);
		if (result == 1)
			System.out.println("Container refilled successfully");
	}

	private static void refillSugarContainer() throws NullFieldException {
		int result;
		Container container = new Container(Ingredient.SUGAR, ContainerMaxCapacity.SUGAR.getMaxCapacity(),
				ContainerMaxCapacity.SUGAR.getMaxCapacity());
		result = containerService.refillContainer(container);
		if (result == 1)
			System.out.println("Container refilled successfully");
	}

	private static void refillWaterContainer() throws NullFieldException {
		int result;
		Container container = new Container(Ingredient.WATER, ContainerMaxCapacity.WATER.getMaxCapacity(),
				ContainerMaxCapacity.WATER.getMaxCapacity());
		result = containerService.refillContainer(container);
		if (result == 1)
			System.out.println("Container refilled successfully");
	}

	private static void refillTeaContainer() throws NullFieldException {
		int result;
		Container container = new Container(Ingredient.TEA, ContainerMaxCapacity.TEA.getMaxCapacity(),
				ContainerMaxCapacity.TEA.getMaxCapacity());
		result = containerService.refillContainer(container);
		if (result == 1)
			System.out.println("Container refilled successfully");
	}

	private static void refillCoffeeContainer() throws NullFieldException {
		int result;
		Container container = new Container(Ingredient.COFFEE, ContainerMaxCapacity.COFFEE.getMaxCapacity(),
				ContainerMaxCapacity.COFFEE.getMaxCapacity());
		result = containerService.refillContainer(container);
		if (result == 1)
			System.out.println("Container refilled successfully");
	}

	private static void makeBlackTea(Scanner userInput) throws ContainerUnderflowException, NullFieldException {
		int quantity;
		int cost;
		Order order;
		System.out.println("Enter number of cups:");
		quantity = userInput.nextInt();
		BlackTeaBuilder blackTeaBuilder = (BlackTeaBuilder) BlackTeaBuilder.getDrinkBuilder();
		cost = quantity * Drink.BLACK_TEA.getCost();
		order = new Order(quantity, Drink.BLACK_TEA, cost);
		int prepareDrinkResult = blackTeaBuilder.prepareDrink(order);
		if (prepareDrinkResult == 1)
			System.out.println("Black Tea prepared");
	}

	private static void makeBlackCoffee(Scanner userInput) throws ContainerUnderflowException, NullFieldException {
		int quantity;
		int cost;
		Order order;
		System.out.println("Enter number of cups:");
		quantity = userInput.nextInt();
		BlackCoffeeBuilder blackCoffeeBuilder = (BlackCoffeeBuilder) BlackCoffeeBuilder.getDrinkBuilder();
		cost = quantity * Drink.BLACK_COFFEE.getCost();
		order = new Order(quantity, Drink.BLACK_COFFEE, cost);
		int prepareDrinkResult = blackCoffeeBuilder.prepareDrink(order);
		if (prepareDrinkResult == 1)
			System.out.println("Black Coffee prepared");
	}

	private static void makeTea(Scanner userInput) throws ContainerUnderflowException, NullFieldException {
		int quantity;
		int cost;
		Order order;
		System.out.println("Enter number of cups:");
		quantity = userInput.nextInt();
		TeaBuilder teaBuilder = (TeaBuilder) TeaBuilder.getDrinkBuilder();
		cost = quantity * Drink.TEA.getCost();
		order = new Order(quantity, Drink.TEA, cost);
		int prepareDrinkResult = teaBuilder.prepareDrink(order);
		if (prepareDrinkResult == 1)
			System.out.println("Tea prepared");
	}

	private static void makeCoffee(Scanner userInput) throws ContainerUnderflowException, NullFieldException {
		int quantity;
		int cost;
		Order order;
		System.out.println("Enter number of cups:");
		quantity = userInput.nextInt();
		CoffeeBuilder coffeeBuilder = (CoffeeBuilder) CoffeeBuilder.getDrinkBuilder();
		cost = quantity * Drink.COFFEE.getCost();
		order = new Order(quantity, Drink.COFFEE, cost);
		int prepareDrinkResult = coffeeBuilder.prepareDrink(order);
		if (prepareDrinkResult == 1)
			System.out.println("Coffee prepared");
	}
}
