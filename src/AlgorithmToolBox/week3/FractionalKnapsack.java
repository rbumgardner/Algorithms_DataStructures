package AlgorithmToolBox.week3;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        double[] valuePerWeight = calculateValuePerWeight(values, weights);
        int i = 0;
        while (capacity > 0 && i < valuePerWeight.length) {
            double maxVPW = 0.0;
            int index = 0;
            for (int j = 0; j < valuePerWeight.length; j++) {
                if (valuePerWeight[j] > maxVPW) {
                    maxVPW = valuePerWeight[j];
                    index = j;
                }
            }
            valuePerWeight[index] = 0.0;
            int a = Math.min(capacity, weights[index]);
            value = value + a * maxVPW;
            capacity = capacity - a;
            i++;
        }
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(value));
    }

    private static double[] calculateValuePerWeight(int[] values, int[] weights) {
        double[] vpw = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            vpw[i] = (values[i]*1.0)/weights[i];
        }
        return vpw;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
