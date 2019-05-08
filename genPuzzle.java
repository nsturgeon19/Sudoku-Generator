import java.util.Arrays;
import java.util.Random;

public class genPuzzle {

  public static int[][] genPuzzle(){
    // Or shift an existing puzzle.
    int[][] solvedSudokuPuzzle = {
            {1,2,3,4,5,6,7,8,9},
            {7,8,9,1,2,3,4,5,6},
            {4,5,6,7,8,9,1,2,3},
            {3,4,5,6,7,8,9,1,2},
            {9,1,2,3,4,5,6,7,8},
            {6,7,8,9,1,2,3,4,5},
            {5,6,7,8,9,1,2,3,4},
            {2,3,4,5,6,7,8,9,1},
            {8,9,1,2,3,4,5,6,7}
    };

    Random rand = new Random();

    // Shuffles the rows, one box at a time.
    for(int box = 0; box < solvedSudokuPuzzle.length; box += 3){
      // Picks a boxes' worth of rows.
      int[][] rowsInBox = {solvedSudokuPuzzle[box],solvedSudokuPuzzle[box+1],solvedSudokuPuzzle[box+2]};
      rowsInBox = genPuzzle.shuffleRows(rowsInBox);

      // gives the original puzzle the shuffled rows.
      for (int row = 0; row < 3; row++){
        solvedSudokuPuzzle[box+row] = rowsInBox[row];
      }
    }

    // prints the array after row shuffle
//     printArray(solvedSudokuPuzzle);
    System.out.println();

    // Shuffles the columns, one box at a time.
    for(int box = 0; box < solvedSudokuPuzzle.length; box += 3) {
      // Creates a representation of the columns that need to be shuffled.
      int[][] columnsToShuffle = new int[9][3];
      for (int row = 0; row < solvedSudokuPuzzle.length; row++) {
        for (int column = 0; column < 3; column++) {
          columnsToShuffle[row][column] = solvedSudokuPuzzle[row][box+column];
        }
      }

      columnsToShuffle = genPuzzle.shuffleColumns(columnsToShuffle);

      for (int row = 0; row < solvedSudokuPuzzle.length; row++) {
        for (int column = 0; column < 3; column++) {
          solvedSudokuPuzzle[row][box+column] = columnsToShuffle[row][column];
        }
      }
    }

    // Print after column shuffle.
    // printArray(solvedSudokuPuzzle);
    System.out.println();

    // shuffles the numbers in the puzzle
    int[] numShuffleKey = genPuzzle.fisherYatesShuffle();
    for (int row = 0; row < solvedSudokuPuzzle.length; row++){
      for (int column = 0; column < solvedSudokuPuzzle[0].length; column++){
        solvedSudokuPuzzle[row][column] = numShuffleKey[solvedSudokuPuzzle[row][column]-1];
      }
    }

    // Print final puzzle
    Main.printArray(solvedSudokuPuzzle);
    System.out.println();
    Main.printArrayRaw(solvedSudokuPuzzle);
    System.out.println();

    int[][] notSolvedSudokuPuzzle = solvedSudokuPuzzle;
    // Generates a random num of emptyCells from 40 to 60.
    int emptyCells = rand.nextInt(21) + 40;
    for (int removeNum = 0; removeNum < emptyCells; removeNum++){
      notSolvedSudokuPuzzle = genPuzzle.removeRandom(notSolvedSudokuPuzzle);
    }

    System.out.println(emptyCells);
    Main.printArray(notSolvedSudokuPuzzle);
    System.out.println();
    Main.printArrayRaw(notSolvedSudokuPuzzle);

    return notSolvedSudokuPuzzle;
  }

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

  // Removes one random number in the array passed in.
  public static int[][] removeRandom(int[][] puzzle) {
    Random rand = new Random();
    boolean setCorrectly = false;
    while (!setCorrectly) {
      int row = rand.nextInt(puzzle.length);
      int column = rand.nextInt(puzzle[0].length);
      if (puzzle[row][column] != 0) {
        puzzle[row][column] = 0;
        setCorrectly = true;
      }
    }
    return puzzle;
  }
}
