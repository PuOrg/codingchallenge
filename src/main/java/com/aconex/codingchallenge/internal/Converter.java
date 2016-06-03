package com.aconex.codingchallenge.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.codingchallenge.exception.CHException;

/**
 * Contains the core logic of the number to string transformation
 * 
 * @author Punnadi
 *
 */
public class Converter {
	final static Logger logger = Logger.getLogger(Converter.class.getName());
	private String phoneNumber;

	// Holds the number to word conversions, 0 and 1 or any single number
	List<List<String>> conversions = new ArrayList<>();

	public Converter(String phoneNo) {
		this.phoneNumber = phoneNo;
	}

	@SuppressWarnings("serial")
	public List<String> convert() throws CHException {
		if (phoneNumber.matches(Constants.ZEROS_ONES)) {
			return new ArrayList<String>(1) {
				{
					add(phoneNumber);
				}
			};
		}
		prepare(phoneNumber);
		return retrieveCombinations();
	}

	/**
	 * Divide the number by 0|1 into 3 parts and recursively do it until first
	 * and last parts contain no more 0|1
	 * 
	 * e.g: targetPhoneNumber - 1800263464242553643
	 * 
	 * first = []; middle = 1 ;last = 800263464242553643
	 * 
	 * 800263464242553643
	 * 
	 * first = 8; middle = 0 ;last = 0263464242553643
	 * 
	 * 0263464242553643
	 * 
	 * first=[]; middle=0 ;last =263464242553643
	 * 
	 * @param targetPhoneNumber
	 *            cleaned phone number
	 * @throws CHException
	 */
	private void prepare(String targetPhoneNumber) throws CHException {
		int i = targetPhoneNumber.indexOf("0") == -1 ? targetPhoneNumber.indexOf("1") : targetPhoneNumber.indexOf("0");
		if (i == -1) {
			convertToLetters(targetPhoneNumber);
		} else {
			int j = 0;
			int k = targetPhoneNumber.length();
			String first = targetPhoneNumber.substring(j, i);
			if (!first.equals(Constants.EMPTY_STRING))
				prepare(first);
			String middle = targetPhoneNumber.substring(i, i + 1);
			List<String> zeroOrOne = new ArrayList<>();
			zeroOrOne.add(middle);
			conversions.add(zeroOrOne);
			String last = targetPhoneNumber.substring(i + 1, k);
			if (!last.equals(Constants.EMPTY_STRING))
				prepare(last);
		}
	}

	/**
	 * Find matching words from the dictionary for the given number Get the i th
	 * character and load the encodings. Find all the matching words from
	 * dictionary where one of the retrieved encodings are in i th position of
	 * the word.
	 * 
	 * If possibleMatches is already empty, save the matches in possibleMatches
	 * Else find the commonMatches by comparing matches with possibleMatches. |
	 * |---If commonMatches found, replace possibleMatches with commonMatches
	 * |---Else retrieve the words from possibleMatches which has i+1 length and
	 * break the targetPhoneNumber from i to it's length and recursively call
	 * the convertToLetters again
	 * 
	 * @param targetPhoneNumber
	 *            Any number which has no 0|1
	 * @throws CHException
	 */
	private void convertToLetters(String targetPhoneNumber) throws CHException {
		List<String> possibleMatches = new ArrayList<>();
		if (targetPhoneNumber.length() == 1) {
			List<String> oneChar = new ArrayList<>();
			oneChar.add(targetPhoneNumber);
			conversions.add(oneChar);
			return;
		}
		for (int i = 0; i < targetPhoneNumber.length(); i++) {
			char number = targetPhoneNumber.charAt(i);
			List<String> encodings = NumberEncoding.getInstance()
					.getEncodingsForNumber(Character.getNumericValue(number));
			List<String> matches = getAllMatchingWordsFromDictonary(i, encodings, targetPhoneNumber.length());
			if (possibleMatches.size() == 0) {
				possibleMatches.addAll(matches);
			} else {
				List<String> commonMatches = new ArrayList<>();
				for (String match : matches) {
					if (possibleMatches.contains(match)) {
						commonMatches.add(match);
					}
				}
				if (commonMatches.size() > 0) {
					possibleMatches.clear();
					possibleMatches.addAll(commonMatches);
					if (targetPhoneNumber.length() == i + 1) {
						List<String> currentMatches = new ArrayList<>();
						for (String match : possibleMatches) {
							if (match.length() == i + 1) {
								currentMatches.add(match);
							}
						}
						conversions.add(currentMatches);
					}
				} else {
					List<String> currentMatches = new ArrayList<>();
					for (String match : possibleMatches) {
						if (match.length() == i) {
							currentMatches.add(match);
						} else {
							String msg = "Cannot find a match for " + phoneNumber + " as it has two adjecent numbers "
									+ targetPhoneNumber.substring(0, i + 1) + " which canot be transformed to letters";
							logger.log(Level.WARNING, msg);
							throw new CHException(msg);
						}
					}
					conversions.add(currentMatches);
					List<String> specialChar = new ArrayList<>();
					specialChar.add(Constants.HYPHEN);
					conversions.add(specialChar);
					String remaining = targetPhoneNumber.substring(i, targetPhoneNumber.length());
					convertToLetters(remaining);

					// Need to break the loop since same function is being
					// called recursively above
					break;
				}
			}
		}
	}

