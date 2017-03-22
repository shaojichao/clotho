package com.runmit.clotho.rest.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by qian.liu on 2016/4/5.
 */
@Data
public class TodayWeather {
    private String date;//今天日期
    private String week;//今天星期
    private String curTemp;//当前温度
    private String aqi;//pm
    private String fengxiang;//风向
    private String fengli;//风力
    private String hightemp;//最高温度
    private String lowtemp;//最低温度
    private String type;//天气状态

    private List<MoreIndex> index;//指标列表


}
