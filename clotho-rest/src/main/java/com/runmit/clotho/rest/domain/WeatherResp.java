package com.runmit.clotho.rest.domain;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hongbin.cao
 *
 * @date 2015年6月15日
 */
@Data
public class WeatherResp implements Serializable{

    private String city;

    private String pinyin;

    private String citycode;

    private String date;

    private String time;

    private String weatherCode;

    private String weather;

    private String temp;

    private String lTemp;

    private String hTemp;

    private String WDCode;

    private String WD;

    private String WSCode;

    private String WS;

    private String sunrise;

    private String sunset;

}
