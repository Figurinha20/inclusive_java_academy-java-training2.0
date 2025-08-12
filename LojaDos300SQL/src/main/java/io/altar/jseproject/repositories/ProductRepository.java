package io.altar.jseproject.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.model.Product;


@ApplicationScoped
public class ProductRepository extends EntityRepository<Product>{
}
