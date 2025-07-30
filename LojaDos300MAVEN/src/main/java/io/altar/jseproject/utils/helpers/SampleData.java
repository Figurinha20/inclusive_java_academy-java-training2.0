package io.altar.jseproject.utils.helpers;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;

public class SampleData {
	public static void populateDb() {
		ProductService ps = new ProductService();
		ShelfService ss = new ShelfService();
		
		//Populate "DB" with data
		Product p1 = new Product("Cola", 0.9, 5, 23);
		Product p2 = new Product("Pepsi", 0.8, 5, 23);
		Product p3 = new Product("Pepsi Max", 0.75, 5, 12);
		ps.addEntity(p1);
		ps.addEntity(p2);
		ps.addEntity(p3);
		Shelf s1 = new Shelf(200, 24);
		Shelf s2 = new Shelf(400, 38);
		Shelf s3 = new Shelf(300, 1, 30);
		ss.addEntity(s1);
		ss.addEntity(s2);
		ss.addEntity(s3);
		
		//Add product to a Shelf
		Shelf oldsShelf = ss.getEntityById(0);
		Shelf newShelf = ss.getEntityById(0);
		newShelf.setProductId(0);
		ss.updateEntity(newShelf, oldsShelf);
		
		//Add the same product to the Shelf, it shouldn't add it to the product again!
		oldsShelf = ss.getEntityById(0);
		newShelf = ss.getEntityById(0);
		newShelf.setProductId(0);
		ss.updateEntity(newShelf, oldsShelf);
		
		//Add the product to another Shelf as well
		oldsShelf = ss.getEntityById(1);
		newShelf = ss.getEntityById(1);
		newShelf.setProductId(0);
		ss.updateEntity(newShelf, oldsShelf);
		
		//Remove the first shelf
		Shelf currentShelf = ss.getEntityById(0);
		ss.deleteEntity(currentShelf.getId());
		
		//Add a product to the Shelf as well
		oldsShelf = ss.getEntityById(1);
		newShelf = ss.getEntityById(1);
		newShelf.setProductId(2);
		ss.updateEntity(newShelf, oldsShelf);
		
		//Add the same product to another Shelf
		oldsShelf = ss.getEntityById(2);
		newShelf = ss.getEntityById(2);
		newShelf.setProductId(2);
		ss.updateEntity(newShelf, oldsShelf);
		
		//create a new shelf with the first product
		Shelf s4 = new Shelf(600, 0, 50);
		ss.addEntity(s4);
	}
}
