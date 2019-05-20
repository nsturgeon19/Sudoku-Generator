import java.util.Arrays;

class Main {

  public static int makeCandidateCount = 0;
  public static int attemptNum = (81-40)*8;

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

    // Makes the puzzle based off of current solved squares.
    for (int row = 0; row < candidatePuzzle.length; row++) {
      for (int column = 0; column < candidatePuzzle[0].length; column++) {
        if (candidatePuzzle[row][column][0] == 0) {
          candidatePuzzle[row][column] = makeCandidates(candidatePuzzle, row, column);
        }
      }
    }

    System.out.println("Before");
    print3DArrayUF(candidatePuzzle);

    candidatePuzzle = removeEasyCandidates(candidatePuzzle);

//    System.out.println(Arrays.deepToString(candidatePuzzle));
//    print3DArray(candidatePuzzle);
    System.out.println("After");
    print3DArrayUF(candidatePuzzle);
    System.out.println("done");
  }

  public static int[][][] removeEasyCandidates(int[][][] puzzle) {
//    int[][][] oldPuzzle = puzzle;
//    System.out.println("oldPuzzle:");
//    print3DArrayUF(oldPuzzle);
    for (int attempt = 0; attempt < attemptNum; attempt++) {
      for (int row = 0; row < puzzle.length; row++) {
        for (int column = 0; column < puzzle.length; column++) {
          boolean testIt = puzzle[row][column][0] != 0 && puzzle[row][column][1] != 0;
          if (testIt) {
            puzzle = solvePuzzle.removeBasedOnRow(puzzle,row,column);
          }
          boolean testItStill = puzzle[row][column][0] != 0 && puzzle[row][column][1] != 0;
          if (testItStill) {
            puzzle = solvePuzzle.removeBasedOnColumn(puzzle,row,column);
          }
        }
      }
    }
//    System.out.println("candidatePuzzle");
//    print3DArrayUF(puzzle);
    return puzzle;
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

  // Finds the largest number of candidates in one box.
  public static int findLongestLength (int[][][] puzzle) {
    int result = 0;
    for (int row = 0; row < puzzle.length; row++) {
      for (int column = 0; column < puzzle[row].length; column++){
        int candidateCount = 0;
        for (int index = 0; index < puzzle[row][column].length; index++) {
          if (puzzle[row][column][index] == 0){
            break;
          } else {
            candidateCount += 1;
          }
        }
        if (candidateCount == 9) {
          return 9;
        } else if (candidateCount > result) {
          result = candidateCount;
        }
      }
    }
    return result;
  }

  // Prints a line that can fit the number of candidates in a puzzle, as provided by segLength.
  public static void printLineForUF (int segLength) {
    System.out.print(" ");
    for (int box = 0; box < 3; box++) {
      System.out.print("+");
      for (int i = 0; i < (segLength*3)+4; i++) {
        System.out.print("-");
      }
    }
    System.out.print("+");
    System.out.println();
  }
  
  // Prints the puzzle in a format that a observer can see an understand.
  public static void print3DArrayUF (int[][][] puzzle) {
    int boxWidth = findLongestLength(puzzle);
    System.out.println(boxWidth);
    // String horizontalBoxEdge = "| ";
    for (int row = 0; row < puzzle.length; row++) {
      if (row % 3 == 0) {
        printLineForUF(boxWidth);
      }
      for (int column = 0; column < puzzle[0].length; column++) {
        if (column % 3 == 0) {
          System.out.print("  ");
        }
        System.out.print(" ");
        for (int candidateColumn = 0; candidateColumn < boxWidth; candidateColumn++) {
          int candidate = puzzle[row][column][candidateColumn];
          if (candidate != 0) {
            System.out.print(candidate);
          } else {
            System.out.print(" ");
          }
        }
      }
      System.out.println();
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

  // Moves all the zeros to the front of the input array
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
