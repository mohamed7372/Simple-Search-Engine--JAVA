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
					strategy();
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
	
	static void strategy() {
		System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
		String choise = sc.nextLine();
		System.out.println("\nEnter a name or email to search all suitable people.");
		String name = sc.nextLine().toLowerCase();
		switch (choise) {
		case "ALL":
			searchAll(name.split(" "));
			break;
		case "ANY":
			searchAny(name.split(" "));
			break;
		case "NONE": 
			searchNone(name.split(" "));
			break;
		}
	}
	static void searchAny(String[] names) {
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < names.length; i++) {
			if(words.containsKey(names[i])) {
				List<Integer> index = words.get(names[i]);
				for (Integer g : index) {
					if(!res.contains(arr.get(g)))
						res.add(arr.get(g));
				}
			}
		}
		if(res.size() != 0) {
			System.out.println("\n" + res.size() + " persons found:");
			for (String string : res) {
				System.out.println(string);
			}
		}
		else
			System.out.println("No matching people found.");
	}
	static void searchAll(String[] names) {
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < arr.size(); i++) {
			boolean t = true;
			String d = arr.get(i).toLowerCase();
			for (int j = 0; j < names.length; j++) {
				if(!d.matches(".*" + names[j] + ".*")) {
					t = false;
					break;
				}
			}
			if(t)
				res.add(arr.get(i));
		}
		if(res.size() != 0) {
			System.out.println("\n" + res.size() + " persons found:");
			for (String string : res) {
				System.out.println(string);
			}
		}
		else
			System.out.println("No matching people found.");
	}
	static void searchNone(String[] names) {
		List<String> res = new ArrayList<String>(arr);
		for (int i = 0; i < names.length; i++) {
			if(words.containsKey(names[i])) {
				List<Integer> index = words.get(names[i]);
				for (int g : index) {
					if(res.contains(arr.get(g)))
						res.remove(arr.get(g));
				}
			}
		}
		if(res.size() != 0) {
			System.out.println("\n" + res.size() + " persons found:");
			for (String string : res) {
				System.out.println(string);
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
