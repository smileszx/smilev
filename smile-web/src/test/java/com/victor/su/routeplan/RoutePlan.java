package com.victor.su.routeplan;

public interface RoutePlan {

    /**
     * 根據不同的交通方式計算路徑
     * @param type
     * @return
     */
    RouteResult requestRoutePlan(int type, String start, String dest);
}
