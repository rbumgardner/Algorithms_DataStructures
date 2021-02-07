package AlgorithmToolBox.week2;

import java.math.BigInteger;
import java.util.*;

public class FibonacciLastDigit {
    private static BigInteger getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);

        BigInteger previous = new BigInteger(String.valueOf(0));
        BigInteger current  = new BigInteger(String.valueOf(1));
        System.out.print("0,1,");

        for (int i = 0; i < n - 1; ++i) {
            BigInteger tmp_previous = previous;
            previous = current;
            current = (tmp_previous.add(current));
            System.out.print(current + ",");
        }
        System.out.println(" ");

        return current.mod(BigInteger.valueOf(10));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getFibonacciLastDigitNaive(n));
    }
}

