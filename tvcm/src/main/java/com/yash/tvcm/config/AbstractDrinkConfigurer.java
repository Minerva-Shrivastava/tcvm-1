package com.yash.tvcm.config;

import java.util.Map;

import com.yash.tvcm.enums.Drink;
import com.yash.tvcm.enums.Ingredient;

public abstract class AbstractDrinkConfigurer implements IDrinkConfigurer {

	private Map<Ingredient, Double> ingredientConsumption;

	private Map<Ingredient, Double> ingredientWastage;

	private double drinkRate;

	private Drink drinkType;

	public AbstractDrinkConfigurer() {
		initDrinkConfig();
	}

	private void initDrinkConfig() {

		configIngredientConsumption();

		configIngredientWastage();

		configDrinkType();

	}

	public Map<Ingredient, Double> getIngredientConsumption() {
		return ingredientConsumption;
	}

	public void setIngredientConsumption(Map<Ingredient, Double> ingredientConsumption) {
		this.ingredientConsumption = ingredientConsumption;
	}

	public Map<Ingredient, Double> getIngredientWastage() {
		return ingredientWastage;
	}

	public void setIngredientWastage(Map<Ingredient, Double> ingredientWastage) {
		this.ingredientWastage = ingredientWastage;
	}

	public double getDrinkRate() {
		return drinkRate;
	}

	public void setDrinkRate(double drinkRate) {
		this.drinkRate = drinkRate;
	}

	public Drink getDrinkType() {
		return drinkType;
	}

	public void setDrinkType(Drink drinkType) {
		this.drinkType = drinkType;
	}

	public static IDrinkConfigurer getDrinkConfigurer() {
		return null;
	}

}
