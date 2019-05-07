import java.util.Arrays;
import java.util.Random;

public class genPuzzleTools {

  // Shuffles a list of numbers (1-9) and returns the result.
  public static int[] fisherYatesShuffle() {
    int[] result = {1,2,3,4,5,6,7,8,9};
    Random rand = new Random();
    for (int swapTo = result.length-1; swapTo > 1; swapTo--){
      int swapToContents = result[swapTo];
      int swapResult = rand.nextInt(result.length);
      result[swapTo] = result[swapResult];
      result[swapResult] = swapToContents;
    }
    return result;
  }

  // Takes 3 rows and shuffles them.
  public static int[][] shuffleRows (int[][] rowsToShuffle) {
    int[][] result = new int[3][9];
    int[] emptyRow = {0,0,0,0,0,0,0,0,0};
    Random rand = new Random();
    for (int rowCount = 0; rowCount < rowsToShuffle.length; rowCount++){
      while (true) {
        int rowPlacement = rand.nextInt(3);
        if (Arrays.equals(result[rowPlacement], emptyRow)) {
          result[rowPlacement] = rowsToShuffle[rowCount];
          break;
        }
      }
    }
    return result;
  }

  // Takes 3 columns and shuffles them.
  public static int[][] shuffleColumns (int[][] columnsToShuffle) {
    int[][] result = new int[9][3];
    Random rand = new Random();
    int[] indexOfColumns = new int[3];
    for (int number = 0; number < 3; number++) {
      int index = rand.nextInt(3);
      if (indexOfColumns[index] == 0) {
        indexOfColumns[index] = number;
      } else {
        number--;
      }
    }

    for (int column = 0; column < result[0].length; column++) {
      for (int row = 0; row < result.length; row++){
        result[row][column] = columnsToShuffle[row][indexOfColumns[column]];
      }
    }
    return result;
  }
}
