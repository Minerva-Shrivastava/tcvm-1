package com.yash.tvcm.model;

import java.util.Date;

import com.yash.tvcm.enums.Drink;

public class Order {

	private Integer quantity;
	private Drink drink;
	private Date date;
	private int cost;

	public Order(Integer quantity, Drink drink, int cost) {
		this.quantity = quantity;
		this.drink = drink;
		this.cost = cost;
		this.date = new Date();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public Date getDate() {
		return date;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Order [quantity=" + quantity + ", drink=" + drink + ", date=" + date + ", cost=" + cost + "]";
	}

}
