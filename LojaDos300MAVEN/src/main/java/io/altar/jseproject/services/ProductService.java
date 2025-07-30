package io.altar.jseproject.services;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.dbAccess.ProductDBAccess;

public class ProductService extends EntityService implements ProductDBAccess {
	static ShelfService shelfService = new ShelfService();
	
	public int addEntity(Product e) {
		return PRODUCT_DB.addEntity(e);
	}
	
	public void updateEntity(Product e) {
		PRODUCT_DB.updateEntity(e);
	}
	
	public Collection<Product> getAllEntities() {
		return PRODUCT_DB.getAllEntities();
	}
	
	public Product getEntityById(int id) {
		return PRODUCT_DB.getEntityById(id);
	}
	
	public int getSize() {
		return PRODUCT_DB.getSize();
	}
	
	public Product deleteEntity(int id) {
		return PRODUCT_DB.deleteEntity(id);
	}
	
	public boolean entityExists(int id) {
		return PRODUCT_DB.entityExists(id);
	}
	
	public boolean shelfExists(int id) {
		return shelfService.entityExists(id);
	}
	
	protected void addShelfToProduct(Shelf newShelf) {
		Product currentProduct = getEntityById(newShelf.getProductId());
		currentProduct.addShelfId(newShelf.getId());
		updateEntity(currentProduct);
	}
}
