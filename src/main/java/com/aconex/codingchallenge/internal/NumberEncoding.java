package com.aconex.codingchallenge.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A singleton class which returns predefined Number to Letter Mappings
 * 
 * @author Punnadi
 *
 */
public class NumberEncoding {
	private Map<Integer, List<String>> encodings;
	private static NumberEncoding numberEncoding;

	public static NumberEncoding getInstance() {
		if (numberEncoding == null) {
			numberEncoding = new NumberEncoding();
		}
		return numberEncoding;
	}

	public List<String> getEncodingsForNumber(int number) {
		return encodings.get(number);
	}

	@SuppressWarnings("serial")
	private NumberEncoding() {
		encodings = new HashMap<>();
		List<String> charsForTwo = new ArrayList<String>() {
			{
				add("A");
				add("B");
				add("C");
			}
		};
		encodings.put(2, charsForTwo);
		List<String> charsForThree = new ArrayList<String>() {
			{
				add("D");
				add("E");
				add("F");
			}
		};
		encodings.put(3, charsForThree);
		List<String> charsForFour = new ArrayList<String>() {
			{
				add("G");
				add("H");
				add("I");
			}
		};
		encodings.put(4, charsForFour);
		List<String> charsForFive = new ArrayList<String>() {
			{
				add("J");
				add("K");
				add("L");
			}
		};
		encodings.put(5, charsForFive);
		List<String> charsForSix = new ArrayList<String>() {
			{
				add("M");
				add("N");
				add("O");
			}
		};
		encodings.put(6, charsForSix);
		List<String> charsForSeven = new ArrayList<String>() {
			{
				add("P");
				add("Q");
				add("R");
				add("S");
			}
		};
		encodings.put(7, charsForSeven);
		List<String> charsForEight = new ArrayList<String>() {
			{
				add("T");
				add("U");
				add("V");
			}
		};
		encodings.put(8, charsForEight);
		List<String> charsForNine = new ArrayList<String>() {
			{
				add("W");
				add("X");
				add("Y");
				add("Z");
			}
		};
		encodings.put(9, charsForNine);
	}

}
