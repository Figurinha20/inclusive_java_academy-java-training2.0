package io.altar.jseproject.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.models.entities.Product;


@ApplicationScoped
public class ProductRepository extends EntityRepository<Product>{

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String getAll() {
		return Product.GET_ALL_PRODUCTS;
	}

	@Override
	public String getEntityCount() {
		return Product.GET_PRODUCT_COUNT;
	}
}
