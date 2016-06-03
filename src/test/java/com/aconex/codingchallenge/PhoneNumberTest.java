package com.aconex.codingchallenge;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aconex.codingchallenge.internal.PhoneNumber;

public class PhoneNumberTest {

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
	public void testCleanedPhoneNumber() {
		PhoneNumber phoneNo = new PhoneNumber("2255.63");
		assertEquals("225563", phoneNo.getcleanedNumber());
	}

	@Test
	public void testCleanedPhoneNumber2() {
		PhoneNumber phoneNo = new PhoneNumber("225-56-3");
		assertEquals("225563", phoneNo.getcleanedNumber());
	}

	@Test
	public void testCleanedPhoneNumber3() {
		PhoneNumber phoneNo = new PhoneNumber("(011)225563");
		assertEquals("011225563", phoneNo.getcleanedNumber());
	}

}
