package assignments.module5;

/**
 * This class determines whether a given word or set of words is a Kangaroo
 * word, Twin Kangaroo word, Grand Kangaroo word, or an Anti-Kangaroo word. A
 * Kangaroo word contains a smaller word within it that has a similar meaning.
 * An Anti-Kangaroo word contains an antonym of another word within it.
 * 
 * @author Tran Ly
 * @version module 5 assignment
 */
public class KangarooWords {
	/**
	 * This is extra credit asssignment that check if user input a pair of these
	 * word then it'll output anti-kangaroo word
	 */
	private String[][] antonymsList = {

			{ "abuse", "use" }, { "animosity", "amity" }, { "bearded", "bare" }, { "communicative", "mute" },
			{ "convent", "coven" }, { "courteous", "curt" }, { "fabrication", "fact" }, { "feast", "fast" },
			{ "female", "male" }, { "friend", "fiend" }, { "iconoclast", "icon" }, { "pest", "pet" },
			{ "prudent", "rude" }, { "prurient", "pure" }, { "resigned", "reigned" }, { "she", "he" },
			{ "there", "here" }, { "threat", "treat" }, { "wonderful", "woeful" } };

	/**
	 * Checks if the given words form an "Anti-Kangaroo" pair. An Anti-Kangaroo word
	 * is pair of antonyms.
	 *
	 * @param str Array containing two words to check.
	 * @return true if the words are antonyms from the list, otherwise is false.
	 */
	public boolean isAntiKangaroo(String[] str) {
		String row;
		String col;
		for (int i = 0; i < antonymsList.length; i++) {
			row = antonymsList[i][0];
			col = antonymsList[i][1];
			if (str[0].equalsIgnoreCase(row) && str[1].equalsIgnoreCase(col)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Splits a given string into an array of words.
	 *
	 * @param str The input string.
	 * @return An array of words from the input string.
	 */
	public String[] split(String str) {
		String[] wordsArray = str.split(" ");
		return wordsArray;
	}

	/**
	 * Determines the "Grand Kangaroo" word by finding common letters between each
	 * pair of words in the input.
	 *
	 * @param str Array of words to process.
	 * @return A string of extracted letters forming the Grand Kangaroo.
	 */
	public String grandKangaroo(String[] str) {
		String word1 = str[0];
		String storingJWords = "";
		String[] eachWord = new String[str.length];
		for (int x = 0; x < str.length; x++) {
			eachWord[x] = str[x];
			for (int i = 1 + x; i < str.length; i++) {
				eachWord[i] = str[i];
				for (int k = 0; k < eachWord[x].length(); k++) {
					for (int j = 0; j < eachWord[i].length(); j++) {
						if (eachWord[x].charAt(k) == eachWord[i].charAt(j)) {
							storingJWords += eachWord[x].charAt(k);
							j++;
							break;
						}
					}

				}
				break;
			}
			storingJWords += " ";
		}
		return storingJWords;
	}

	/**
	 * Determines the "Twin Kangaroo" word by finding common letters between the
	 * first word and each subsequent word in order.
	 *
	 * @param str Array of words to process.
	 * @return A string containing Twin Kangaroo words.
	 */
	public String twinKangaroo(String[] str) {
		String word1 = str[0];
		String storingJWords = "";
		int cnt = 0;
		int cnts = 0;
		String[] eachWord = new String[str.length];
		for (int i = 1; i < str.length; i++) {
			cnt = 0;
			cnts = 0;
			for (int j = 0; j < str[i].length(); j++) {
				cnts = cnt;
				for (int k = cnts; k < word1.length(); k++) {
					cnt += 1;
					if (word1.charAt(k) == str[i].charAt(j)) {
						storingJWords += str[i].charAt(j);

						break;
					}
				}
			}
			storingJWords += " ";
		}
		return storingJWords;

	}

	/**
	 * Verifies if any words from the extracted "JWords" list match the original
	 * input words.
	 *
	 * @param verifyJWords The extracted words.
	 * @param str          The original input words.
	 * @return true if a match is found, false otherwise.
	 */
	public boolean verifying(String[] verifyJWords, String[] str) {
		boolean verify = false;
		for (int i = 0; i < verifyJWords.length; i++) {
			verifyJWords[i] = verifyJWords[i];
			for (int j = 1 + i; j < str.length; j++) {
				if (verifyJWords[i].trim().equalsIgnoreCase(str[j].trim())) {
					verify = true;
					break;
				} else {
					verify = false;
					return verify;

				}
			}
		}
		return verify;
	}

	/**
	 * Determines the type of Kangaroo word based on the given words.
	 *
	 * @param str Array of words.
	 * @return A string indicating the Kangaroo word type.
	 */
	public String typeKangaroo(String[] str) {
		boolean antiWord = isAntiKangaroo(str);
		if (antiWord) {
			return "Anti-Kangaroo Word";
		} else {
			if (str.length >= 4) {
				return "Grand Kangaroo";
			} else if (str.length >= 3) {
				return "Twin Kangaroo";
			} else {
				return "Kangaroo";
			}
		}

	}
}