package AlgorithmToolBox.week1;

import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(long[] numbers) {
        int max1_index = 0;
        int max2_index = 0;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[max1_index]) {
                max1_index = i;
            }
        }

        if (max1_index == 0) {
            max2_index = 1;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (i != max1_index && numbers[i] > numbers[max2_index]) {
                max2_index = i;
            }
        }

//        for (int first = 0; first < n; ++first) {
//            for (int second = first + 1; second < n; ++second) {
//                max_product = Math.max(max_product, numbers[first] * numbers[second]);
//            }
//        }

        return numbers[max1_index] * numbers[max2_index];
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
