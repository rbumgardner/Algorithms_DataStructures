package AlgorithmToolBox.week6;

import java.util.Scanner;

public class Partition3 {
    private static int partition3(int[] A) {
        int n = A.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
        }
        if (sum % 3 != 0 || n < 3) {
            return 0;
        }

        boolean[][][] table = new boolean[n + 1][sum/3 + 1][sum/3 + 1];

        for (int i = 0; i <= n; i++) {
            table[i][0][0] = true;
        }
        for (int i = 1; i <= sum/3; i++) {
            for (int j = 1; j <= sum/3; j++) {
                table[0][i][j] = false;
            }
        }

        for (int i = 1; i <= n; i++) {
            int currentValue = A[i - 1];
            for (int j = 0; j <= sum/3; j++) {
                for (int k = 0; k <= sum/3; k++) {
                    if ((j - currentValue) >= 0) {
                        table[i][j][k] = table[i - 1][j][k] ||
                                table[i - 1][j - currentValue][k];
                    } else if ((k - currentValue) >= 0) {
                        table[i][j][k] = table[i - 1][j][k] ||
                                table[i - 1][j][k - currentValue];
                    } else {
                        table[i][j][k] = table[i - 1][j][k];
                    }
                }
            }
        }
        return table[n][sum/3][sum/3] ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

