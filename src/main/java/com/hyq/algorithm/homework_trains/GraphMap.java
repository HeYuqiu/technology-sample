package com.hyq.algorithm.homework_trains;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphMap {

    private static final char START_CHAR = 'A';                     // 起始字符
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";    // 没有此路径，减少硬编码
    private List<Trip.Route> routes;                                // 用于临时存储路线
    public static final int UNLIMITED_STOPS = 20;                   // 不限制步数，但是要防止无限循环
    public static final int UNLIMITED_DISTANCE = 0;                 // 不限制距离

    /**
     * 各点之间的距离：AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7 .
     */
    private int[][] map;

    public GraphMap(int[][] map) {
        this.map = map;
    }

    /**
     * 计算路线的距离，如果不存在则返回NO_SUCH_ROUTE
     *
     * @param routeStr 格式：ADCEF，不能有其他字符，并且不能出范围
     * @return
     */
    public String calcRouteDistance(String routeStr) {
        if (Objects.isNull(routeStr) || routeStr.length() <= 1) {
            throw new RuntimeException("The calculated route is not valid !");
        }
        char[] chars = routeStr.toCharArray();
        checkPosition(chars);
        int distance = 0;
        for (int i = 1; i < chars.length; i++) {
            int pDistance = getDistance(chars[i - 1], chars[i]);
            if (pDistance == 0) {
                return NO_SUCH_ROUTE;
            }
            distance += pDistance;
        }
        return distance + "";
    }

    /**
     * 获取给定两点间的旅行方案（包含所有路线）
     *
     * @param start       起始点
     * @param end         结束点
     * @param maxStops    限制最大步数
     * @param maxDistance 限制最大距离
     * @return
     */
    public Trip search(char start, char end, int maxStops, int maxDistance) {
        checkPosition(start, end);
        routes = new ArrayList<>();
        dfs(start - START_CHAR, end - START_CHAR, "" + start, 0, maxStops, maxDistance);
        Trip trip = new Trip(start, end, routes);
        return trip;
    }

    /**
     * 深度优先搜索遍历有向图的递归实现
     *
     * @param x           起始点
     * @param y           结束点
     * @param path        当前路径
     * @param distance    当前距离
     * @param maxStops    限制最大步数
     * @param maxDistance 限制最大距离
     * @return
     */
    private void dfs(int x, int y, String path, int distance, int maxStops, int maxDistance) {
        for (int i = 0; i < map[x].length; i++) {
            if (map[x][i] > 0
                    && (maxStops > 0 ? path.length() < maxStops + 1 : true)
                    && (maxDistance > 0 ? distance + map[x][i] < maxDistance : true)) {
                if (i == y) {
                    Trip.Route route = new Trip.Route(path + (char) (START_CHAR + i), distance + map[x][i]);
                    routes.add(route);
                }
                dfs(i, y, path + (char) (START_CHAR + i), distance + map[x][i], maxStops, maxDistance);
            }
        }
    }

    /**
     * 获取两点间的距离（map上的直接距离，为0表示不通）
     *
     * @param start
     * @param end
     * @return
     */
    private int getDistance(char start, char end) {
        checkPosition(start, end);
        return map[start - START_CHAR][end - START_CHAR];
    }

    /**
     * 检查站点是否合法
     *
     * @param positions 站点集合
     */
    private void checkPosition(char... positions) {
        for (char position : positions) {
            if (position < START_CHAR || position > START_CHAR + map.length) {
                throw new RuntimeException("There is no such position: " + position);
            }
        }
    }

}
