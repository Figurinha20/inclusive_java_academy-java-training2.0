package io.altar.jseproject.model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.mail.Store;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name = Product.GET_ALL_PRODUCTS, query = "SELECT p FROM Product p"),
	@NamedQuery(name = Product.GET_PRODUCT_COUNT, query = "SELECT COUNT(p) FROM Product p")
	})
public class Product extends Entity_ {
	private static final long serialVersionUID = 1L;
	
	public static final String GET_ALL_PRODUCTS = "getAllProducts";
	public static final String GET_PRODUCT_COUNT = "getProductCount";

	private String name;
	private ArrayList<Integer> shelfIds = new ArrayList<Integer>();
	private double pvp;
	private double discount;
	private double iva;

	public Product() {}

	@JsonIgnore
	public String getRealPrice() {
		double realPrice = (this.pvp * (1 + this.iva / 100)) * (1 - (this.discount / 100));
		return new DecimalFormat("#.##").format(realPrice);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getShelfIds() {
		return shelfIds;
	}

	// method to add a single shelfId
	public void addShelfId(int shelfId) {
		this.shelfIds.add(shelfId);
	}

	public void setShelfIds(ArrayList<Integer> shelfIds) {
		this.shelfIds = shelfIds;
	}

	// remove a single shelf Id from the array
	public boolean removeShelfId(int id) {
		return shelfIds.removeIf(idToRemove -> idToRemove == id);
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}
}
