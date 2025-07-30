package io.altar.jseproject.repositories;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import io.altar.jseproject.model.Entity;
import io.altar.jseproject.repositories.interfaces.EntityInterface;

public abstract class EntityRepository<T extends Entity> implements EntityInterface<T> {
	private Map<Integer, T> db = new HashMap<Integer, T>();
	private int currentId = 0;
	
	public int addEntity(T e) {
		e.setId(currentId);
		db.put(currentId, e);
		return currentId++;
	}
	
	public void updateEntity(T e) {
		db.put(e.getId(), e);
	}
	
	public Collection<T> getAllEntities() {
		return db.values();
	}
	
	public T getEntityById(int id) {
		return db.get(id);
	}
	
	public int getSize() {
		return db.size();
	}
	
	public T deleteEntity(int id) {
		return db.remove(id);
	}
	
	public boolean entityExists(int id) {
		return db.get(id) != null ? true : false;
	}
}