package io.altar.jseproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class Shelf extends Entity {
	private int capacity;
	private int productId = -1;
	private double dailyPrice;

	public Shelf() {
	};

	// In case the shelf starts empty
	public Shelf(int capacity, double dailyPrice) {
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
	}

	// In case the shelf starts with a product
	public Shelf(int capacity, int productId, double dailyPrice) {
		this.productId = productId;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return (productId == -1 ? true : false);
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double price) {
		this.dailyPrice = price;
	}
}
