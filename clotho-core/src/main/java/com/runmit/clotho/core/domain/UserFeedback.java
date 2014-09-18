package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class UserFeedback implements Serializable {
    private Integer id;
    private String hwid;
    private String udid;
    private String wifimac;
    private String wirelesssmac;
    private String wiremac;
    private Integer os;
    private String osver;
    private Integer device;
    private String area;
    private String language;
    private String imei;
    private String idfv;
    private String appkey;
    private String appver;
    private String uid;
    private String devicebrand;
    private String devicedevice;
    private String devicemodel;
    private String devicehardware;
    private String deviceid;
    private String deviceserial;
    private String ro;
    private String channel;
    private Long dts;
    private String contact;
    private String content;
}