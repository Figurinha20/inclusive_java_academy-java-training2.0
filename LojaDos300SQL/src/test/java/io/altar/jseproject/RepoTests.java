package io.altar.jseproject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
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
		Product p = new Product();
		assertEquals(0, p.getId(), "Defaul ID should be 0");
		assertDoesNotThrow(() -> {
			p.setId(1);
		});
	}
	
	@Test
	@Order(2)
	@DisplayName("Test set product id with invalid id")
	void testSetProductIdWithInvalidId() {
		Product p = new Product();
		assertThrows(RuntimeException.class, () -> {
			p.setId(-2);
		});
	}

	@Test
	@Order(3)
	@DisplayName("Test set product id after valid id")
	void testSetProductIdAfterValidId() {
		Product p = new Product();
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
	  Product originalProduct = new Product();
	  ps.addEntity(originalProduct);
	  Product productFormRepo1 = ps.getEntityById(0);
	  Product productFormRepo2 = ps.getEntityById(0);
	  productFormRepo2.setIva(23);
	  assertNotSame(productFormRepo1,productFormRepo2);
	  assertNotEquals(productFormRepo1.getIva(),productFormRepo2.getIva());
	  assertNotSame(originalProduct,productFormRepo1);
	  assertNotSame(originalProduct,productFormRepo2);
	 }
	
	
	@Test
	@Order(5)
	@DisplayName("Add Default Data for tests")
	void addDefaultData() {
		//Populate "DB" with data
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		ps.addEntity(p1);
		ps.addEntity(p2);
		ps.addEntity(p3);
		Shelf s1 = new Shelf();
		Shelf s2 = new Shelf();
		Shelf s3 = new Shelf();
		ss.addEntity(s1);
		ss.addEntity(s2);
		ss.addEntity(s3);
	}
	
	@Test
	@Order(6)
	@DisplayName("Test if same product in same shelf doesn't replicate")
	void testIfSameProductInSameShelfDoesNotReplicate() {
		//Add Product 0 to the Shelf 0	
		Shelf oldsShelf = ss.getEntityById(0);
		Shelf newShelf = ss.getEntityById(0);
		newShelf.setProductId(0);
		ss.updateEntity(newShelf, oldsShelf);
		
		//Add Product 0 to Shelf 0, it shouldn't add it to the product again!
		oldsShelf = ss.getEntityById(0);
		newShelf = ss.getEntityById(0);
		newShelf.setProductId(0);
		ss.updateEntity(newShelf, oldsShelf);
		
		assertEquals(1, ps.getEntityById(0).getShelfIds().size());
	}
	
	@Test
	@Order(7)
	@DisplayName("Test if shelf was removed.")
	void testIfShelfWasRemoved() {
		//The shelf isn't empty, there should be an exception
		Shelf currentShelf = ss.getEntityById(0);
		assertThrows(UnsupportedOperationException.class, () -> {
			ss.deleteEntity(currentShelf.getId());
		}); 
	}
	
	@Test
	@Order(8)
	@DisplayName("Test if shelf is added to product")
	void testIfShelfIsAddedToProduct() {
		//create a new shelf with the first product
		Shelf s4 = new Shelf();
		s4.setProductId(0);
		ss.addEntity(s4);
		
		assertEquals(2, ps.getEntityById(0).getShelfIds().size());
	}

}
