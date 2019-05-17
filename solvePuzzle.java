public class solvePuzzle {
  public static int[][][] removeBasedOnRow (int[][][] puzzle, int row, int column) {
    int[] candidates = puzzle[row][column];
    for (int index = 0; index < candidates.length; index++){
      if (candidates[index] != 0) {
        for (int rowColumn = 0; rowColumn < puzzle.length; rowColumn++) {
          if (puzzle[row][rowColumn][0] == candidates[index] && puzzle[row][rowColumn][1] == 0) {
            candidates[index] = 0;
          }
        }
      }
    }
    return puzzle;
  }
}
