package com.aconex.codingchallenge.internal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.codingchallenge.exception.CHException;

/**
 * Singleton class to load Dictionary
 * 
 * @author Punnadi
 *
 */
public class EnglishDictionary {
	private final static Logger logger = Logger.getLogger(EnglishDictionary.class.getName());
	private static EnglishDictionary dictonary;
	private String filePath;
	private Set<String> words = new HashSet<>();

	private EnglishDictionary(String path) throws CHException {
		this.filePath = path;
		if (isFileExists()) {
			loadValuesFromFile();
		} else {
			String msg = "Sample English Dictonary cannot be found in the provided path " + filePath;
			logger.log(Level.SEVERE, msg);
			throw new CHException(msg);
		}
	}

	public boolean isFileExists() {
		File path = new File(filePath);
		return path.exists();
	}

	/**
	 * Load the dictoinary values from given input file
	 * 
	 * @throws CHException
	 */
	public void loadValuesFromFile() throws CHException {

		try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
			while (input.ready()) {
				String word = input.readLine().trim().toUpperCase();
				if (word.length() > 1)
					words.add(word);
			}
		} catch (IOException e) {
			String msg = "Error while loading the dictonary values from file in path " + filePath;
			logger.log(Level.SEVERE, msg);
			throw new CHException(msg, e);
		}

	}

	/**
	 * Returns the singleton instance
	 * 
	 * @param path
	 *            dictionary file path
	 * @return
	 */
	public static EnglishDictionary getInstance(String path) {
		if (dictonary == null) {
			try {
				dictonary = new EnglishDictionary(path);
			} catch (CHException e) {
				String msg = "Error while loading dictoionary " + path;
				logger.log(Level.SEVERE, msg);
				System.exit(0);
			}
		}
		return dictonary;
	}

	public Set<String> getWords() {
		return words;
	}

}
