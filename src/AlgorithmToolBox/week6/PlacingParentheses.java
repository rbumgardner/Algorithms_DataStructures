package AlgorithmToolBox.week6;

import java.util.Scanner;

public class PlacingParentheses {

    private static long getMaximValue(String exp) {
        int n = exp.length() / 2 + 1;
        long[][] min = new long[n][n], max = new long[n][n];
        for (int i = 0; i < n; i++) {
            min[i][i] = exp.charAt(i * 2) - '0';
            max[i][i] = exp.charAt(i * 2) - '0';
        }

        for (int s = 1; s <= n - 1; s++) {
            for (int i = 0; i <= n - 1 - s; i++) {
                int j = s + i;
                long[] res = getMinAndMax(exp, i, j, min, max);
                min[i][j] = res[0];
                max[i][j] = res[1];
            }
        }
        return max[0][n - 1];
    }

    private static long[] getMinAndMax(String exp, int i, int j, long[][] min, long[][] max) {
        long[] res = new long[]{Long.MAX_VALUE, Integer.MIN_VALUE};
        for (int k = i; k <= j - 1; k++) {
            char oper = exp.charAt(k * 2 + 1);
            long a = eval(min[i][k], min[k + 1][j], oper),
                    b = eval(min[i][k], max[k + 1][j], oper),
                    c = eval(max[i][k], min[k + 1][j], oper),
                    d = eval(max[i][k], max[k + 1][j], oper);
            res[0] = Math.min(a, Math.min(b, Math.min(c, Math.min(d, res[0]))));
            res[1] = Math.max(a, Math.max(b, Math.max(c, Math.max(d, res[1]))));
        }
        return res;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

