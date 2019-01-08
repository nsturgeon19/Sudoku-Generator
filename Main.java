import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Scans for input
		Scanner input = new Scanner(System.in);
		System.out.println("Hi");
		System.out.println("How many solving strategies do you want to use?");
		int strategyNum = input.nextInt();
		String[] inputStrategies = new String[strategyNum];
		for (int i = 0; i < inputStrategies.length; i++) {
			System.out.println("What is one strategy that you want to use? (No spaces, camelcase.)");
			inputStrategies[i] = input.next();
		}
		// Prints out inputs for debugging purposes
		System.out.println("Your chosen strategies are: " + Arrays.toString(inputStrategies));
		
		// Makes a puzzle
		int[][] result = PuzzleGen.main();
		
		// Prints out the puzzle for debugging purposes.
		System.out.println(Arrays.deepToString(result));
	}

}
