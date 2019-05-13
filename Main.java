import java.util.Arrays;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    // Makes the puzzle to be solved.
    int[][] basicPuzzle = genPuzzle.genPuzzle();

    int[][][] candidatePuzzle = new int[9][9][9];

    // Adds a third dimension for candidates in the puzzle.
    for (int row = 0; row < candidatePuzzle.length; row++) {
      for (int column = 0; column < candidatePuzzle[0].length; column++){
        candidatePuzzle[row][column][0] = basicPuzzle[row][column];
      }
    }

    boolean going = true;
    while (going) {
      int[][][] oldPuzzle = candidatePuzzle;
      // Adds the candidates to the puzzle
      for (int row = 0; row < candidatePuzzle.length; row++) {
        for (int column = 0; column < candidatePuzzle[0].length; column++) {
          if (candidatePuzzle[row][column][0] == 0) {
            candidatePuzzle[row][column] = makeCandidates(candidatePuzzle, row, column);
          }
        }
      }
      if (Arrays.deepEquals(oldPuzzle,candidatePuzzle)) {
        going = false;
      }
    }

    System.out.println("done");
//    System.out.println(Arrays.deepToString(candidatePuzzle));
    print3DArray(candidatePuzzle);

  }

  // Prints a sudoku puzzle out in grid-like form.
  public static void printArray (int[][] puzzle){
    for (int row = 0; row < puzzle.length; row++){
      System.out.println(Arrays.toString(puzzle[row]));
    }
  }

  // Prints a sudoku puzzle in exportable form
  public static void printArrayRaw (int[][] puzzle) {
    for (int row = 0; row < puzzle.length; row++) {
      for (int column = 0; column < puzzle[0].length; column++) {
        System.out.print(puzzle[row][column]);
      }
    }
    System.out.println();
  }

  public static void print3DArray (int[][][] puzzle) {
    for (int row = 0; row < puzzle.length; row++) {
      System.out.println(Arrays.deepToString(puzzle[row]));
    }
  }

  // Checks for any easy eliminations before putting the possibilities in.
  public static int[] makeCandidates (int[][][] candidatePuzzle, int row, int column) {
    int[] candidatePoss = {1,2,3,4,5,6,7,8,9};

    // Checks for row eliminations
    for (int currColumn = 0; currColumn < candidatePuzzle.length; currColumn++){
      if (candidatePuzzle[row][currColumn][0] != 0 && candidatePuzzle[row][currColumn][1] == 0){
        candidatePoss[candidatePuzzle[row][currColumn][0]-1] = 0;
      }
    }

    // Checks for column eliminations
    for (int currRow = 0; currRow < candidatePuzzle[0].length; currRow++){
      if (candidatePuzzle[currRow][column][0] != 0 && candidatePuzzle[currRow][column][1] == 0){
        candidatePoss[candidatePuzzle[currRow][column][0]-1] = 0;
      }
    }

    // Checks for box eliminations
    int boxRow = row/3;
    int boxColumn = column/3;
    for (int currRow = boxRow*3; currRow < (boxRow+1)*3; currRow++) {
      for (int currColumn = boxColumn*3; currColumn < (boxColumn+1)*3; currColumn++){
        if (currRow/3 == boxRow && currColumn/3 == boxColumn) {
          if (candidatePuzzle[currRow][currColumn][0] != 0 && candidatePuzzle[currRow][currColumn][1] == 0) {
            candidatePoss[candidatePuzzle[currRow][currColumn][0]-1] = 0;
          }
        }
      }
    }

    candidatePoss = condenseArray(candidatePoss);

    return candidatePoss;
  }

  // Moves all the zero's to the front of the input array
  public static int[] condenseArray (int[] array){
    for (int index = 1; index < array.length; index++) {
      if (index == 0) {
        index++;
      }
      if (array[index]  != 0 && array[index-1] == 0){
        array[index-1] = array[index];
        array[index] = 0;
        index -= 2;
      }
    }
    return array;
  }

}
