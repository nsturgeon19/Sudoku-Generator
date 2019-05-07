import java.util.Arrays;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    // Or shift an existing puzzle.
    int[][] solvedSudokuPuzle = {
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
    for(int box = 0; box < solvedSudokuPuzle.length; box += 3){
      // Picks a boxes' worth of rows.
      int[][] rowsInBox = {solvedSudokuPuzle[box],solvedSudokuPuzle[box+1],solvedSudokuPuzle[box+2]};
      rowsInBox = shuffleRows(rowsInBox);

      // gives the original puzzle the shuffled rows.
      for (int row = 0; row < 3; row++){
        solvedSudokuPuzle[box+row] = rowsInBox[row];
      }
    }

    // prints the array after row shuffle
    // printArray(solvedSudokuPuzle);
    System.out.println();

    for(int box = 0; box < solvedSudokuPuzle.length; box += 3) {
      // Creates a representation of the columns that need to be shuffled.
      int[][] columnsToShuffle = new int[9][3];
      for (int row = 0; row < solvedSudokuPuzle.length; row++) {
        for (int column = 0; column < 3; column++) {
          columnsToShuffle[row][column] = solvedSudokuPuzle[row][box+column];
        }
      }

      columnsToShuffle = shuffleColumns(columnsToShuffle);

      for (int row = 0; row < solvedSudokuPuzle.length; row++) {
        for (int column = 0; column < 3; column++) {
          solvedSudokuPuzle[row][box+column] = columnsToShuffle[row][column];
        }
      }
    }

    // Print after column shuffle.
    // printArray(solvedSudokuPuzle);
    System.out.println();

    int[] numShuffleKey = fisherYatesShuffle();
    for (int row = 0; row < solvedSudokuPuzle.length; row++){
      for (int column = 0; column < solvedSudokuPuzle[0].length; column++){
        solvedSudokuPuzle[row][column] = numShuffleKey[solvedSudokuPuzle[row][column]-1];
      }
    }

    // Print final puzzle
    printArray(solvedSudokuPuzle);
    System.out.println();
    printArrayRaw(solvedSudokuPuzle);
    System.out.println();

    int[][] notSolvedSudokuPuzzle = solvedSudokuPuzle;
    // Generates a random num of emptyCells from 40 to 60.
    int emptyCells = rand.nextInt(21) + 40;
    for (int removeNum = 0; removeNum < emptyCells; removeNum++){
      notSolvedSudokuPuzzle = removeRandom(notSolvedSudokuPuzzle);
    }

    System.out.println(emptyCells);
    printArray(notSolvedSudokuPuzzle);
    System.out.println();
    printArrayRaw(notSolvedSudokuPuzzle);
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

  // Prints a sudoku puzzle out in grid-like form.
  public static void printArray (int[][] puzzle){
    for (int row = 0; row < puzzle.length; row++){
      System.out.println(Arrays.toString(puzzle[row]));
    }
  }

  public static void printArrayRaw (int[][] puzzle) {
    for (int row = 0; row < puzzle.length; row++) {
      for (int column = 0; column < puzzle[0].length; column++) {
        System.out.print(puzzle[row][column]);
      }
    }
  }

}
