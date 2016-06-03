package com.aconex.codingchallenge;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.codingchallenge.exception.CHException;
import com.aconex.codingchallenge.internal.Constants;
import com.aconex.codingchallenge.internal.Converter;
import com.aconex.codingchallenge.internal.EnglishDictionary;
import com.aconex.codingchallenge.internal.NumberInputHandler;
import com.aconex.codingchallenge.internal.PhoneNumber;

/**
 * The entry point for the program which manage the number to word conversion
 * 
 * @author Punnadi
 *
 */
public class Client {
	
	private final static Logger logger = Logger.getLogger(Client.class.getName());

	public static void main(String[] args) throws CHException {
		String dictionaryPath = null;
		String inputDirPath = null;
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-d")) {
				dictionaryPath = args[i + 1];
			} else if (arg.equals("-i")) {
				inputDirPath = args[i + 1];
			} else if (arg.equals("-help")) {
				System.out.println("-i Full path to the input files directory <Optional>");
				System.out.println("-d Full path to the dictionary <Optional>");
				System.exit(0);
			}
		}
		if (dictionaryPath == null) {
			logger.log(Level.INFO, "No valid dictionary found. Using default "
					+ Paths.get(Constants.RESOURCES_DICTIONARY_SIMPLE_DICTIONARY).toAbsolutePath());
			dictionaryPath = Constants.RESOURCES_DICTIONARY_SIMPLE_DICTIONARY;
		}
		// Load the dictionary
		EnglishDictionary.getInstance(dictionaryPath);

		if (inputDirPath == null) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the phone number: ");
			String number = scanner.next().trim();
			scanner.close();
			if (number.matches(Constants.ALPHABHET_IN_CAPITAL_SIMPLE)) {
				System.out.println("Invalid number " + number);
				System.exit(0);
			}
			invoke(number);
		} else {
			if (Files.exists(Paths.get(inputDirPath))) {
				List<String> inputFileNames = NumberInputHandler.fileList(inputDirPath);
				for (String inputFileName : inputFileNames) {
					logger.log(Level.INFO, "Reading numbers from file " + inputFileName);
					Set<String> numbers = NumberInputHandler.loadNumbersFromFile(inputFileName);
					for (String number : numbers) {
						try {
							invoke(number);
						} catch (CHException e) {
							String msg = "Error occured for number " + number;
							logger.log(Level.INFO, msg);
						}
					}

				}
			} else {
				logger.log(Level.WARNING, "Invalid input directory path specified " + inputDirPath);
			}
		}
	}

	/**
	 * Invoke the transformation process for the given number
	 * 
	 * @param number
	 *            the phone number
	 * @throws CHException
	 */
	private static void invoke(String number) throws CHException {
		PhoneNumber phoneNo = new PhoneNumber(number);
		logger.log(Level.INFO, "Looking for conversions for the number " + number);
		Converter converter = new Converter(phoneNo.getcleanedNumber());
		List<String> conversions = converter.convert();
		for (String conversion : conversions) {
			System.out.println(number + "-> " + conversion);
		}
	}

}
