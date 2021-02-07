package AlgorithmToolBox.week5;

import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        int[] coins = {1,3,4};
        int[] table = new int[m + 1];
        table[0] = 0;
        for (int i = 1; i <= m; i++) {
            table[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j  < coins.length; j++) {
                if (coins[j] <= i) {
                    int temp = table[i - coins[j]];
                    if (temp != Integer.MAX_VALUE && temp + 1 < table[i]) {
                        table[i] = temp + 1;
                    }
                }
            }
        }
        return table[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

