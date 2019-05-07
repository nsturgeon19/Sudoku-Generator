import java.util.Arrays;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

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
    printArray(solvedSudokuPuzzle);
    System.out.println();
    printArrayRaw(solvedSudokuPuzzle);
    System.out.println();

    int[][] notSolvedSudokuPuzzle = solvedSudokuPuzzle;
    // Generates a random num of emptyCells from 40 to 60.
    int emptyCells = rand.nextInt(21) + 40;
    for (int removeNum = 0; removeNum < emptyCells; removeNum++){
      notSolvedSudokuPuzzle = genPuzzle.removeRandom(notSolvedSudokuPuzzle);
    }

    System.out.println(emptyCells);
    printArray(notSolvedSudokuPuzzle);
    System.out.println();
    printArrayRaw(notSolvedSudokuPuzzle);


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
