package userIO;

import java.util.Scanner;

import javax.swing.JFrame;

public class GetInput{	
	public static Scanner keyboard = new Scanner(System.in);
	
	// Prompt user in console to enter first and last name
	// Paramaters - none
	// Returns String[] with first and last name
	public static String[] promptGetUserName()
	{
		String[] fullName = {"",""};
		System.out.println("Enter users first name: ");
		fullName[0] = keyboard.nextLine();//.trim();			// Gets the line of input and trims the leading and trailing edges
		
		System.out.println("Enter users last name: ");
		fullName[1] = keyboard.nextLine();
		
		return fullName;
	}
}
