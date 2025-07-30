package io.altar.jseproject.textinterface.states.productOperations;

import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.textinterface.states.State;

public class DeleteProduct extends State {
	ProductService productService = new ProductService();
	
	@Override
	public int on() {
		int id = sc.getInt("Id:", "Os Ids dos produtos são numeros! Tenta outra vez.");
		if (productService.entityExists(id)) {
			if(sc.getConfirmation("Pretende mesmo remover o produto \033[0;1m" + productService.getEntityById(id).getName() + "\033[0;0m?! \033[0;1m (s/n)\033[0;0m")) {
				productService.deleteEntity(id);
			}
		}
		else {
			System.out.println("Este produto não existe :(");
		}
		
		//let's go back
		return 1;
	}
}
