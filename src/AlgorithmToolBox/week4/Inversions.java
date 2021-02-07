package AlgorithmToolBox.week4;

import java.util.Arrays;
import java.util.Scanner;

public class Inversions {

    private static String mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int ave = (left + right) / 2;
            mergeSort(a, left, ave);
            mergeSort(a, ave + 1, right);
            merge(a, left, ave, right);
        }
        return Arrays.toString(a);
    }

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        for( int i = 0; i < a.length - 1; i++) {
            int n = i + 1;
            while (n < a.length) {
                if (a[i] > a[n]) {
                    numberOfInversions++;
                }
                n++;
            }
        }
        return numberOfInversions;
    }

    private static void merge(int[] a, int left, int ave, int right) {
        int n1 = ave - left + 1;
        int n2 = right - ave;
        int[] l = new int[n1 + 1];
        int[] r = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            l[i] = a[left + i];
        }
        for (int j = 0; j < n2; j++) {
            r[j] = a[ave + j + 1];
        }
        l[n1] = Integer.MAX_VALUE;
        r[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = left; k <= right; k++) {
            if (l[i] < r[j]) {
                a[k] = l[i];
                i++;
            } else {
                a[k] = r[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println("Inversions: " + getNumberOfInversions(a, b, 0, a.length - 1));
        System.out.println(" Sorted: " + mergeSort(a, 0, a.length - 1));
    }
}

