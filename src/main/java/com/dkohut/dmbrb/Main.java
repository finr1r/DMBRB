package com.dkohut.dmbrb;

import com.dkohut.dmbrb.generators.WrapperGenerator;

public class Main {

	public static void main(String[] args) {		
		System.out.println(greeting());
		generate();
	}
	
	public static String greeting() {
		return 
				"##################################################################\n" +
				"##                                                              ##\n" +
				"##     @@@@@@@     @       @   @@@@@     @@@@@@     @@@@@       ##\n" +
				"##     @      @    @@     @@   @    @    @     @    @    @      ##\n" +
				"##     @       @   @ @   @ @   @    @    @     @    @    @      ##\n" +
				"##     @       @   @  @ @  @   @@@@@@    @     @    @@@@@@      ##\n" +
				"##     @       @   @   @   @   @     @   @@@@@@     @     @     ##\n" +
				"##     @       @   @       @   @     @   @    @     @     @     ##\n" +
				"##     @      @    @       @   @     @   @     @    @     @     ##\n" +
				"##     @@@@@@@     @       @   @@@@@@    @      @   @@@@@@      ##\n" +
				"##                                                              ##\n" +
				"##################################################################\n";
	}
	
	public static void generate() {
		String pathToFiles = "src/main/resources/solidity/contracts/build/contracts";
		String pathForOutput = "src/main/java";
		String packageName = "com.dkohut.dmbrb.wrappers";
		
		WrapperGenerator.generateWrappers(pathToFiles, pathForOutput, packageName);
	}
	
}
