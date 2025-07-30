package io.altar.jseproject.textinterface;


import io.altar.jseproject.textinterface.states.MenuInit;
import io.altar.jseproject.textinterface.states.MenuProduct;
import io.altar.jseproject.textinterface.states.MenuShelf;
import io.altar.jseproject.textinterface.states.productOperations.CreateProduct;
import io.altar.jseproject.textinterface.states.productOperations.DeleteProduct;
import io.altar.jseproject.textinterface.states.productOperations.UpdateProduct;
import io.altar.jseproject.textinterface.states.shelfOperations.CreateShelf;
import io.altar.jseproject.textinterface.states.shelfOperations.DeleteShelf;
import io.altar.jseproject.textinterface.states.shelfOperations.ReadShelf;
import io.altar.jseproject.textinterface.states.shelfOperations.UpdateShelf;
import io.altar.jseproject.textinterface.states.productOperations.ReadProduct;
import io.altar.jseproject.textinterface.states.State;


//1. Create a "wrapper" class that models the state machine
public class StateMachine {
	// 2. states
	private State[] states = { 
			new MenuInit(), // State 0
			new MenuProduct(), // State 1
			new MenuShelf(), // State 2
			new CreateProduct(), // State 3
			new UpdateProduct(), // State 4
			new ReadProduct(), // State 5
			new DeleteProduct(), // State 6
			new CreateShelf(), // State 7
			new UpdateShelf(), // State 8
			new ReadShelf(), // State 9 
			new DeleteShelf() // State 10
	};
	// 4. transitions
	private int[][] transition = { 
			{ 1, 2 }, 
			{ 3, 4, 5, 6, 0 }, 	
			{ 7, 8, 9, 10, 0 }, 
			{ 1 }, 
			{ 1 }, 
			{ 1 },
			{ 1 },
			{ 2 },
			{ 2 },
			{ 2 },
			{ 2 }
			};
	
	// 3. current
	private int current = 0;

	// 5. All client requests are simply delegated to the current state object
	public void start() {
		while(true) {
			int option = states[current].on();
			if (current == 0 && option == 3) {
				System.out.println("\033[0;1mBye Bye 👋\033[0;0m");
				break; //desliga a app
			}
			current = transition[current][option-1];
		}
	}

}