package io.altar.jseproject.services;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.models.entities.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;

@Transactional
@ApplicationScoped
public class ShelfService  {
	@Inject
	ShelfRepository shelfRepo;
	@Inject
	ProductService productService;

	public int addEntity(Shelf e) {
		//If we get here it means the shelf has a product
		if (e.getProduct() != null && !productService.entityExists(e.getProduct().getId())) return -1; // ABORT, PRODUCT DOESN'T EVEN EXIST

		return shelfRepo.addEntity(e);
	}

	public void updateEntity(Shelf newShelf, Shelf oldShelf) {
		shelfRepo.updateEntity(newShelf);
	}


	public Collection<Shelf> getAllEntities() {
		return shelfRepo.getAllEntities();
	}

	public Shelf getEntityById(int id) {
		return shelfRepo.getEntityById(id);
	}

	public int getSize() {
		return shelfRepo.getSize();
	}

	public Shelf deleteEntity(int id) {			
		if (!getEntityById(id).isEmpty()) {
			throw new UnsupportedOperationException("The Shelf " + id + " has a product, you need to make it empty first."); //ABORT!! SHELF ISN'T EMPTY
		}
		
		return shelfRepo.deleteEntity(id);
	}

	public boolean entityExists(int id) {
		return shelfRepo.entityExists(id);
	}

	public boolean productExists(int id) {
		return productService.entityExists(id);
	}
}