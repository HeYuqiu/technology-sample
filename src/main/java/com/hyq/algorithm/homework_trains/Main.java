package com.hyq.algorithm.homework_trains;

import java.util.Scanner;

import static com.hyq.algorithm.homework_trains.GraphMap.UNLIMITED_DISTANCE;
import static com.hyq.algorithm.homework_trains.GraphMap.UNLIMITED_STOPS;

public class Main {
    public static void main(String[] args) {
        System.out.println("Type it in like this: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        String input = new Scanner(System.in).nextLine();
        int[][] map = inputTransform(input);
        GraphMap graphMap = new GraphMap(map);

        System.out.println("Output #1: " + graphMap.calcRouteDistance("ABC"));
        System.out.println("Output #2: " + graphMap.calcRouteDistance("AD"));
        System.out.println("Output #3: " + graphMap.calcRouteDistance("ADC"));
        System.out.println("Output #4: " + graphMap.calcRouteDistance("AEBCD"));
        System.out.println("Output #5: " + graphMap.calcRouteDistance("AED"));
        System.out.println("Output #6: " + graphMap.search('C', 'C', 3, UNLIMITED_DISTANCE).getNumOfAllTheRoutes());
        System.out.println("Output #7: " + graphMap.search('A', 'C', 4, UNLIMITED_DISTANCE).getNumOfRoutesWithExactlyStops(4));
        System.out.println("Output #8: " + graphMap.search('A', 'C', UNLIMITED_STOPS, UNLIMITED_DISTANCE).getShortestRouteDistance());
        System.out.println("Output #9: " + graphMap.search('B', 'B', UNLIMITED_STOPS, UNLIMITED_DISTANCE).getShortestRouteDistance());
        System.out.println("Output #10: " + graphMap.search('C', 'C', UNLIMITED_STOPS, 30).getNumOfAllTheRoutes());
    }

    /**
     * 数据输入
     *
     * @param input
     * @return
     */
    private static int[][] inputTransform(String input) {
        int[][] result = new int[26][26];
        String[] arr = input.split(",");
        for (String s : arr) {
            s = s.trim();
            if (s.length() != 3)
                throw new IllegalArgumentException("Wrong input context!");
            if (!s.substring(2).matches("-?\\d+"))
                throw new IllegalArgumentException("Wrong distance!");
            char[] c = s.toCharArray();
            if (c[0] < 'A' || c[0] > 'Z' || c[1] < 'A' || c[1] > 'Z')
                throw new IllegalArgumentException("Input out of range!");
            result[c[0] - 'A'][c[1] - 'A'] = Integer.parseInt(String.valueOf(c[2]));
        }

        return result;
    }
}
