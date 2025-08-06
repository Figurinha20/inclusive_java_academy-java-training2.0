package io.altar.jseproject.services;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.dbAccess.ProductDBAccess;

public class ProductService extends EntityService implements ProductDBAccess {
	static ShelfService shelfService = new ShelfService();
	
	public int addEntity(Product e) {
		//Adding the product to the shelf if needed
		if(e.getShelfIds().size() == 0) { //means the product isn't in any shelf
			return PRODUCT_DB.addEntity(e);
		}
		
		for (int shelfId : e.getShelfIds()) {
			if (!shelfExists(shelfId)) return -1; //ABORT, SHELF DOESN'T EVEN EXIST
			if (!shelfService.getEntityById(shelfId).isEmpty()) return -2; //ABORT, SHELF ISN'T EMPTY
		}
		
		int id = PRODUCT_DB.addEntity(e);
		//add the product we just created to the shelves passed on create
		for (int shelfId : e.getShelfIds()) {
			Shelf updatedShelf = shelfService.getEntityById(shelfId);
			updatedShelf.setProductId(id);
			shelfService.updateEntityOnProductCreate(updatedShelf);
		}
		return id;
	}
	
	public void updateEntity(Product e) {
		Product oldProduct = getEntityById(e.getId());
		if (!oldProduct.getShelfIds().equals(e.getShelfIds())) {
			shelfService.updateProductOnShelfs(e,
					oldProduct.getShelfIds(),
					e.getShelfIds());
		}
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
