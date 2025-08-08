package io.altar.jseproject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;

@TestMethodOrder(OrderAnnotation.class)
public class RepoTests {
	@Inject
	ProductService ps;
	@Inject
	ShelfService ss;
	
	@Test
	@DisplayName("Test set product id")
	@Order(1)
	void testSetProductId() {
		Product p = new Product(null, null, 0, 0, 0);
		assertEquals(0, p.getId(), "Defaul ID should be 0");
		assertDoesNotThrow(() -> {
			p.setId(1);
		});
	}
	
	@Test
	@Order(2)
	@DisplayName("Test set product id with invalid id")
	void testSetProductIdWithInvalidId() {
		Product p = new Product(null, null, 0, 0, 0);
		assertThrows(RuntimeException.class, () -> {
			p.setId(-2);
		});
	}

	@Test
	@Order(3)
	@DisplayName("Test set product id after valid id")
	void testSetProductIdAfterValidId() {
		Product p = new Product(null, null, 0, 0, 0);
		p.setId(1);
		assertThrows(RuntimeException.class, () -> {
			p.setId(2);
		});
		assertThrows(RuntimeException.class, () -> {
			p.setId(-2);
		});
	}
	
	@Test
	@Order(4)
	@DisplayName("Test get clone from repository")
	 void testGetCloneFromRepo() {
	  Product originalProduct = new Product("Cola", 0.9, 5, 0);
	  ps.addEntity(originalProduct);
	  Product productFormRepo1 = ps.getEntityById(0);
	  Product productFormRepo2 = ps.getEntityById(0);
	  productFormRepo2.setIva(23);
	  assertNotSame(productFormRepo1,productFormRepo2);
	  assertNotEquals(productFormRepo1.getIva(),productFormRepo2.getIva());
	  assertNotSame(originalProduct,productFormRepo1);
	  assertNotSame(originalProduct,productFormRepo2);
	 }
	
//	
//	@Test
//	@Order(5)
//	@DisplayName("Add Default Data for tests")
//	void addDefaultData() {
//		//Populate "DB" with data
//		Product p1 = new Product("Cola", 0.9, 5, 23);
//		Product p2 = new Product("Pepsi", 0.8, 5, 23);
//		Product p3 = new Product("Pepsi Max", 0.75, 5, 12);
//		ps.addEntity(p1);
//		ps.addEntity(p2);
//		ps.addEntity(p3);
//		Shelf s1 = new Shelf(200, 24);
//		Shelf s2 = new Shelf(400, 38);
//		Shelf s3 = new Shelf(300, 1, 30);
//		ss.addEntity(s1);
//		ss.addEntity(s2);
//		ss.addEntity(s3);
//	}
//	
//	@Test
//	@Order(6)
//	@DisplayName("Test if same product in same shelf doesn't replicate")
//	void testIfSameProductInSameShelfDoesNotReplicate() {
//		//Add Product 0 to the Shelf 0	
//		Shelf oldsShelf = ss.getEntityById(0);
//		Shelf newShelf = ss.getEntityById(0);
//		newShelf.setProductId(0);
//		ss.updateEntity(newShelf, oldsShelf);
//		
//		//Add Product 0 to Shelf 0, it shouldn't add it to the product again!
//		oldsShelf = ss.getEntityById(0);
//		newShelf = ss.getEntityById(0);
//		newShelf.setProductId(0);
//		ss.updateEntity(newShelf, oldsShelf);
//		
//		assertEquals(1, ps.getEntityById(0).getShelfIds().size());
//	}
//	
//	@Test
//	@Order(7)
//	@DisplayName("Test if shelf was removed from product")
//	void testIfShelfWasRemovedFromProduct() {
//		//Remove the shelf
//		Shelf currentShelf = ss.getEntityById(0);
//		ss.deleteEntity(currentShelf.getId());
//		
//		assertEquals(0, ps.getEntityById(0).getShelfIds().size());
//	}
//	
//	@Test
//	@Order(8)
//	@DisplayName("Test if shelf is added to product on create")
//	void testIfShelfIsAddedToProductOnCreate() {
//		//create a new shelf with the first product
//		Shelf s4 = new Shelf(600, 0, 50);
//		ss.addEntity(s4);
//		
//		assertEquals(2, ps.getEntityById(0).getShelfIds().size());
//	}

}
