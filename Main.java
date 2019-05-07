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
      rowsInBox = genPuzzleTools.shuffleRows(rowsInBox);

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

      columnsToShuffle = genPuzzleTools.shuffleColumns(columnsToShuffle);

      for (int row = 0; row < solvedSudokuPuzle.length; row++) {
        for (int column = 0; column < 3; column++) {
          solvedSudokuPuzle[row][box+column] = columnsToShuffle[row][column];
        }
      }
    }

    // Print after column shuffle.
    // printArray(solvedSudokuPuzle);
    System.out.println();

    int[] numShuffleKey = genPuzzleTools.fisherYatesShuffle();
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
      notSolvedSudokuPuzzle = genPuzzleTools.removeRandom(notSolvedSudokuPuzzle);
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
