package AlgorithmToolBox.week6;

import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] bars) {
        int[][] value = new int[W + 1][bars.length + 1];
        for (int i = 0; i <= bars.length; i++) {
            value[0][i] = 0;
        }
        for (int i = 0; i <= W; i++) {
            value[i][0] = 0;
        }
        int val = 0;
        for (int i = 1; i <= bars.length; i++) {
            for (int w = 0; w <= W; w++) {
                value[w][i] = value[w][i-1];
                if (bars[i - 1] <= w) {
                    val = value[w - bars[i - 1]][i - 1] + bars[i - 1];
                    if (value[w][i] < val) {
                        value[w][i] = val;
                    }
                }
            }
        }
//        int result = 0;
//        for (int i = 0; i < w.length; i++) {
//          if (result + w[i] <= W) {
//            result += w[i];
//          }
//        }
        return value[W][bars.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

