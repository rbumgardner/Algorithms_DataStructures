package AlgorithmToolBox.week3;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int numCoins = 0;
        while (m > 0) {
            if (m >= 10) {
                m -= 10;
            } else if (m >= 5) {
                m -= 5;
            } else {
                m = m -1;
            }
            numCoins++;
        }
        return numCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

