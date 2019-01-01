package com.victor.su.routeplan;

public class WalkRoute implements RoutePlan {


    @Override
    public RouteResult requestRoutePlan(int type, String start, String dest) {
        System.out.println("================步行路線推薦===============");

        System.out.println("您可以從 " + start + "沿著直線走到 " + dest + "預計耗時1天。");

        return new RouteResult(200,"您可以從 " + start + "沿著直線走到 " + dest + "預計耗時1天。", "success" );
    }
}
