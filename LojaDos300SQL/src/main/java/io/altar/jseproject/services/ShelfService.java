package io.altar.jseproject.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;

@ApplicationScoped
public class ShelfService extends EntityService  {
	@Inject
	ShelfRepository SHELF_DB;
	@Inject
	ProductService productService;

	public int addEntity(Shelf e) {
		// Adding the shelf to the product if needed
		if (e.getProductId() == -1) { // means the shelf doesn't have a product
			return SHELF_DB.addEntity(e); //let's just create the shelf
		}
		
		//If we get here it means the shelf has a product
		if (!productService.entityExists(e.getProductId())) return -1; // ABORT, PRODUCT DOESN'T EVEN EXIST
		int id = SHELF_DB.addEntity(e);
		e = getEntityById(id);
		productService.addShelfToProduct(e); //Let's add the shelf to the product
		return id;
	}

	public void updateEntity(Shelf newShelf, Shelf oldShelf) {
		// Se o produto mudou, temos de adicionar a prateleira ao array de prateleiras do produto
		if (newShelf.getProductId() != oldShelf.getProductId()) {
			removeOldShelfFromProduct(oldShelf);
			if (newShelf.getProductId() >= 0) {
				productService.addShelfToProduct(newShelf);
			}
		}

		SHELF_DB.updateEntity(newShelf);
	}

	public void updateEntityOnProductCreate(Shelf updatedShelf) {
		SHELF_DB.updateEntity(updatedShelf);
	}

	private void removeOldShelfFromProduct(Shelf shelf) {
		Product product = productService.getEntityById(shelf.getProductId());
		// Se o array de prateleiras no produto não estava vazia também temos de remover
		// a prateleira do array de prateleiras do antigo produto
		if (product != null && product.getShelfIds().size() > 0 && product.removeShelfId(shelf.getId()) == true) {
			productService.updateEntity(product);
		}
	}

	public Collection<Shelf> getAllEntities() {
		return SHELF_DB.getAllEntities();
	}

	public Shelf getEntityById(int id) {
		return SHELF_DB.getEntityById(id);
	}

	public int getSize() {
		return SHELF_DB.getSize();
	}

	public Shelf deleteEntity(int id) {			
		if (getEntityById(id).getProductId() != -1) {
			throw new UnsupportedOperationException("The Shelf " + id + " has a product, you need to make it empty first."); //ABORT!! SHELF ISN'T EMPTY
		}
		
		return SHELF_DB.deleteEntity(id);
	}

	public boolean entityExists(int id) {
		return SHELF_DB.entityExists(id);
	}

	public boolean productExists(int id) {
		return productService.entityExists(id);
	}

	public void updateProductOnShelfs(Product product, ArrayList<Integer> shelfsOld, ArrayList<Integer> shelfsNew) {
		for (int shelfId : shelfsOld) {
			if (shelfId >= 0 && shelfsNew.indexOf(shelfId) == -1) {
				Shelf shelf = SHELF_DB.getEntityById(shelfId);
				shelf.setProductId((int) -1);
				SHELF_DB.updateEntity(shelf);
			}
		}
		for (int shelfId : shelfsNew) {
			if (shelfId >= 0 && shelfsOld.indexOf(shelfId) == -1) {
				Shelf shelf = SHELF_DB.getEntityById(shelfId);
				shelf.setProductId(product.getId());
				SHELF_DB.updateEntity(shelf);
			}
		}

	}
}