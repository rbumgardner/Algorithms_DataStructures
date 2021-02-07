package AlgorithmToolBox.week3;

import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        StringBuilder result = new StringBuilder();
        while (a.length > 0) {
            Integer max = Integer.valueOf(a[0]);
            for (int i = 1; i < a.length; i++) {
                Integer num = Integer.valueOf(a[i]);
                if (isGreaterOrEqual(num, max)) {
                    max = num;
                }
            }
            result.append(max);
            a = removeMax(max, a);
        }
        return result.toString();
    }

    private static boolean isGreaterOrEqual(Integer num, Integer max) {
        String a = num.toString() + max.toString();
        String b = max.toString() + num.toString();
         return Integer.compare(Integer.valueOf(a), Integer.valueOf(b)) >= 0;

    }

    private static String[] removeMax(Integer max, String[] a) {
        String[] strings = new String[a.length - 1];
        boolean continueSearch = true;
        if (a.length > 1) {
            for (int i = 0, j = 0; i < a.length; i++) {
                if (Integer.valueOf(a[i]).compareTo(max) == 0 && continueSearch) {
                    continueSearch = false;
                    continue;
                }
                strings[j++] = a[i];
            }
        }
        return strings;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

