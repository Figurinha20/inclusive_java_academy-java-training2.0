package io.altar.jseproject.textinterface.states.shelfOperations;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.dbAccess.ShelfDBAccess;
import io.altar.jseproject.textinterface.states.State;

public class CreateShelf extends State implements ShelfDBAccess {
	@Override
	public int on() {
		int capacity = sc.getInt("Capacidade;", "Isto não é um número rapaz! Tenta outra vez.");
		int dailyPrice = sc.getInt("Preço Diário:", "Isto não é um número rapaz! Tenta outra vez.");
	
		Shelf newShelf = (sc.getConfirmation("Pretende adicionar um produto à pratleira? \033[0;1m(s/n)\033[0;0m") ?  new Shelf(readProductToAdd(), capacity, dailyPrice) : new Shelf(capacity, dailyPrice)); 
		SHELF_DB.addEntity(newShelf);
		
		//Let's go back to were we were
		return 1;
	}
	
	private int readProductToAdd() {
		int productId = sc.getInt("Qual o ID do Produto?", "Isto não é um número rapaz! Tenta outra vez.");
		return productId;
	}
}