	/**
	 * Find all the matching words from dictionary where one of the retrieved
	 * encodings are in i th position of the word.
	 * 
	 * @param i
	 *            the position zero based
	 * @param encodings
	 *            the number encodings of a given number
	 * @param numberLength
	 *            the maximum length of the given number
	 * @return
	 */
	private List<String> getAllMatchingWordsFromDictonary(int i, List<String> encodings, int numberLength) {
		List<String> matches = new ArrayList<>();
		EnglishDictionary englishDictonary = EnglishDictionary.getInstance(Constants.EMPTY_STRING);
		Set<String> words = englishDictonary.getWords();
		for (String word : words) {
			int length = word.length();
			if (length <= numberLength && length > i && encodings.contains(Character.toString(word.charAt(i)))) {
				matches.add(word);
			}
		}
		return matches;
	}

	/**
	 * Construct all the combinations
	 * 
	 * @return
	 */
	private List<String> retrieveCombinations() {
		List<StringBuffer> finals = new ArrayList<>();
		for (int i = 0; i < conversions.size(); i++) {
			List<String> conversion = conversions.get(i);
			if (i == 0) {
				for (String item : conversion) {
					StringBuffer buffer = new StringBuffer(item);
					finals.add(buffer);
				}
			} else {
				List<StringBuffer> temp = new ArrayList<>();
				for (StringBuffer buff : finals) {
					for (String item : conversion) {
						String existingItem = buff.toString();
						StringBuffer buffer = new StringBuffer();
						if (isHyphenRequired(item, existingItem)) {
							buffer.append(existingItem + Constants.HYPHEN + item);
						} else {
							buffer.append(existingItem + item);
						}
						temp.add(buffer);
					}
				}
				finals.clear();
				finals.addAll(temp);
			}
		}
		List<String> results = new ArrayList<>(finals.size());
		for (StringBuffer buff : finals) {
			results.add(buff.toString());
		}
		return results;
	}

	private boolean isHyphenRequired(String item, String existingItem) {
		return (existingItem.substring(existingItem.length() - 1).matches(Constants.ZERO_OR_ONE_ONLY)
				&& item.substring(0, 1).matches(Constants.NUMBERS_WITH_ENCODING))
				|| (existingItem.substring(existingItem.length() - 1).matches(Constants.ZERO_OR_ONE_ONLY)
						&& item.substring(0, 1).matches(Constants.ALPHABHET))
				|| (existingItem.substring(existingItem.length() - 1).matches(Constants.ALPHABHET)
						&& item.substring(0, 1).matches(Constants.ZERO_OR_ONE_ONLY));
	}

}
