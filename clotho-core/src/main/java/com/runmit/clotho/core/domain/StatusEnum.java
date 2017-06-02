package com.runmit.clotho.core.domain;

/**
 * 搜索状态枚举
 */
public enum StatusEnum{
    STATUS_N(0, "非默认搜索"),
    STATUS_Y(1, "默认搜索");

    private final int type;
    private final String val;

    private StatusEnum(int type, String val){
        this.type = type;
        this.val = val;
    }

    public int getType(){
        return this.type;
    }

    public String getVal(){
        return this.val;
    }



}
