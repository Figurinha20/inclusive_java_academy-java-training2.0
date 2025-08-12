package io.altar.jseproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Shelf extends Entity_ {
	private static final long serialVersionUID = 1L;
	
	private int capacity;
	private int productId = -1;
	private double dailyPrice;

	public Shelf() {};

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
