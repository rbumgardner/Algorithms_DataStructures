package AlgorithmToolBox.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("x: " + x);
            sb.append(", ");
            sb.append("y: " + y);
            return sb.toString();
        }
    }


    static class XComparator implements Comparator<Point> {

        @Override
        public int compare(Point point1, Point point2) {
            return Long.compare(point1.x, point2.x);
        }
    }

    static class YComparator implements Comparator<Point> {

        @Override
        public int compare(Point point1, Point point2) {
            return Long.compare(point1.y, point2.y);
        }
    }

    static double minimalDistance(Point[] points, int leftIdx, int rightIdx) {
        double dist = Double.POSITIVE_INFINITY;
        if (rightIdx - leftIdx <= 3) {
            return calculateMinDistance(points, leftIdx, rightIdx);
        }
        int midIdx = (rightIdx + leftIdx)/2;
        double distL = minimalDistance(points, leftIdx, midIdx);
        double distR = minimalDistance(points, midIdx + 1, rightIdx);
        dist = Math.min(distL, distR);
        return Math.min(dist, getMinBoundary(points, leftIdx, midIdx, rightIdx, dist));
    }

    private static double getMinBoundary(Point[] points, int leftIdx, int midIdx, int rightIdx, double dist) {
        List<Point> boundaryPoints = new ArrayList<>();
        for (int i = leftIdx; i <= rightIdx; i++) {
            if (points[i].x >= points[midIdx].x - dist && points[i].x <= points[midIdx].x + dist) {
                boundaryPoints.add(points[i]);
            }
        }
        Collections.sort(boundaryPoints, new YComparator());
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < boundaryPoints.size(); i++) {
            for (int j = i + 1; j < boundaryPoints.size() && j - i < 7; j++) {
                double tempDist = Math.sqrt(Math.pow((boundaryPoints.get(j).x - boundaryPoints.get(i).x), 2) + Math.pow((boundaryPoints.get(j).y - boundaryPoints.get(i).y), 2));
                if (tempDist < minDist) {
                    minDist = tempDist;
                }
            }
        }
        return minDist;
    }

    private static double calculateMinDistance(Point[] points, int leftIdx, int rightIdx) {
        if (rightIdx - leftIdx + 1 == 2) {
            return Math.sqrt(Math.pow((points[rightIdx].x - points[leftIdx].x), 2) + Math.pow((points[rightIdx].y - points[leftIdx].y), 2));
        } else {
            double dist = Double.POSITIVE_INFINITY;
            for (int i = leftIdx; i <= rightIdx; i++) {
                for (int j = i + 1; j <= rightIdx ; j++) {
                    double tempDist = Math.sqrt(Math.pow((points[j].x - points[i].x), 2) + Math.pow((points[j].y - points[i].y), 2));
                    if (tempDist < dist) {
                        dist = tempDist;
                    }
                }
            }
            return dist;
        }
    }

    private static Point[] sortPoints(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i ++) {
            Point point = new Point(x[i], y[i]);
            points[i] = point;
        }
        Arrays.sort(points, new XComparator());
        return points;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
//        while (true) {
//            Random random = new Random();
//            int min = -5;
//            int max = 5;
//            int maxN = 6;
//            int n = random.nextInt(maxN) + 2;
//            int[] x = new int[n];
//            int[] y = new int[n];
//            for (int i = 0; i < n; i++) {
//                x[i] = random.nextInt(max - min) + min;
//                y[i] = random.nextInt(max - min) + min;
//            }
//            Point[] points = sortPoints(x, y);
//            double minDistBrute = minDistanceBrute(points, 0, x.length - 1);
//            double minDist = minimalDistance(points, 0, x.length - 1);
//            if (minDistBrute != minDist) {
//                System.out.println("minDistBrute: " + minDistBrute);
//                System.out.println("minDist: " + minDist);
//                System.out.println("n: " + n);
//                for (int i = 0; i < points.length; i++) {
//                    System.out.println(points[i].toString());
//                }
//                break;
//            }
//            System.out.println("Success!");
//        }
        System.out.println(minimalDistance(sortPoints(x, y), 0, x.length - 1));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    static double minDistanceBrute(Point[] points, int leftIdx, int rightIdx) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = leftIdx; i <= rightIdx; i++) {
            for (int j = i + 1; j <= rightIdx; j++) {
                double tempDist = Math.sqrt(Math.pow((points[j].x - points[i].x), 2) + Math.pow((points[j].y - points[i].y), 2));
                if (tempDist < minDist) {
                    minDist = tempDist;
                }
            }
        }
        return minDist;
    }
}
