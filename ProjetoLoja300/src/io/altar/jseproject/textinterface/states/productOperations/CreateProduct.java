package io.altar.jseproject.textinterface.states.productOperations;

import java.util.ArrayList;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.dbAccess.ProductDBAccess;
import io.altar.jseproject.textinterface.states.State;

public class CreateProduct extends State implements ProductDBAccess {
	@Override
	public int on() {
		System.out.println("Nome do Produto:");
		String name = sc.getValue();
		double price = sc.getDouble("PVP:", "Isto não é um número rapaz! Tenta outra vez.");
		double discount = sc.getDouble("Desconto:", "Isto não é um número rapaz! Tenta outra vez.");
		double iva = sc.getDouble("IVA:", "Isto não é um número rapaz! Tenta outra vez.");
	
		Product newProduct = (sc.getConfirmation("Pertence adicionar o produto a uma ou várias prateleiras? \033[0;1m(s/n)\033[0;0m") ?  new Product(name, readShelvesToAddProduct(), price, discount, iva) : new Product(name, price, discount, iva)); 
		PRODUCT_DB.addEntity(newProduct);
		
		//Let's go back to were we were
		return 1;
	}
	
	private ArrayList<Integer> readShelvesToAddProduct() {
		ArrayList<Integer> shelves = new ArrayList<Integer>();
		int numberOfShelves = sc.getInt("A quantas prateleiras?", "Isto não é um número rapaz! Tenta outra vez.");
		shelves.addAll(sc.loopingGetInt("Id da prateleira Nº", "Isto não é um número rapaz! Tenta outra vez.", numberOfShelves));
		return shelves;
	}
}
