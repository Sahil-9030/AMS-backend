package com.major.connect.controllers;

import java.util.stream.Collectors;

public class sample {

	// "hello" remove all vowels
	public static void main(String[] args) {
		
		String str = "hello";
		
		String output = str.chars()
							.mapToObj(obj -> (char)obj)
							.filter(f-> "aeiou".indexOf(f) == -1)
							.map(String::valueOf)
							.collect(Collectors.joining());
		
		String output1 = str.chars()
							.mapToObj(obj -> (char)obj)
							.filter(f -> !"aeiou".contains(String.valueOf(f)))
							.map(String::valueOf)
							.collect(Collectors.joining());
		
		System.out.println("Output is :" + output1);
	}

}
