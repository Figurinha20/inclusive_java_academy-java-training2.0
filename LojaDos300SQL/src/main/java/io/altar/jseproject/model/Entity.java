package io.altar.jseproject.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (this.id != 0 || id < 0) {

			throw new UnsupportedOperationException("Este atributo não pode ser modificado.");
//			throw new IllegalStateException("Este atributo não pode ser modificado.");
//			throw new IllegalArgumentException("Este atributo não pode ser modificado.");
		}
		this.id = id;
	}
}
