package io.altar.jseproject.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.altar.jseproject.model.Shelf;

@ApplicationScoped
public class ShelfRepository extends EntityRepository<Shelf>{

	@Override
	protected Class<Shelf> getEntityClass() { 
		return Shelf.class;
	}

	@Override
	public String getAll() {
		return Shelf.GET_ALL_SHELVES;
	}

	@Override
	public String getEntityCount() {
		return Shelf.GET_SHELF_COUNT;
	}
}
