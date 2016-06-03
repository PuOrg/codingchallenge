package com.aconex.codingchallenge;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aconex.codingchallenge.exception.CHException;
import com.aconex.codingchallenge.internal.NumberInputHandler;

public class NumberInputHandlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testFileListForValidDirectoryPath() {
		try {
			List<String> files = NumberInputHandler.fileList("resources/input");
			assertEquals(2, files.size());
		} catch (CHException e) {
			fail("This shoudn't come here");
			e.printStackTrace();
		}

	}

	@Test(expected = CHException.class)
	public void testFileListForInValidDirectoryPath() throws CHException {
		NumberInputHandler.fileList("resources/input1");
		fail("This shoudn't come here");

	}

	@Test
	public void testLoadNumbersFromFileWithValidFile() throws CHException {
		Set<String> numbers = NumberInputHandler.loadNumbersFromFile("resources/input/file1");
		assertTrue(numbers.contains("2255.6388"));

	}

	@Test(expected = CHException.class)
	public void testLoadNumbersFromNonExistingInputFile() throws CHException {
		NumberInputHandler.loadNumbersFromFile("resources/input/file3");
		fail("This shoudn't come here");

	}

}
