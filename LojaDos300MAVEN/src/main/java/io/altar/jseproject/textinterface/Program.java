package io.altar.jseproject.textinterface;

import io.altar.jseproject.utils.helpers.SampleData;

public class Program {
	
	public static void main(String[] args) {
		StateMachine program = new StateMachine();
		SampleData.populateDb();
		program.start();;
	}
}
