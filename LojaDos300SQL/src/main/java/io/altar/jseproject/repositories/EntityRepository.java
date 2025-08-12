package io.altar.jseproject.repositories;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.model.Entity_;
import io.altar.jseproject.repositories.interfaces.EntityInterface;

public abstract class EntityRepository<T extends Entity_> implements EntityInterface<T> {
	protected abstract Class<T> getEntityClass();

	@PersistenceContext(unitName = "database")
	protected EntityManager entityManager;
	
	public int addEntity(T e) {
		return entityManager.merge(e).getId();
	}
	
	public void updateEntity(T e) {
		entityManager.merge(e);
	}
	
	public abstract String getAll();
	public Collection<T> getAllEntities() {
		return entityManager.createNamedQuery(getAll(), getEntityClass()).getResultList();
	}
	
	
	public T getEntityById(int id) {
		return entityManager.find(getEntityClass(), id);
	}
	
	public abstract String getEntityCount();
	public int getSize() {
		return entityManager.createNamedQuery(getEntityCount(), Integer.class).getSingleResult();
	}
	
	public T deleteEntity(int id) {
		T e = getEntityById(id);
		if (e != null) {
			entityManager.remove(e);
		}
		return e;
	}
	
	public boolean entityExists(int id) {
		return getEntityById(id) != null ? true : false;
	}
}