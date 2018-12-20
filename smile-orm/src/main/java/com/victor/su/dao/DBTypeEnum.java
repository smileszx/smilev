package com.victor.su.dao;

public enum DBTypeEnum {
    dbone("dbone"), dbtwo("dbtwo");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
