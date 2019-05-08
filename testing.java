import java.util.Arrays;

public class testing {
  public static void main(String[] args) {
    int[] test = {0,2,0,0,5,6,0,8,9};
    test = Main.condenseArray(test);
    System.out.println(Arrays.toString(test));
  }
}
