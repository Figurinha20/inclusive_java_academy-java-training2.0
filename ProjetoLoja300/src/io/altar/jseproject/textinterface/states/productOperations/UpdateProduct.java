package io.altar.jseproject.textinterface.states.productOperations;

import java.util.ArrayList;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.dbAccess.ProductDBAccess;
import io.altar.jseproject.textinterface.states.State;

public class UpdateProduct extends State implements ProductDBAccess {
	@Override
	public int on() {
		int productId = sc.getInt("Id:", "Os Ids dos produtos são numeros! Tenta outra vez.");
		if (PRODUCT_DB.entityExists(productId)) {
			Product currentProduct = PRODUCT_DB.getEntityById(productId);
			editProductParams(currentProduct);
			editProductShelfs(currentProduct);
			PRODUCT_DB.updateEntity(currentProduct);
		}
		else {
			System.out.println("Este produto não existe :(");
		}
		
		//Let's go back to were we were
		return 1;
	}
	
	private void editProductParams(Product currentProduct) {
		System.out.println("Se simplesmente carregar enter para o campo, este fica igual ao antigo.");
		System.out.println("Nome do Produto (" + currentProduct.getName() + "):");
		String name = sc.getValue();
		if (!name.equals("")) {currentProduct.setName(name);}
		String price = sc.getDoubleOrBlank("PVP(" + currentProduct.getPvp() + "):", "Isto não é um número rapaz! Tenta outra vez.");
		if (!price.equals("")) {currentProduct.setPvp(Double.parseDouble(price));}
		String discount = sc.getDoubleOrBlank("Desconto(" + currentProduct.getDiscount() + "):", "Isto não é um número rapaz! Tenta outra vez.");
		if (!discount.equals("")) {currentProduct.setPvp(Double.parseDouble(discount));}
		String iva = sc.getDoubleOrBlank("IVA(" + currentProduct.getIva() + "):", "Isto não é um número rapaz! Tenta outra vez.");
		if (!iva.equals("")) {currentProduct.setPvp(Double.parseDouble(iva));}
	}
	
	private void editProductShelfs(Product currentProduct) {
		//Changing existing shelfs
		System.out.println("Se simplesmente carregar enter para o tabela, o produto é removido da prateleira.");
		ArrayList<Integer> newShelfIds = new ArrayList<Integer>();
		if(currentProduct.getShelfIds().size() > 0) {
			for (int shelfId : currentProduct.getShelfIds()) {
				String newShelfId = sc.getIntOrBlank("Tabela(" + shelfId + "):", "Isto não é um número rapaz! Tenta outra vez.");
				if(!newShelfId.equals("")) {newShelfIds.add(Integer.parseInt(newShelfId));}
			}
		}
		
		//Add new shelfs
		if (sc.getConfirmation("Pretende adicionar novas prateleiras? \033[0;1m(s/n)\033[0;0m")) {
			int loops = sc.getInt("Quantas?", "Só quero um número man.");
			for (Integer shelfId : sc.loopingGetInt("Id da Prateleira Nº", "Só quero um número man.", loops)) {
				newShelfIds.add(shelfId);
			}
		}
		
		currentProduct.setShelfIds(newShelfIds);
	}
}
