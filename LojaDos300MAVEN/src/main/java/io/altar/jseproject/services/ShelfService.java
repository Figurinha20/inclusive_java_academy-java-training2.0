package io.altar.jseproject.services;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.dbAccess.ShelfDBAccess;

public class ShelfService extends EntityService implements ShelfDBAccess{
	static ProductService productService = new ProductService();
	
	public int addEntity(Shelf e) {
		int id = SHELF_DB.addEntity(e);
		if(e.getProductId() != -1) {
			productService.addShelfToProduct(e);
		}
		return id;
	}
	
	public void updateEntity(Shelf newShelf, Shelf oldShelf) {
		//Se o produto mudou, temos de adicionar a prateleira ao array de prateleiras do produto
		if(newShelf.getProductId() != oldShelf.getProductId()) {
			productService.addShelfToProduct(newShelf);
			Product oldProduct = productService.getEntityById(oldShelf.getProductId());
			//Se o array de prateleiras no produto não estava vazia também temos de remover a prateleira do array de prateleiras do antigo produto
			if (oldProduct != null && oldProduct.getShelfIds().size() > 0) {
				if (oldProduct.removeShelfId(newShelf.getId()) == true) {productService.updateEntity(oldProduct);}
			}
		}
		
		SHELF_DB.updateEntity(newShelf);
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
		Shelf removedShelf = SHELF_DB.deleteEntity(id);
		//Se realmente removeu uma prateleira e a prateleira tinha um produto
		if (removedShelf != null  && removedShelf.getProductId() != -1) {
			Product product = productService.getEntityById(removedShelf.getProductId());
			if (product.removeShelfId(removedShelf.getId()) == true) {productService.updateEntity(product);}
		}
		
		return removedShelf;
	}
	
	public boolean entityExists(int id) {
		return SHELF_DB.entityExists(id);
	}
	
	public boolean productExists(int id) {
		return productService.entityExists(id);
	}
}