package AlgorithmToolBox.week2;

import java.util.Scanner;

public class FibonacciSumLastDigit {
    private static long getFibonacciSum(long n) {
        int mod = 10;
        if (n <= 1L) {
            return n;
        }

        long[] modFib = new long[(int) (mod*mod+1)];
        modFib[0] = 0;
        modFib[1] = 1;
        long period = 0;

        for (int i = 2; i < mod * mod + 1; ++i) {
            modFib[i] = ((modFib[i-2] % mod) + (modFib[i-1] % mod)) % mod;
            if (modFib[i-1] == 0 && modFib[i] == 1) {
                period = i - 1;
                break;
            }
        }

        if (modFib[(int) ((n+2) % period)] == 0) {
            return 9;
        } else {
            return modFib[(int) ((n+2) % period)] -1;
        }
    }

    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        System.out.println(getFibonacciSum(n));
    }
}

