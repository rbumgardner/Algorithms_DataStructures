package AlgorithmToolBox.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int numRefills = 0;
        int currentPosition = 0;
        int n = stops.length;
        List<Integer> updatedStops = convertToList(stops, dist);
        while (currentPosition <= n) {
            int lastPosition = currentPosition;
            while (currentPosition <= n && updatedStops.get(currentPosition + 1) - updatedStops.get(lastPosition) <= tank) {
                currentPosition++;
            }
            if (currentPosition == lastPosition) {
                return  -1;
            }
            if (currentPosition <= n) {
                numRefills++;
            }
        }
        return numRefills;
    }

    private static List<Integer> convertToList(int[] stops,int dist) {
        List<Integer> list = new ArrayList<>();
        list.add(0); //add the start position
        for (int stop : stops) {
            list.add(Integer.valueOf(stop));
        }
        list.add(Integer.valueOf(dist)); //add the last position
        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
