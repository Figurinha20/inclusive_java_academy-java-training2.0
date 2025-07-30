package io.altar.jseproject.repositories.interfaces;

import java.util.Collection;

import io.altar.jseproject.model.Entity;

public abstract interface EntityInterface<T extends Entity> {
	public int addEntity(T e);
	
	public void updateEntity(T e);
	
	public Collection<T> getAllEntities();
	
	public T getEntityById(int id);
	
	public int getSize();
	
	public T deleteEntity(int id);
	
	public boolean entityExists(int id);
}
