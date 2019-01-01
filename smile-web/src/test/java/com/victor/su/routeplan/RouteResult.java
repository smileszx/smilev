package com.victor.su.routeplan;

public class RouteResult {
    /*
     * 规划结果返回码
     */
    private int code;

    /*
     * 路径描述
     */
    private Object data;

    /*
     * 提示信息
     */
    private String msg;

    public RouteResult(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


}
