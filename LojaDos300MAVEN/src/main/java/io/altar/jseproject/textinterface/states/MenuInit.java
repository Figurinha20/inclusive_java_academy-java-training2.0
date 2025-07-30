package io.altar.jseproject.textinterface.states;

public class MenuInit extends State {

	@Override
	public int on() {
		System.out.println("Por favor selecione uma das seguintes opções:\r\n" 
				+ "\033[0;1m1) Listar produtos\r\n"
				+ "2) Listar prateleiras\r\n" 
				+ "3) Sair\033[0;0m");
		return sc.getValidInt("", 1, 3);	
	}

}