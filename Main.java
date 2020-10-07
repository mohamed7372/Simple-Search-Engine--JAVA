package simpleSearchEngine;

import java.util.Scanner;

public class Main {
	final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Enter the number of people:");
		int nbrPeople = Integer.valueOf(sc.nextLine());
		
		System.out.println("Enter all people:");
		String[] allPeople = new String[nbrPeople];
		for (int i = 0; i < allPeople.length; i++) 
			allPeople[i] = sc.nextLine();
		
		int fin = 0;
		while(fin == 0) {
			menu();
			int ch = Integer.valueOf(sc.nextLine());
			switch (ch) {
			case 1:
				search(allPeople);
				break;
			case 2:
				listePeople(allPeople);
				break;
			case 0:
				fin = 1;
				System.out.println("\nBye!");
				break;
			default:
				System.out.println("\nIncorrect option! Try again.");
			}
		}
	}
	
	static void menu() {
		System.out.println("\n=== Menu ===");
		System.out.println("1. Search information.");
		System.out.println("2. Print all data.");
		System.out.println("0. Exit.");
	}
	static void search(String[] arr) {
		System.out.println("\nEnter a name or email to search all suitable people.");
		String name = sc.nextLine().toLowerCase();
		
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i].toLowerCase();
			if(s.matches(".*" + name + ".*"))
				System.out.println(arr[i]);
		}
	}
	static void listePeople(String[] arr) {
		System.out.println("\n=== List of people ===");
		for (int i = 0; i < arr.length; i++) 
			System.out.println(arr[i]);
	}
}
