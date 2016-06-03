package com.aconex.codingchallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aconex.codingchallenge.internal.EnglishDictionary;

public class EnglishDictionaryTest {
	private static EnglishDictionary dictonary;

	@BeforeClass
	public static void setUpBeforeClass() {
		String path = "resources/dictonary/SimpleDictonary";
		dictonary = EnglishDictionary.getInstance(path);

	}

	@AfterClass
	public static void tearDownAfterClass() {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDictonaryInputFileExists() {
		assertTrue(dictonary.isFileExists());
	}

	@Test
	public void testLoadDictonary() throws Exception {
		dictonary.loadValuesFromFile();
		assertEquals(301, dictonary.getWords().size());
	}
}
