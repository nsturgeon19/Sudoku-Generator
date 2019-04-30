import java.util.Arrays;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    int[][] sudokuPuzzle = genPuzzle();
    System.out.println(Arrays.deepToString(sudokuPuzzle));
  }

  public static int[][] genPuzzle() {
    int[] genArray = {1,2,3,4,5,6,7,8,9};
    int[][] result = new int[9][9];
    Random rand = new Random();

    int numPlaced = 0;
    for (int row = 0; row < result.length; row++) {
      for (int num = 0; num < genArray.length; num++) {
        while (true) {
          // Chooses a random column in the array
          int column = rand.nextInt(9);

          // Checks if the number is free in the current column.
          boolean numFree = checkColumn(result,genArray[num],column);

          if (result[row][column] == 0 && numFree) {
            // Sets the column to the num in GenArray that we are at.
            result[row][column] = genArray[num];
            numPlaced++;
            System.out.println("Number placed"+numPlaced);
            printArray(result);
            break;
          }
        }
      }
    }
    return result;
  }

  public static boolean checkColumn(int[][] puzzle, int number, int column) {
    boolean result = true;
    for (int row = 0; row < puzzle.length;row++) {
      if (puzzle[row][column] == number) {
        result = false;
      }
    }
    return result;
  }

  // Prints the sudoku puzzle out in grid-like form.
  public static void printArray (int[][] puzzle){
    for (int row = 0; row < puzzle.length; row++){
      System.out.println(Arrays.toString(puzzle[row]));
    }
  }
}
