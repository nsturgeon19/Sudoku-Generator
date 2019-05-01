import java.util.Arrays;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    // There are two possible ways I can do this.
    // Either generate a puzzle from scratch:
//    int[][] sudokuPuzzle = genPuzzle();

    /*
    for (int row = 0; row < sudokuPuzzle.length; row++) {
      for (int column = 0; column < sudokuPuzzle[row].length; column++){
        System.out.print(sudokuPuzzle[row][column]);
      }
    }
    */

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

    printArray(solvedSudokuPuzle);

    // Creates a representation of the columns that need to be shuffled.
    int[][] columnsToShuffle = new int[9][3];
    for (int row = 0; row < solvedSudokuPuzle.length; row++){
      for (int column = 0; column < 3; column++){
        columnsToShuffle[row][column] = solvedSudokuPuzle[row][column];
      }
    }

    columnsToShuffle = shuffleColumns(columnsToShuffle);

    System.out.println();
    printArray(solvedSudokuPuzle);

    System.out.println();
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

  public static int[][] genPuzzle() {
    int[][] result = new int[9][9];
    Random rand = new Random();

    // Used for debugging. Counts each num as it is placed.
    int numPlaced = 0;

    for (int row = 0; row < result.length; row++) {
      for (int num = 1; num < 10; num++) {
        if (checkIfStalled(result,num,row) && row != 0) {
            clearRow(result,row);
            row--;
            break;
        }
        while (true) {

          // Chooses a random column in the array
          int column = rand.nextInt(9);

          // Checks if the number is free in the current column.
          boolean numFree = checkColumn(result,num,column);

          if (result[row][column] == 0 && numFree) {
            // Sets the column to the num in GenArray that we are at.
            result[row][column] = num;
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


    public static int[][] clearRow(int[][] puzzle, int row) {
      Arrays.fill(puzzle[row],0);
      return puzzle;
    }

    // checks a column for a specific number. If yes, return false.
    public static boolean checkColumn(int[][] puzzle, int number, int column) {
        for (int row = 0; row < puzzle.length;row++) {
            if (puzzle[row][column] == number) {
                return false;
            }
        }
        return true;
    }

  // Prints the sudoku puzzle out in grid-like form.
  public static void printArray (int[][] puzzle){
    for (int row = 0; row < puzzle.length; row++){
      System.out.println(Arrays.toString(puzzle[row]));
    }
  }

  // Checks if no more numbers can be placed in the row.
  public static boolean checkIfStalled (int[][] puzzle, int num, int row) {
    for (int column = 0; column < puzzle.length; column++){
      if (puzzle[row][column] == 0 && checkColumn(puzzle,num,column)){
        return false;
      }
    }
    return true;
  }

  // Checks if the row is correct according to the sudoku rules.
  public static boolean checkRow (int[][] puzzle, int row) {
    // If any equal 0, return false.
    for (int column = 0; column < puzzle[row].length; column++){
      if (puzzle[row][column] == 0){
        return false;
      }
    }

    // Adds 1 every single time a number is found on that space in the array.
    // e.x. a "5" adds 1 to index 4
    int[] rowNums = {0,0,0,0,0,0,0,0,0};
    for (int column = 0; column < puzzle[row].length; column++){
      int content = puzzle[row][column];
      rowNums[content-1]++;
    }

    // checks if we have any duplicate nums in rowNums. If yes, return false.
    for (int index = 0; index < rowNums.length; index++){
      if (rowNums[index] > 1) {
        return false;
      }
    }

    return true;
  }
}
