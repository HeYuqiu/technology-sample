package com.hyq.algorithm.homework_trains;

import org.junit.Before;
import org.junit.Test;

import static com.hyq.algorithm.homework_trains.GraphMap.UNLIMITED_DISTANCE;
import static com.hyq.algorithm.homework_trains.GraphMap.UNLIMITED_STOPS;
import static org.junit.Assert.assertEquals;


public class GraphTest {
    private GraphMap graphMap;

    @Before
    public void before() {
        int[][] map = {
                //        A  B  C  D  E
                /** A **/{0, 5, 0, 5, 7},
                /** B **/{0, 0, 4, 0, 0},
                /** C **/{0, 0, 0, 8, 2},
                /** D **/{0, 0, 8, 0, 6},
                /** E **/{0, 3, 0, 0, 0}
        };
        graphMap = new GraphMap(map);
    }


    // 1.The distance of the route A-B-C.
    @Test
    public void test1() {
        assertEquals("9", graphMap.calcRouteDistance("ABC"));
    }

    // 2.The distance of the route A-D.
    @Test
    public void test2() {
        assertEquals("5", graphMap.calcRouteDistance("AD"));
    }

    // 3.The distance of the route A-D-C.
    @Test
    public void test3() {
        assertEquals("13", graphMap.calcRouteDistance("ADC"));
    }

    // 4.The distance of the route A-E-B-C-D.
    @Test
    public void test4() {
        assertEquals("22", graphMap.calcRouteDistance("AEBCD"));
    }

    // 5.The distance of the route A-E-D.
    @Test
    public void test5() {
        assertEquals("NO SUCH ROUTE", graphMap.calcRouteDistance("AED"));
    }

    // 6.The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
    @Test
    public void test6() {
        int limitStop = 3;
        Trip trip = graphMap.search('C', 'C', limitStop, UNLIMITED_DISTANCE);
        assertEquals(2, trip.getNumOfAllTheRoutes());
    }

    // 7.The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
    @Test
    public void test7() {
        int limitStop = 4;
        Trip trip = graphMap.search('A', 'C', limitStop, UNLIMITED_DISTANCE);
        assertEquals(3, trip.getNumOfRoutesWithExactlyStops(4));
    }

    // 8.The length of the shortest route (in terms of distance to travel) from A to C.
    @Test
    public void test8() {
        Trip trip = graphMap.search('A', 'C', UNLIMITED_STOPS, UNLIMITED_DISTANCE);
        assertEquals(9, trip.getShortestRouteDistance());
    }

    // 9.The length of the shortest route (in terms of distance to travel) from B to B.
    @Test
    public void test9() {
        Trip trip = graphMap.search('B', 'B', UNLIMITED_STOPS, UNLIMITED_DISTANCE);
        assertEquals(9, trip.getShortestRouteDistance());
    }

    // 10.The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
    @Test
    public void test10() {
        Trip trip = graphMap.search('C', 'C', UNLIMITED_STOPS, 30);
        assertEquals(7, trip.getNumOfAllTheRoutes());
    }
}