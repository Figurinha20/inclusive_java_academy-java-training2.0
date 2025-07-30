package io.altar.jseproject.textinterface.states.productOperations;

import io.altar.jseproject.repositories.dbAccess.ProductDBAccess;
import io.altar.jseproject.textinterface.states.State;

public class DeleteProduct extends State implements ProductDBAccess {

	@Override
	public int on() {
		int id = sc.getInt("Id:", "Os Ids dos produtos são numeros! Tenta outra vez.");
		if (PRODUCT_DB.entityExists(id)) {
			if(sc.getConfirmation("Pretende mesmo remover o produto \033[0;1m" + PRODUCT_DB.getEntityById(id).getName() + "\033[0;0m?! \033[0;1m (s/n)\033[0;0m")) {
				PRODUCT_DB.deleteEntity(id);
			}
		}
		else {
			System.out.println("Este produto não existe :(");
		}
		
		//let's go back
		return 1;
	}
}
