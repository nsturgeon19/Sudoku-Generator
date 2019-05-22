import java.util.Arrays;

public class testing {
  public static void main(String[] args) {
    int[][] badPuzzle = {
            {0,2,7,0,3,1,5,4,0},
            {0,0,0,0,0,8,0,7,6},
            {0,5,0,0,0,0,9,0,1},
            {0,0,0,0,1,0,0,8,0},
            {0,0,0,0,0,5,0,0,0},
            {0,3,1,0,0,9,0,0,5},
            {0,0,5,0,2,0,0,0,0},
            {7,1,2,0,0,0,6,5,4},
            {0,0,0,6,5,4,0,2,0}
    };
    int[][][] candidatePuzzle = new int[9][9][9];

    Main.printArrayRaw(badPuzzle);

    // Adds a third dimension for candidates in the puzzle.
    for (int row = 0; row < candidatePuzzle.length; row++) {
      for (int column = 0; column < candidatePuzzle[0].length; column++){
        candidatePuzzle[row][column][0] = badPuzzle[row][column];
      }
    }

    // Makes the puzzle based off of current solved squares.
    for (int row = 0; row < candidatePuzzle.length; row++) {
      for (int column = 0; column < candidatePuzzle[0].length; column++) {
        if (candidatePuzzle[row][column][0] == 0) {
          candidatePuzzle[row][column] = Main.makeCandidates(candidatePuzzle, row, column);
        }
      }
    }

//    System.out.println("Before");
//    Main.print3DArrayUF(candidatePuzzle);

    candidatePuzzle = Main.removeEasyCandidates(candidatePuzzle);

//    System.out.println(Arrays.deepToString(candidatePuzzle));
//    print3DArray(candidatePuzzle);
//    System.out.println("After");
//    Main.print3DArrayUF(candidatePuzzle);
//    System.out.println("done");
  }
}
