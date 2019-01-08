import java.util.concurrent.ThreadLocalRandom;

public class PuzzleGen {
	public static int[][] main () {
		// Note: +1 is used to make the top value exculsive
		int cellCount = ThreadLocalRandom.current().nextInt(17, 45 + 1);
		int[][] puzzle = new int[9][9];
		for (int count = 0; count < cellCount; count++) {
			puzzle[ThreadLocalRandom.current().nextInt(0, 8 + 1)][ThreadLocalRandom.current().nextInt(0, 8 + 1)] 
					= ThreadLocalRandom.current().nextInt(1, 9 + 1);
		}
		return puzzle;
	} 
}
