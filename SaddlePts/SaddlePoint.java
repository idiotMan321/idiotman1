package projects;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Tran Ly This project generates a 3x3 array of random numbers and
 *         identifies a saddle point. A saddle point is defined as the smallest
 *         number in a row that is also the largest number in one of the columns
 * 
 */
public class SaddlePoint {
	/**
	 * 
	 * Main method calls other method to generates a 3x3 array, finds and displays
	 * the saddle point, and rotates the array.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		byte arrayCreateFromUser = scan.nextByte();
		byte[][] n = createArray((byte) 3);
		displaySaddlePoint(n);
	}

	/**
	 * 
	 * Creates a 2D array filled with random numbers within a user input range.
	 * 
	 */
	public static byte[][] createArray(byte arrayCreateFromUser) {
		
		byte userArray[][] = new byte[arrayCreateFromUser][arrayCreateFromUser];
		Random rand = new Random();
		int[] rangeNum = assignValuesToArray();
		byte randomGenerator = 0;
		for (int rows = 0; rows < arrayCreateFromUser; rows++) {
			for (int columns = 0; columns < arrayCreateFromUser; columns++) {
				randomGenerator = (byte) rand.nextInt(rangeNum[0], rangeNum[1]);
				userArray[rows][columns] = randomGenerator;
			}
		}
		return userArray;
	}

	/**
	 * 
	 * Ask the user to input the lower and upper range for random numbers.
	 */
	public static int[] assignValuesToArray() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input range 1: ");
		int lower = scan.nextInt();
		System.out.println("Input range 2: ");
		int upper = scan.nextInt();
		int secondVal;
		if (lower >= 0 && upper >= 0 && lower < upper) {
			return new int[] { lower, upper };
		}
		return null;
	}

	/**
	 * Displays 2D arrays
	 */
	public static void displayArray(byte[][] n) {

		for (int rows = 0; rows < n.length; rows++) {
			for (int columns = 0; columns < n[0].length; columns++) {
				System.out.printf("%d ", n[rows][columns]);

			}
			System.out.println();
		}
	}

	/**
	 * Here method find the saddle point of a 2D array. The smallest number in a row
	 * that is also the largest number in one of the columns.
	 * 
	 */
	public static int[] findSaddlePoint(byte[][] n) {
		int rows = -1;
		int columns = -1;

		int[] groupSmallestVal = new int[n.length];
		for (int i = 0; i < n.length; i++) {
			int smallestVal = n[i][0];
			for (int j = 1; j < n[i].length; j++) {
				if (n[i][j] < smallestVal) {
					smallestVal = n[i][j];
				}
			}
			groupSmallestVal[i] = smallestVal;
		}
		int greatest = 0;

		for (int i = 0; i < groupSmallestVal.length; i++) {
			if (groupSmallestVal[i] > greatest) {
				greatest = groupSmallestVal[i];
			}
		}
		int maxVal = -1;
		int saddlePoint = greatest;
		for (int col = 0; col < n[0].length; col++) {
			for (int row = 0; row < n.length; row++) {
				if (maxVal < n[row][col]) {
					maxVal = n[row][col];
					rows = row;
					columns = col;
				}

			}
			if (maxVal == saddlePoint) {
				columns = col;
				return new int[] { maxVal, rows, columns };
			}
		}
		return new int[] { -1, rows, columns };
	}

	/**
	 * Displays the saddle point of the array and rotates the array by 90 degrees.
	 * It also finds the saddle point in both the original and rotated arrays.
	 * 
	 */
	public static void displaySaddlePoint(byte[][] n) {
		int[] saddlePoint = findSaddlePoint(n);
		int result = saddlePoint[0];
		int row = saddlePoint[1];
		int col = saddlePoint[2];
		System.out.println("\nFinding the Saddle Point for a given 2D array...\n\nThe generated array\r\n" + " ");
		displayArray(n);
		if (result != -1) {
			System.out.printf("\nThe Saddle Point of the array is located at row %d column %d: %d \r\n", row, col,
					result);
		} else {
			System.out.println("\nThere is no Saddle Point in original array.");
		}
		System.out.println("\nRotated array by 90 degrees\n");

		rotateArray(n);
		saddlePoint = findSaddlePoint(n);
		result = saddlePoint[0];
		row = saddlePoint[1];
		col = saddlePoint[2];
		displayArray(n);

		if (result != -1) {
			System.out.printf("\nThe Saddle Point of the array is located at row %d column %d: %d \r\n", row, col,
					result);

		} else {
			System.out.printf("\nThere is no Saddle Point in rotated array.");
		}
	}

	/**
	 * Rotates a square 2D array by 90 degrees clockwise.
	 */
	public static void rotateArray(byte[][] n) {
		byte[][] rotated = new byte[n.length][n.length];

		int rowRotated = 0;
		int colRotated = n.length - 1;
		for (int row = 0; row < n.length; row++) {
			rowRotated = 0;
			for (int col = 0; col < n.length; col++) {
				rotated[rowRotated][colRotated] = n[row][col];
				rowRotated++;
			}
			colRotated--;
		}
		for (int row = 0; row < n.length; row++) {
			for (int col = 0; col < n.length; col++) {
				n[row][col] = rotated[row][col];
			}
		}

	}
}
