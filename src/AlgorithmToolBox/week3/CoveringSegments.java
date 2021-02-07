package AlgorithmToolBox.week3;

import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        List<Integer> points = new ArrayList<>();
        while (segments.length > 0) {
            int r = minR(segments);
            points.add(r);
            segments = removeSegments(r, segments);
        }
        return convertToArray(points);
    }

    private static int[] convertToArray(List<Integer> points) {
        int[] array = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            array[i] = points.get(i);
        }
        return array;
    }

    private static Segment[] removeSegments(int r, Segment[] segments) {
        List<Segment> segmentList = new ArrayList<>();
        for (Segment segment : segments) {
            if (segment.start <= r && r <= segment.end) {
                continue; //move along, nothing to see here
            }
            segmentList.add(segment);
        }
        Segment[] segArray = new Segment[segmentList.size()];
        for (int i = 0; i < segmentList.size(); i++) {
            segArray[i] = segmentList.get(i);
        }
        return segArray;
    }

    private static int minR(Segment[] segments) {
        int minR = segments[0].end;
        for (int i = 1; i < segments.length; i++) {
            if (segments[i].end < minR) {
                minR = segments[i].end;
            }
        }
        return minR;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
