package AlgorithmToolBox.week3;

import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        long sum = 0;
        int k = 1;
        while (sum < n) {
            if (sum + k <= n) {
                sum += k;
                summands.add(k);
            } else {
                break;
            }
            k++;
        }
        if (sum < n) {
            summands.set(k - 2, (int) (summands.get(k - 2) + (n - sum)));
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

