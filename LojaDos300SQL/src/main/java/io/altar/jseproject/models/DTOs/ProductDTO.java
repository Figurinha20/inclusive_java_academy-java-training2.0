 package io.altar.jseproject.models.DTOs;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO extends EntityDTO {
	private String name;
	private List<Integer> shelfIds = new ArrayList<Integer>();
	private double pvp;
	private double discount;
	private double iva;
	
	public ProductDTO() {};
	
	public ProductDTO(String name, int id, List<Integer> shelfIds, double discount, double iva, double pvp) {
		this.name = name;
		this.id = id;
		this.shelfIds = shelfIds;
		this.discount = discount;
		this.iva = iva;
		this.pvp = pvp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getShelfIds() {
		return shelfIds;
	}

	public void setShelfIds(List<Integer> shelfIds) {
		this.shelfIds = shelfIds;
	}
	
	// remove a single shelf rom the array
	public boolean removeShelf(int shelfId) {
		return shelfIds.removeIf(shelfIdToRemove -> shelfIdToRemove == shelfId);
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
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
}
