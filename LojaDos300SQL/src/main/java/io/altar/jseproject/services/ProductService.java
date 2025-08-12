package io.altar.jseproject.services;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;

@Transactional
@ApplicationScoped
public class ProductService {
	@Inject
	ProductRepository productRepo;
	@Inject
	ShelfService shelfService;
	
	public int addEntity(Product e) {
		//Adding the product to the shelf if needed
		if(e.getShelfIds().size() == 0) { //means the product isn't in any shelf
			return productRepo.addEntity(e);
		}
		
		for (int shelfId : e.getShelfIds()) {
			if (!shelfExists(shelfId)) return -1; //ABORT, SHELF DOESN'T EVEN EXIST
			if (!shelfService.getEntityById(shelfId).isEmpty()) return -2; //ABORT, SHELF ISN'T EMPTY
		}
		
		int id = productRepo.addEntity(e);
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
		productRepo.updateEntity(e);
	}
		
	public Collection<Product> getAllEntities() {
		return productRepo.getAllEntities();
	}
	
	public Product getEntityById(int id) {
		return productRepo.getEntityById(id);
	}
	
	public int getSize() {
		return productRepo.getSize();
	}
	
	public Product deleteEntity(int id) {
		if(getEntityById(id).getShelfIds().size() > 0) return null; //ABORT! PRODUCT IS IN SHELFS
		
		return productRepo.deleteEntity(id);
	}
	
	public boolean entityExists(int id) {
		return productRepo.entityExists(id);
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
