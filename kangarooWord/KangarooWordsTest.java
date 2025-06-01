package assignments.module5;

import java.util.Scanner;

/**
 * This class tests the functionality of the KangarooWords class. It allows the
 * user to input words and determine if they form a Kangaroo word type. Then it
 * outputs the results.
 * 
 * @author Tran Ly
 * @version module 5 assignment
 * 
 */
public class KangarooWordsTest {
	/**
	 * The main method serves as the entry point for the program. It interacts with
	 * the user, takes input words, and determines the type of Kangaroo word. The
	 * program runs in a loop until the user chooses to exit.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		KangarooWords kangarooVerify = new KangarooWords();
		String answer = "Y";
		String[] split;
		String compares;
		String[] compareJWords;
		String words;
		boolean antiWords;
		System.out.print("Searching for Kangaroo words...\n");
		while (true) {
			if (answer.equalsIgnoreCase("N")) {
				System.out.println("\nGoodbye!");
				break;
			}
			System.out.print("\nInput words separated by a space: ");
			words = scan.nextLine().toLowerCase();
			split = kangarooVerify.split(words);
			antiWords = kangarooVerify.isAntiKangaroo(split);
			if (antiWords) {
				System.out.println(kangarooVerify.typeKangaroo(split));
			} else {
				if (split.length >= 4) {
					compares = kangarooVerify.grandKangaroo(split);
				} else {
					compares = kangarooVerify.twinKangaroo(split);
				}
				compareJWords = kangarooVerify.split(compares);

				if (kangarooVerify.verifying(compareJWords, split)) {
					System.out.println(kangarooVerify.typeKangaroo(split));
				} else {
					System.out.print("Invalid Kangaroo Word\n");
				}
			}

			System.out.print("\nContinue? ");
			answer = scan.nextLine();
		}
	}

}
