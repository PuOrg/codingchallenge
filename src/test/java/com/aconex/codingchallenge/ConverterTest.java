package com.aconex.codingchallenge;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aconex.codingchallenge.exception.CHException;
import com.aconex.codingchallenge.internal.Converter;
import com.aconex.codingchallenge.internal.EnglishDictionary;
import com.aconex.codingchallenge.internal.PhoneNumber;



public class ConverterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EnglishDictionary.getInstance("resources/dictionary/SimpleDictionary");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPhoneNumberToWordForOnlyZeroAndOnes() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("100-001-101-000");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		List<String> combinatons = numberToWord.convert();
		assertTrue(combinatons.contains("100001101000"));
	}

	@Test(expected = CHException.class)
	public void testPhoneNumberToWord1() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("2255.6388");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		numberToWord.convert();
	}

	@Test
	public void testPhoneNumberToWord2() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("3569377-1-800-35693770");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		List<String> combinatons = numberToWord.convert();
		assertTrue(combinatons.contains("FLOWERS-1-800-FLOWERS-0"));
	}

	@Test
	public void testPhoneNumberToWord3() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("2255.63");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		List<String> combinatons = numberToWord.convert();
		assertTrue(combinatons.contains("CALL-ME"));
	}

	@Test
	public void testPhoneNumberToWord4() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("1-800-263464-242553643");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		List<String> combinatons = numberToWord.convert();
		assertTrue(combinatons.contains("1-800-CODING-CHALLENGE"));
	}

	
	@Test
	public void testPhoneNumberToWord5() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("228-0228-212255");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		List<String> combinatons = numberToWord.convert();
		assertTrue(combinatons.contains("ACT-0-ACT-21-BALL"));
	}

	@Test
	public void testPhoneNumberToWord6() throws CHException {
		PhoneNumber phoneNo = new PhoneNumber("1-800-3569377");
		Converter numberToWord = new Converter(phoneNo.getcleanedNumber());
		List<String> combinatons = numberToWord.convert();
		assertTrue(combinatons.contains("1-800-FLOWERS"));
	}
}
