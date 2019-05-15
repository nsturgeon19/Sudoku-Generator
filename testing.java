import java.util.Arrays;

public class testing {
  public static void main(String[] args) {
    String bad = "1";
    System.out.println(bad + 1);
//    System.out.print(" ");
    Main.printLineForUF(5);
    String num = "12345";
    for (int j = 0; j < 3; j++) {
      System.out.print("   " + num);
      for (int i = 0; i < 2; i++) {
        System.out.print(" " + num);
      }
    }
  }
}
