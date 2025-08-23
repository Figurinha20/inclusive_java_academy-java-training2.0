package io.altar.jseproject.models.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({ 
	@NamedQuery(name = Shelf.GET_ALL_SHELVES, query = "SELECT s FROM Shelf s"),
	@NamedQuery(name = Shelf.GET_SHELF_COUNT, query = "SELECT COUNT(s) FROM Shelf s") 
	})
public class Shelf extends Entity_ {
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_SHELVES = "getAllShelves";
	public static final String GET_SHELF_COUNT = "getShelfCount";
	
	private int capacity;	
	@OneToOne
	private Product product;
	private double dailyPrice;

	public Shelf() {};

	@JsonIgnore
	public boolean isEmpty() {
		return (product == null ? true : false);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
