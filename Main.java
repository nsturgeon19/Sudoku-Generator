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

    for (int row = 0; row < result.length; row++) {
      for (int index = 0; index < genArray.length; index++) {
        while (true) {
          // Chooses a random coloumn in the array
          int coloumn = rand.nextInt(9);

          // Checks if the number is free in the current coloumn.
          boolean numFree = checkColoumn(result,genArray[index],coloumn);

          if (result[row][coloumn] == 0 && numFree) {
            // Sets the coloumn to the index in GenArray that we are at.
            result[row][coloumn] = genArray[index];
            break;
          }
        }
      }
    }
    return result;
  }

  public static boolean checkColoumn (int[][] puzzle,int number,int coloumn) {
    boolean result = true;
    for (int row = 0; row < puzzle.length;row++) {
      if (puzzle[row][coloumn] == number) {
        result = false;
      }
    }
    return result;
  }
}
