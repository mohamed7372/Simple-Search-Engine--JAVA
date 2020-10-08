package simpleSearchEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<String> arr = new ArrayList<String>();
	static Map<String, List<Integer>> words = new HashMap<String, List<Integer>>();
	
	public static void main(String[] args) {
		if(args.length>1)
			if(args[0].equals("--data")) {
				getDataFile(args[1]);
			}
		if(arr.size() != 0) {
			indexingWord();
			sc = new Scanner(System.in);
			int fin = 0;
			while(fin == 0) {
				menu();
				int ch = Integer.valueOf(sc.nextLine());
				switch (ch) {
				case 1:
					search();
					break;
				case 2:
					listePeople();
					break;
				case 0:
					fin = 1;
					System.out.println("\nBye!");
					break;
				default:
					System.out.println("\nIncorrect option! Try again.");
				}
				System.out.println();
			}
		}
	}
	
	static void menu() {
		System.out.println("=== Menu ===");
		System.out.println("1. Search information.");
		System.out.println("2. Print all data.");
		System.out.println("0. Exit.");
	}
	static void search() {
		System.out.println("\nEnter a name or email to search all suitable people.");
		String name = sc.nextLine().toLowerCase();
		if(words.containsKey(name)) {
			List<Integer> index = words.get(name);
			for (Integer i : index) {
				System.out.println(arr.get(i));
			}
		}
		else
			System.out.println("No matching people found.");
	}
	static void listePeople() {
		System.out.println("\n=== List of people ===");
		for (int i = 0; i < arr.size(); i++) 
			System.out.println(arr.get(i));
	}

	static void getDataFile(String nameFile) {
		try {
			File f = new File("C:\\Users\\HP\\eclipse-work\\zhard8\\src\\simpleSearchEngine\\" + nameFile);
			if(f.exists()) {
			sc = new Scanner(f);
			while(sc.hasNext()) 
				arr.add(sc.nextLine());
			}
			sc.close();
		} 
		catch (IOException e) { 
			e.printStackTrace();
		}
	}

	static void indexingWord() {
		ArrayList<String> allWord = new ArrayList<String>(); 
		for (int i = 0; i < arr.size(); i++) {
			String[] s = arr.get(i).split(" ");
			for (int j = 0; j < s.length; j++) 
				allWord.add(s[j].toLowerCase());
		}
		//
		for (int i = 0; i < allWord.size(); i++) {
			if(!words.containsKey(allWord.get(i))) {
				List<Integer> index = new ArrayList<Integer>();
				for (int j = 0; j < arr.size(); j++) {
					String ss = arr.get(j).toLowerCase();
					if(ss.matches(".*" + allWord.get(i) + ".*"))
						index.add(j);
				}
				words.put(allWord.get(i), index);
			}
		}
	}
}
