package com.runmit.clotho.rest.domain;

import lombok.Data;

/**
 * Created by qian.liu on 2016/4/5.
 */
@Data
public class History {
    private String date;
    private String week;
    private String aqi;
    private String fengxiang;
    private String fengli;
    private String hightemp;
    private String lowtemp;
    private String type;
}
