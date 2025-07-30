package io.altar.jseproject.repositories.dbAccess;

import io.altar.jseproject.repositories.ProductRepository;

public abstract interface ProductDBAccess {
	public static ProductRepository PRODUCT_DB = ProductRepository.getInstance();
}
