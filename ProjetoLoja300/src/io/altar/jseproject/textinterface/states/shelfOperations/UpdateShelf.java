package io.altar.jseproject.textinterface.states.shelfOperations;

import io.altar.jseproject.repositories.dbAccess.ShelfDBAccess;
import io.altar.jseproject.textinterface.states.State;

public class UpdateShelf extends State implements ShelfDBAccess {

	@Override
	public int on() {
		return 1;
	}

}
