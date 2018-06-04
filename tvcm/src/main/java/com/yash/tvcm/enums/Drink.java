package com.yash.tvcm.enums;

public enum Drink {

	TEA(10), COFFEE(15), BLACK_TEA(15), BLACK_COFFEE(10);
	private int cost;

	private Drink(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
}
