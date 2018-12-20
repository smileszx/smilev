package com.victor.su.dao;

public class DbContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDbType() {
        return contextHolder.get();
    }
}
