package pt.upacademy.primeiraTentativa;

import java.util.Scanner;

public class HelloWorld {
	private Scanner scanner = new Scanner(System.in);
	private User currentUser = new User();
	
	public static void main(String[] args) {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.run();
	}

	private String getName() {
		String name = scanner.nextLine();
		return name;
	}
	
	private int getAge() {
		if(scanner.hasNextInt()) {
			int age = scanner.nextInt();
			return age;
		}
		else if (scanner.hasNext()) {
			scanner.nextLine();
		}

		return -1;
	}
	
	private void run() {
		System.out.println("Hello World, what's your name?");
		currentUser.setName(getName());
		System.out.println("And what's your age?");
		currentUser.setAge(getAge());
		while(currentUser.getAge() == -1) {
			System.out.println("That's not a valid age, try again!");
			currentUser.setAge(getAge());
		}
		scanner.close();
		currentUser.printUserInfo();
	}
}
