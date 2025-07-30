package io.altar.jseproject;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;

public class RepoTests {
	@Test
	 void testGetCloneFromRepo() {
	  Product originalProduct = new Product("Cola", 0.9, 5, 0);
	  ProductRepository pr = ProductRepository.getInstance();
	  pr.addEntity(originalProduct);
	  Product productFormRepo1 = pr.getEntityById(0);
	  Product productFormRepo2 = pr.getEntityById(0);
	  productFormRepo2.setIva(23);
	  assertNotSame(productFormRepo1,productFormRepo2);
	  assertNotEquals(productFormRepo1.getIva(),productFormRepo2.getIva());
	  assertNotSame(originalProduct,productFormRepo1);
	  assertNotSame(originalProduct,productFormRepo2);
	 }
}
