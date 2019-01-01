package com.victor.su.routeplan;

public enum RouteType {

    WALK(1, new WalkRoute());

    private int type;

    private RoutePlan routePlan;

    RouteType (int type, RoutePlan routePlan) {
        this.type = type;
        this.routePlan = routePlan;
    }

    public RoutePlan getRoutePlan (int type) {

        for (RouteType routeType : RouteType.values()) {
            if (routeType.type == type) {
                return routeType.routePlan;
            }
        }
        return null;
    }
}
