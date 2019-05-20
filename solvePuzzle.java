public class solvePuzzle {
  public static int[][][] removeBasedOnRow (int[][][] puzzle, int row, int column) {
    int[] candidates = puzzle[row][column];
    for (int index = 0; index < candidates.length; index++){
      if (candidates[index] != 0) {
        for (int puzzleColumn = 0; puzzleColumn < puzzle.length; puzzleColumn++) {
          if (puzzle[row][puzzleColumn][0] == candidates[index] && puzzle[row][puzzleColumn][1] == 0) {
            System.out.println("row removed " + candidates[index] + " at " + row + "," + column);
            candidates[index] = 0;
          }
        }
      }
    }
    candidates = Main.condenseArray(candidates);
    puzzle[row][column] = candidates;
    return puzzle;
  }

  public static int[][][] removeBasedOnColumn (int[][][] puzzle, int row, int column) {
    int[] candidates = puzzle[row][column];
    for (int index = 0; index < candidates.length; index++) {
      if (candidates[index] != 0) {
        for (int puzzleRow = 0; puzzleRow < puzzle[0].length; puzzleRow++) {
          if (puzzle[puzzleRow][column][0] == candidates[index] && puzzle[row][column][1] == 0) {
            System.out.println("column removed " + candidates[index] + " at " + row + "," + column);
            candidates[index] = 0;
          }
        }
      }
    }
    candidates = Main.condenseArray(candidates);
    puzzle[row][column] = candidates;
    return puzzle;
  }
}
