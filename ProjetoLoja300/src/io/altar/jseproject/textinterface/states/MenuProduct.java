package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.dbAccess.ProductDBAccess;

public class MenuProduct extends State implements ProductDBAccess {
	
	@Override
	public int on() {
		System.out.println();
		listProducts();
		System.out.println();
		int[] options = new int[] { 1, 5 };
		System.out.println("\033[0;1m1) Criar Produto");
		if (PRODUCT_DB.getSize() != 0) {
			options = new int[] { 1, 2, 3, 4, 5 };
			System.out.println("2) Editar Produto");
			System.out.println("3) Consultar Produto");
			System.out.println("4) Remover Produto");
		}
		System.out.println("5) Voltar\033[0;0m");
		return sc.getValidInt("", options);
	}
	
	private void listProducts() {
		if (PRODUCT_DB.getSize() != 0) {
			System.out.println("Produtos Existentes:");
			for (Product product : PRODUCT_DB.getAllEntities()) {
				System.out.println("Produto \033[0;1m" + product.getId() + "\033[0;0m - " + product.getName() + " | " + product.getRealPrice() + "€");
			}
		}
		else {
			System.out.println("Ainda não há produtos!");
		}
	}
}




