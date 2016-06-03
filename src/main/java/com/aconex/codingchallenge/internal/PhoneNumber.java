package com.aconex.codingchallenge.internal;

/**
 * Holds the phone number
 * 
 * @author Punnadi
 *
 */
public class PhoneNumber {
	private String number;

	public PhoneNumber(String number) {
		this.number = number;
	}

	/**
	 * This remove the special characters or any other characters except the
	 * numbers from the phone number
	 * 
	 * @return the cleaned phone number with numbers only
	 */
	public String getcleanedNumber() {
		return number.replaceAll("[^0-9]+", "");
	}
}
