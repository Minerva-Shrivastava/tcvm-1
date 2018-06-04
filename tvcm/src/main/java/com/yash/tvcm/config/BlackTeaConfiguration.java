package com.yash.tvcm.config;

import java.util.HashMap;
import java.util.Map;

import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.enums.Ingredient;

public class BlackTeaConfiguration extends AbstractDrinkConfigurer {

	private static IDrinkConfigurer drinkConfigurer;

	public static final double WATER_CONSUMPTION = 100;
	public static final double SUGAR_CONSUMPTION = 15;
	public static final double TEA_CONSUMPTION = 3;

	public static final double WATER_WASTAGE = 12;
	public static final double SUGAR_WASTAGE = 2;
	public static final double TEA_WASTAGE = 0;

	static {
		drinkConfigurer = new BlackTeaConfiguration();
	}

	public static IDrinkConfigurer getDrinkConfigurer() {
		return drinkConfigurer;
	}

	public void configIngredientConsumption() {
		Map<Ingredient, Double> ingredientsConsumption = new HashMap<Ingredient, Double>();

		ingredientsConsumption.put(Ingredient.TEA, TEA_CONSUMPTION);
		ingredientsConsumption.put(Ingredient.WATER, WATER_CONSUMPTION);
		ingredientsConsumption.put(Ingredient.SUGAR, SUGAR_CONSUMPTION);

		setIngredientConsumption(ingredientsConsumption);
	}

	public void configIngredientWastage() {
		Map<Ingredient, Double> ingredientsWastage = new HashMap<Ingredient, Double>();

		ingredientsWastage.put(Ingredient.TEA, TEA_WASTAGE);
		ingredientsWastage.put(Ingredient.WATER, WATER_WASTAGE);
		ingredientsWastage.put(Ingredient.SUGAR, SUGAR_WASTAGE);

		setIngredientWastage(ingredientsWastage);
	}

	public void configDrinkType() {
		setDrinkType(Drink.BLACK_TEA);
	}

}
