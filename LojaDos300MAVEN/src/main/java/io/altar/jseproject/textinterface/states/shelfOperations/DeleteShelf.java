package io.altar.jseproject.textinterface.states.shelfOperations;

import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.states.State;

public class DeleteShelf extends State {
	ShelfService shelfService = new ShelfService();
	
	@Override
	public int on() {
		int id = sc.getInt("Id:", "Os Ids das prateleiras são numeros! Tenta outra vez.");
		if (shelfService.entityExists(id)) {
			if(sc.getConfirmation("Pretende mesmo remover a prateleira \033[0;1m" + shelfService.getEntityById(id).getId() + "\033[0;0m?! \033[0;1m (s/n)\033[0;0m")) {
				shelfService.deleteEntity(id);
			}
		}
		else {
			System.out.println("Esta prateleira não existe :(");
		}
		
		//let's go back
		return 1;
	}
}
