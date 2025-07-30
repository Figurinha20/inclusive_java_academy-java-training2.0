package io.altar.jseproject.textinterface.states;

public class MenuShelf extends State {

	@Override
	public int on() {
		int[] options = new int[] { 1, 2, 3, 4, 5 };
		System.out.println("\033[0;1m1) Criar Prateleira");
		System.out.println("2) Editar Prateleira");
		System.out.println("3) Consultar Prateleira");
		System.out.println("4) Remover Prateleira");
		System.out.println("5) Voltar\033[0;0m");
		return sc.getValidInt("", options);
	}
}




