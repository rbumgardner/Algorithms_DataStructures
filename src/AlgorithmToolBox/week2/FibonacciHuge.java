package AlgorithmToolBox.week2;

import java.util.Scanner;

public class FibonacciHuge {
    private static long getFibonacciHuge(long n, long mod) {
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

        return modFib[(int) ((n % period))];
    }

    private static long fib(int n) {
     long[] fib = new long[n+2];
     fib[0] = 0;
     fib[1] = 1;
     for (int i = 2; i <= n; i++){
       fib[i] = fib[i-1] + fib[i-2];
     }
     return fib[n];
   }

    private static long getFibonacciHugeNaive(long n, long mod) {
        if (n <= 1) {
            return n;
        }

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % mod;
        }

        return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}

