import java.util.Arrays;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    int[][] basicPuzzle = genPuzzle.genPuzzle();

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
