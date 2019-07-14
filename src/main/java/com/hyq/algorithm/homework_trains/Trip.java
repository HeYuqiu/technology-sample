package com.hyq.algorithm.homework_trains;

import java.util.List;

/**
 * 两点间旅行方案的所有情况，包含所有的路线、距离、最短路线
 * 并且可以筛选满足一定条件的方案
 */
public class Trip {
    private char start;             // 起点
    private char end;               // 终点
    private List<Route> routes;     // 所有路线方案

    public Trip(char start, char end, List<Route> routes) {
        this.start = start;
        this.end = end;
        this.routes = routes;
    }

    /**
     * 获得这个旅行方案的所有路线数量
     *
     * @return
     */
    public int getNumOfAllTheRoutes() {
        return routes.size();
    }

    /**
     * 获取指定步数的路线数量
     *
     * @param stops
     * @return
     */
    public int getNumOfRoutesWithExactlyStops(int stops) {
        long count = routes.stream().filter(route -> route.path.length() - 1 == stops).count();
        return (int) count;
    }

    /**
     * 获得最短路径的距离
     *
     * @return
     */
    public int getShortestRouteDistance() {
        return routes.stream().mapToInt(Route::getDistance).min().getAsInt();
    }

    public static class Route {
        private String path;        // 路径，比如：ACDEB，由此可以算出步数（经历了多少站）
        private int distance;       // 距离，各个站点之间的距离之和

        public Route(String path, int distance) {
            this.path = path;
            this.distance = distance;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

}
