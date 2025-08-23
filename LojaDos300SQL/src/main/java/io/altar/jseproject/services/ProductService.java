package io.altar.jseproject.services;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.models.entities.Product;
import io.altar.jseproject.models.entities.Shelf;
import io.altar.jseproject.repositories.ProductRepository;

@Transactional
@ApplicationScoped
public class ProductService {
	@Inject
	ProductRepository productRepo;
	@Inject
	ShelfService shelfService;
	
	public int addEntity(Product e) {
		for (int shelfId : e.getShelves().stream().map(Shelf::getId).collect(Collectors.toList())) {
			if (!shelfExists(shelfId)) return -1; //ABORT, SHELF DOESN'T EVEN EXIST
			if (!shelfService.getEntityById(shelfId).isEmpty()) return -2; //ABORT, SHELF ISN'T EMPTY
		}
		return productRepo.addEntity(e);
	}
	
	public void updateEntity(Product e) {
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
		if(getEntityById(id).getShelves().size() > 0) return null; //ABORT! PRODUCT IS IN SHELFS
		
		return productRepo.deleteEntity(id);
	}
	
	public boolean entityExists(int id) {
		return productRepo.entityExists(id);
	}
	
	public boolean shelfExists(int id) {
		return shelfService.entityExists(id);
	}
}
