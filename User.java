package SmallChange;

import java.util.Scanner;

/**
 * The User class provides utility methods for handling user input.
 * It contains static methods for validating and obtaining user input.
 * 
 * @author yuanyuanliu
 */
public class User {
	
	private static Scanner keyboard = new Scanner(System.in);
	
	/**
	 * This is a utility class with only static members, prevent instantiation.
	 */
	private User() {}
	
	/**
	 * This method prompts the user to enter an integer value, validates the input,
	 * If the input is not an integer, it will prompt the user to enter a valid 
	 * integer and continue until a valid input is received.
	 * @return  the integer value entered by the user
	 */
	public static int inputInteger() {
		boolean isInputBad = true;
		boolean hasNextInt;
		int value = 0;
		while(isInputBad) {
			hasNextInt = keyboard.hasNextInt();
			if(hasNextInt) {
				value = keyboard.nextInt();
				isInputBad = false; // break out of loop
			}
			else {
				System.out.print("Invalid input. Enter an integer number: ");
			}
			keyboard.nextLine(); // clean up input stream
		}
		return value;
	}
	
	/**
	 * This method prompts the user using the argument sent to message
	 * and then calls to method inputInteger() to get the actual input value.
	 * @return  the integer value entered by the user
	 */
	public static int inputInteger(String message) {
		
		System.out.printf("%s", message);
		int value = inputInteger(); 
		return value;
	}
	
	/**
	 * This method prompts the user to enter a double value 
	 * and validates the input until a valid value is entered
	 * @return double value entered by the user
	 */
	public static double inputDouble() {
		boolean isInputBad = true;
		boolean hasNextDouble;
		double value = 0.0;
		while(isInputBad) {
			hasNextDouble = keyboard.hasNextDouble();
			if(hasNextDouble) {
				value = keyboard.nextDouble();
				isInputBad = false; // break out of loop
			}
			else {
				System.out.print("Invalid input. Enter a number: ");
			}
			keyboard.nextLine(); // clean up input stream
		}
		return value;	
	}
	
	
	/**
	 * This method prompts the user using the argument sent to message
	 * and then calls to method inputDouble() to get the actual input value
	 * @return double value entered by the user
	 */
	public static double inputDouble(String message) {
		
		System.out.printf("%s", message);
		double value = inputDouble();
		return value;
	}
	
	/**
	 * this method is used to get a line of text from the console, it reads
	 * until it encounters a line terminator character.
	 * @return String value entered by the user
	 */
	public static String inputString() {
		String value = keyboard.nextLine();
		return value;
	}
	
	/**
	 * This method prompts the user using the argument sent to message, 
	 * then calls to method inputString() which reads in the actual text
	 * @return String value entered by the user
	 */
	public static String inputString(String message) {
		System.out.printf("%s", message);
		String value = inputString();
		return value;
	}
	
	
	
	/**
	 * This method prompts the user to enter a positive double value.
	 * If the user enters a non-positive value, 
	 * the method will keep prompting until a positive value is entered.
	 * @param prompt prompt a String message that prompts the user to enter a positive double value
	 * @return  the positive double value entered by the user
	 */
	public static double inputPositiveDouble(String prompt) {
		
		
		double value= -1;
		
		while(value<=0) {
			
		 value = inputDouble(prompt);
		
		 if (value<=0 ) {System.out.println("Enter positive number only");}
			
			}
		
		
		return value; 
	}
}