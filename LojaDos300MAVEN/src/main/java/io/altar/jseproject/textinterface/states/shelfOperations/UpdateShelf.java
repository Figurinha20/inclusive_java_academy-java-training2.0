package io.altar.jseproject.textinterface.states.shelfOperations;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.states.State;

public class UpdateShelf extends State {
	ShelfService shelfService = new ShelfService();
	
	@Override
	public int on() {
		int shelfId = sc.getInt("Id:", "Os Ids das prateleiras são numeros! Tenta outra vez.");
		if (shelfService.entityExists(shelfId)) {
			Shelf oldShelf = shelfService.getEntityById(shelfId);
			Shelf currentShelf = shelfService.getEntityById(shelfId);
			editShelfParams(currentShelf);
			changeProductInShelf(currentShelf);
			shelfService.updateEntity(currentShelf, oldShelf);
		}
		else {
			System.out.println("Esta prateleira não existe :(");
		}
		
		//Let's go back to were we were
		return 1;
	}
	
	private void editShelfParams(Shelf currentShelf) {
		System.out.println("Se simplesmente carregar enter para o campo, este fica igual ao antigo.");
		String capacity = sc.getIntOrBlank("Capacidade(" + currentShelf.getCapacity() + "):", "Isto não é um número rapaz! Tenta outra vez.");
		if (!capacity.equals("")) {currentShelf.setCapacity(Integer.parseInt(capacity));}
		String dailyprice = sc.getDoubleOrBlank("Preço Diário(" + currentShelf.getDailyPrice() + "):", "Isto não é um número rapaz! Tenta outra vez.");
		if (!dailyprice.equals("")) {currentShelf.setDailyPrice(Double.parseDouble(dailyprice));}
	}
	
	private void changeProductInShelf(Shelf currentShelf) {
		String productId = sc.getIntOrBlank("Id do Produto(" + currentShelf.getProductId() + ") (-1 é vazio):", "Isto não é um número rapaz! Tenta outra vez.");
		if (!productId.equals("")) {
			if(shelfService.productExists(Integer.parseInt(productId))) {
				currentShelf.setProductId(Integer.parseInt(productId));
			} else {
				System.out.println("O Produto " + productId + " não existe. Vou passar...");
			}
		}
	}
}
