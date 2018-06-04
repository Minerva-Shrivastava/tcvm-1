package com.yash.tvcm.model;

import com.yash.tvcm.enums.Ingredient;

public class Container {

	private Ingredient ingredient;
	private double maxCapacity;
	private double currentCapacity;

	public Container(Ingredient ingredient, double maxCapacity, double currentCapacity) {
		super();
		this.ingredient = ingredient;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = currentCapacity;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public double getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(double maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(double currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	@Override
	public String toString() {
		return "Container [ingredient=" + ingredient + ", maxCapacity=" + maxCapacity + ", currentCapacity="
				+ currentCapacity + "]";
	}

}
