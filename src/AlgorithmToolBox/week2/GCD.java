package AlgorithmToolBox.week2;

import java.util.*;

public class GCD {

  private static int gcd_naive(int a, int b) {
//    int current_gcd = 1;
    int mod = 1;
    if (b > a) {
      int tmp = b;
      b = a;
      a = tmp;
    }
    while (b != 0) {
      mod = a % b;
      a = b;
      b = mod;
    }

    //    for(int d = 2; d <= a && d <= b; ++d) {
//      if (a % d == 0 && b % d == 0) {
//        if (d > current_gcd) {
//          current_gcd = d;
//        }
//      }
//    }
//
    return a;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd_naive(a, b));
  }
}
