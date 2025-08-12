package io.altar.jseproject.repositories.interfaces;

import java.util.Collection;

import io.altar.jseproject.model.Entity_;

public abstract interface EntityInterface<T extends Entity_> {
	public int addEntity(T e);		
	
	public void updateEntity(T e);
	
	public abstract String getAll();
	public Collection<T> getAllEntities();
	
	public T getEntityById(int id);
	
	public abstract String getEntityCount();
	public int getSize();
	
	public T deleteEntity(int id);
	
	public boolean entityExists(int id);
}
