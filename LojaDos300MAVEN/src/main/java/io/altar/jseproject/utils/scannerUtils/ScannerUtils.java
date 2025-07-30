package io.altar.jseproject.utils.scannerUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class for handling user input from the console using Scanner.
 * Provides methods to read and validate integer and string values.
 * 
 * @author Joao Cortes
 */
public class ScannerUtils {
	/**********
	 * Fields
	 *********/

	/**
	 * Scanner for reading input from the console.
	 */
	private Scanner sc = new Scanner(System.in);
	/**
	 * Scanner for parsing individual input lines.
	 */
	private Scanner lineSc;

	/***********
	 * Methods
	 **********/

	/**
	 * Reads a line of input from the console.
	 * 
	 * @return the input string entered by the user
	 */
	public String getValue() {
		return sc.nextLine();
	}

	/**
	 * Checks if the provided string can be parsed as an integer.
	 * 
	 * @param value the string to check
	 * @return true if the string is an integer, false otherwise
	 */
	public boolean isInt(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextInt();
	}
	
	public boolean isDouble(String value) {
		lineSc = new Scanner(value);
		return lineSc.hasNextDouble();
	}
	/**
	 * Converts the provided string to an integer. Assumes the string is a valid
	 * integer.
	 * 
	 * @param value the string to convert
	 * @return the integer value
	 */
	public int toInt(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextInt();
	}
	
	public double toDouble(String value) {
		lineSc = new Scanner(value);
		return lineSc.nextDouble();
	}

	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Keeps prompting until a valid integer is entered.
	 * 
	 * @param msg the prompt message
	 * @param errorMsg the error message
	 * @return the integer entered by the user
	 */
	public int getInt(String msg, String errorMsg) {
		while (true) {
			System.out.println(msg);
			String value = getValue();
			if (isInt(value)) {
				return toInt(value);
			}
			else {
				System.out.println(errorMsg);
			}
		}
	}
	
	public String getIntOrBlank(String msg, String errorMsg) {
		while (true) {
			System.out.println(msg);
			String value = getValue();
			if (isInt(value) || value.equals("")) {
				return value;
			}
			else {
				System.out.println(errorMsg);
			}
		}
	}
	
	public double getDouble(String msg, String errorMsg) {
		while (true) {
			System.out.println(msg);
			String value = getValue();
			if (isDouble(value)) {
				return toDouble(value);
			}
			else {
				System.out.println(errorMsg);
			}
		}
	}
	
	public String getDoubleOrBlank(String msg, String errorMsg) {
		while (true) {
			System.out.println(msg);
			String value = getValue();
			if (isDouble(value) || value.equals("")) {
				return value;
			}
			else {
				System.out.println(errorMsg);
			}
		}
	}
	
	public ArrayList<Integer> loopingGetInt(String msg, String errorMsg, int loops){
		ArrayList<Integer> returnValues = new ArrayList<Integer>();
		for (int i = 1; i <= loops ; i++) {
			System.out.println(msg + i + " :");
			String value = getValue();
			if (isInt(value)) {
				returnValues.add(toInt(value));
			} else {
				System.out.println(errorMsg);
				i--; //The user messed up, so we add a loop
			}
		}
		return returnValues;
	}
	
	public boolean getConfirmation(String msg) {
		while (true) {
			System.out.println(msg);
			switch (getValue()) {
			case "s":
				return true;
			case "n":
				return false;
			default:
				System.out.println("Tens de decidir se sim ou não \033[0;1m(s/n)\033[0;0m! Tenta outra vez.");
			}
		}
	}
	
	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Only accepts integers within the specified min and max range (inclusive).
	 * 
	 * @param msg the prompt message
	 * @param min the minimum valid value
	 * @param max the maximum valid value
	 * @return the valid integer entered by the user
	 */
	public int getValidInt(String msg, int min, int max) {
		while (true) {
			int result = getInt(msg, "Isto não é um número! Tenta outra vez.");
			if (result >= min && result <= max) {
				return result;
			}
			else {
				System.out.println("Isto não é uma opção! Tenta outra vez.");
			}
		}
	}
	
	/**
	 * Prompts the user with a message and reads a valid integer from the console.
	 * Only accepts integers that are present in the provided array.
	 * 
	 * @param msg    the prompt message
	 * @param values the array of valid integer values
	 * @return the valid integer entered by the user
	 */
	public int getValidInt(String msg, int[] values) {
		do {
			int result = getInt(msg, "Isto não é um número! Tenta outra vez.");
			for (int i : values) {
				if (result == i) {
					return result;
				}
			}
			System.out.println("Isto não é uma opção! Tenta outra vez.");
		} while (true);
	}
}
