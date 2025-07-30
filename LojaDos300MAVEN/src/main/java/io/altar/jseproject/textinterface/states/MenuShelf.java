package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;

public class MenuShelf extends State {
	ShelfService shelfService = new ShelfService();
	
	@Override
	public int on() {
		System.out.println();
		listShelves();
		System.out.println();
		int[] options = new int[] { 1, 5 };
		System.out.println("\033[0;1m1) Criar Prateleira");
		if (shelfService.getSize() != 0) {
			options = new int[] { 1, 2, 3, 4, 5 };
			System.out.println("2) Editar Prateleira");
			System.out.println("3) Consultar Prateleira");
			System.out.println("4) Remover Prateleira");
		}
		System.out.println("5) Voltar\033[0;0m");
		return sc.getValidInt("", options);
	}
	
	private void listShelves() {
		if (shelfService.getSize() != 0) {
			System.out.println("Prateleiras Existentes:");
			for (Shelf shelf : shelfService.getAllEntities()) {
				System.out.println("Prateleiras \033[0;1m" + shelf.getId() + "\033[0;0m | Capacidade de " + shelf.getCapacity() + " unidades | Renda diária de " + shelf.getDailyPrice() + "€");
				if(shelf.getProductId() != -1) {System.out.println("\tProduto \033[0;1m" + shelf.getProductId() + "\033[0;0m");}
			}
		}
		else {
			System.out.println("Ainda não há prateleiras!");
		}
	}
}




