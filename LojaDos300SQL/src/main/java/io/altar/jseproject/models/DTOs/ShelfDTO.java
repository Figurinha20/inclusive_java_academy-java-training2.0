package io.altar.jseproject.models.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShelfDTO extends EntityDTO {
	private int capacity;
	private int productId = -1;
	private double dailyPrice; 
	
	public ShelfDTO() {}

	public ShelfDTO(int id, int capacity, double dailyPrice, int productId) {
		this.id = id;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.productId = productId;
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return (productId == -1 ? true : false);
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

}