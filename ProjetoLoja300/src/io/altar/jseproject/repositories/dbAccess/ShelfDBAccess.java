package io.altar.jseproject.repositories.dbAccess;

import io.altar.jseproject.repositories.ShelfRepository;

public abstract interface ShelfDBAccess {
	public static ShelfRepository SHELF_DB = ShelfRepository.getInstance();
}
