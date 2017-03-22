package com.runmit.clotho.rest.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qian.liu on 2016/4/5.
 */
@Data
public class WeathersResp implements Serializable{

    private String city;//城市名称
    private String cityid;//城市id

    private TodayWeather today;

    private List<Forecase> forecast;//未来预测列表

    private List<History> history;//历史天气列表

}
