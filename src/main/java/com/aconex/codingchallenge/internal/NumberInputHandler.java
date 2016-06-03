package com.aconex.codingchallenge.internal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.codingchallenge.exception.CHException;

/**
 * This class manipulates the input files which contain phone numbers
 * 
 * @author Punnadi
 *
 */
public class NumberInputHandler {
	private final static Logger logger = Logger.getLogger(NumberInputHandler.class.getName());

	/**
	 * List all input files in the dirPath
	 * 
	 * @param dirPath
	 *            the provided input directory which contains the input files
	 * @return list of individual input files
	 * @throws CHException
	 */
	public static List<String> fileList(String dirPath) throws CHException {
		List<String> fileNames = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(dirPath))) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException e) {
			String msg = "Error while loading input files from directory " + dirPath;
			logger.log(Level.SEVERE, msg);
			throw new CHException(msg, e);
		}
		return fileNames;
	}

	/**
	 * Load phone numbers from the input file
	 * 
	 * @param filePath
	 *            input file path
	 * @return set of phone numbers loaded from the nput file
	 * @throws CHException
	 */
	public static Set<String> loadNumbersFromFile(String filePath) throws CHException {
		HashSet<String> numbers = new HashSet<>();
		try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
			while (input.ready()) {
				String number = input.readLine().trim();
				if (number.matches("^[a-zA-Z]+")) {
					logger.log(Level.WARNING, "Skipping an invalid number " + number);
				} else {
					numbers.add(number);
				}
			}
		} catch (IOException e) {
			String msg = "Error while loading the input numbers from file in path " + filePath;
			logger.log(Level.SEVERE, msg);
			throw new CHException(msg, e);
		}
		return numbers;
	}

}
