package io.altar.jseproject.textinterface.states.productOperations;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.textinterface.states.State;

public class ReadProduct extends State {
	ProductService productService = new ProductService();
	
	@Override
	public int on() {
		int productId = sc.getInt("Id:", "Os Ids dos produtos são numeros! Tenta outra vez.");
		if(productService.entityExists(productId)) {
			Product product = productService.getEntityById(productId);
			System.out.println(product.getName() + " - " + product.getRealPrice() + "€" +
			" | PVP: "+ Double.toString(product.getPvp()) + "€" + 
			" | IVA: "+ Double.toString(product.getIva()) + "%" +
			" | Desconto: "+ Double.toString(product.getDiscount()) + "%");
			
			for (int shelfId : product.getShelfIds()) {
				System.out.println("\tPrateleira \033[0;1m" + shelfId + "\033[0;0m");
			}
		}
		else {
			System.out.println("Este produto não existe :(");
		}
		
		//Let's go back to were we were
		return 1;
	}
}
