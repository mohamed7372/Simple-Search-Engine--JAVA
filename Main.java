package simpleSearchEngine;

import java.util.Scanner;

public class Main {
	final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String words = sc.nextLine();
		String search = sc.next();
		
		String[] arr = words.split(" ");
		int pos = -1;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].equals(search)) { 
				pos = i+1;
				break;
			}
		}
		System.out.println(pos == -1 ? "Not found" : pos);
	}

}
