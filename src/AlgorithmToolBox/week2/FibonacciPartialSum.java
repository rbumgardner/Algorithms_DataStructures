package AlgorithmToolBox.week2;

import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSum(long from, long to) {
        int mod = 10;
        if (to <= 1L) {
            return to;
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

//        System.out.println(modFib[(int) ((from+1) % period)]);
//        System.out.println(modFib[(int) ((to+2) % period)]);

        if (from == to) {
            return modFib[(int) ((to % period))];
        } else if (modFib[(int) ((from+1) % period)] > modFib[(int) ((to+2) % period)]) {
            return modFib[(int) ((to+2) % period)] - modFib[(int) ((from+1) % period)] + 10;
        } else {
            return modFib[(int) ((to+2) % period)] - modFib[(int) ((from+1) % period)];
        }
    }

    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

