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
  public static boolean checkIfStalled (int[][] puzzle, int[] nums, int row) {
    for (int numIndex = 0; numIndex < nums.length; numIndex++) {
      for (int column = 0; column < puzzle.length; column++){
        if (puzzle[row][column] == 0 && checkColumn(puzzle,nums[numIndex],column)){
          return false;
        }
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
