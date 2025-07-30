package io.altar.jseproject.model;

import java.text.DecimalFormat;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Product extends Entity{
	private String name;
	private ArrayList<Integer> shelfIds = new ArrayList<Integer>();
	private double pvp;
	private double discount;
	private double iva;
	
	// If when you create the product you don't want to add it to any shelves
	public Product(String name, double pvp, double discount, double iva) {
		this.name = name;
		this.pvp = pvp;
		this.discount = discount;
		this.iva = iva;
	}
	
	// If when you create the product you want to add it to shelves
	public Product(String name, ArrayList<Integer> shelfIds, double pvp, double discount, double iva) {
		this.name = name;
		this.shelfIds = shelfIds;
		this.pvp = pvp;
		this.discount = discount;
		this.iva = iva;
	}
	
	public String getRealPrice() {
		double realPrice = (this.pvp*(1 + this.iva/100)) * (1 - (this.discount/100));
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
	
	//method to add a single shelfId
	public void addShelfId(int shelfId) {
		this.shelfIds.add(shelfId);
	}
	
	public void setShelfIds(ArrayList<Integer> shelfIds) {
		this.shelfIds = shelfIds;
	}
	
	//remove a single shelf Id from the array
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
