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
		
		System.out.println("\nEnter the number of search queries:");
		int nbrSearchQue = Integer.valueOf(sc.nextLine());
		
		System.out.println();
		int i = 1;
		while(i<=nbrSearchQue) {
			search(allPeople);
			i++;
		}
	}
	static void search(String[] arr) {
		System.out.println("Enter data to search people:");
		String name = sc.nextLine().toLowerCase();
		
		String res = "";
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i].toLowerCase();
			if(s.matches(".*" + name + ".*")) 
				res += arr[i] + "\n";
		}
		System.out.println(res.isEmpty() ? "No matching people found.\n" : "\nFound people:\n" + res);
	}
}
