package io.altar.jseproject.textinterface.states.shelfOperations;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.states.State;

public class CreateShelf extends State {
	ShelfService shelfService = new ShelfService();
	
	@Override
	public int on() {
		int capacity = sc.getInt("Capacidade;", "Isto não é um número rapaz! Tenta outra vez.");
		double dailyPrice = sc.getDouble("Preço Diário:", "Isto não é um número rapaz! Tenta outra vez.");
		Shelf newShelf = (sc.getConfirmation("Pretende adicionar um produto à prateleira? \033[0;1m(s/n)\033[0;0m") ?  new Shelf(capacity, readProductToAdd(), dailyPrice) : new Shelf(capacity, dailyPrice)); 
		shelfService.addEntity(newShelf);
		
		//Let's go back to were we were
		return 1;
	}
	
	private int readProductToAdd() {
		int productId = sc.getInt("Qual o ID do Produto?", "Isto não é um número rapaz! Tenta outra vez.");
		if(shelfService.productExists(productId)) {
			return productId;
		}
		System.out.println("O Produto " + productId + " não existe. Vou passar...");
		return -1; //-1 é uma prateleira sem produto
	}
}