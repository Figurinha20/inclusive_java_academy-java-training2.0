package pt.upacademy.primeiraTentativa;

import java.util.Scanner;

public class WorseHelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello World, what's your name?");
		String userName = getName(scanner);
		System.out.println("Hello " + userName + "!");
		
		System.out.println("And what's your age?");
		int userAge = getAge(scanner);
		System.out.println("You're  " + userAge + " years old.");
		scanner.close();
	}

	private static String getName(Scanner scanner) {
		String name = scanner.nextLine();
		return name;
	}
	
	private static int getAge(Scanner scanner) {
		int age = scanner.nextInt();
		return age;
	}
}
