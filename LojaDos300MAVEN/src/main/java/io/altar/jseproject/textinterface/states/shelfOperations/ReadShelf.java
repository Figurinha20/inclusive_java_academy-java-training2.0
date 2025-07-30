package io.altar.jseproject.textinterface.states.shelfOperations;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.states.State;

public class ReadShelf extends State {
	ShelfService shelfService = new ShelfService();
	
	@Override
	public int on() {
		int shelfId = sc.getInt("Id:", "Os Ids das prateleiras são numeros! Tenta outra vez.");
		if(shelfService.entityExists(shelfId)) {
			Shelf shelf = shelfService.getEntityById(shelfId);
			System.out.println(shelf.getCapacity() + " - " + shelf.getDailyPrice() + "€");
			int productId = shelf.getProductId();
			System.out.println(productId != -1 ? "Produto " + productId : "Prateleira vazia!");
		}
		else {
			System.out.println("Esta prateleiras não existe :(");
		}
		
		//Let's go back to were we were
		return 1;
	}
}
