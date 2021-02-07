package AlgorithmToolBox.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        List<Point> allPoints = new ArrayList<>();
        for (int i = 0; i < starts.length; i++) {
            Point startPoint = new Point(starts[i], 'l', i);
            allPoints.add(startPoint);
            Point endPoint = new Point(ends[i], 'r', i);
            allPoints.add(endPoint);
        }
        for (int i = 0; i < points.length; i++) {
            Point point = new Point(points[i], 'p', i);
            allPoints.add(point);
        }
        Collections.sort(allPoints);
        int count = 0, i = 0;
        for (Point allPoint : allPoints) {
            if (allPoint.type == 'l') {
                count++;
            }
            if (allPoint.type == 'p') {
                cnt[allPoint.getIdx()] = count;
            }
            if (allPoint.type == 'r') {
                count--;
            }
        }
        return cnt;
    }

    private static class Point implements Comparable<Point> {
        private int position;
        private char type;
        private int idx;

        public Point(int position, char type, int idx) {
            this.position = position;
            this.type = type;
            this.idx = idx;
        }

        public int getPosition() {
            return this.position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public char getType() {
            return this.type;
        }

        public void setType(char type) {
            this.type = type;
        }

        public int getIdx() {
            return this.idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        @Override
        public int compareTo(Point point) {
            int i = Integer.compare(this.position, point.position);
            if (i != 0) {
                return i;
            }
            i = Character.compare(this.type, point.type);
            if (i != 0) {
                return i;
            }
            return 0;
        }
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

