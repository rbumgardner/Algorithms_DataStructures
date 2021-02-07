package AlgorithmToolBox.week2;

import java.math.BigInteger;
import java.util.*;

public class LCM {
  private static BigInteger lcm_better(long a, long b) {
    BigInteger lcm = BigInteger.valueOf(a/gcd(a, b) * b);
    return lcm;
  }

  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l) {
      if (l % a == 0 && l % b == 0) {
        return l;
      }
    }

    return (long) a * b;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_better(a, b));
  }

  private static long gcd(long a, long b) {
    long mod = 1;
    if (b > a) {
      long tmp = b;
      b = a;
      a = tmp;
    }
    while (b != 0) {
      mod = a % b;
      a = b;
      b = mod;
    }
    return a;
  }
}
