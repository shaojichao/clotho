package com.runmit.clotho.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Created by XP on 2014/8/20.
 */
@Data
@ApiModel(value = "APP评论信息", description = "", discriminator = "")
public class Comment {
    private String hwid;
    @NotEmpty
    private String udid;
    @NotEmpty
    private String wifimac;
    private String wirelesssmac;
    private String wiremac;
    @Range(min=1,max=3)
    private Integer os;
    @NotEmpty
    private String osver;
    @Range(min=1,max=3)
    private Integer device;
    @NotEmpty
    private String area;
    @NotEmpty
    private String language;
    @NotEmpty
    private String imei;
    private String idfv;
    @NotEmpty
    private String appkey;
    @NotEmpty
    private String appver;
    private String uid;
    @NotEmpty
    private String devicebrand;
    @NotEmpty
    private String devicedevice;
    @NotEmpty
    private String devicemodel;
    @NotEmpty
    private String devicehardware;
    @NotEmpty
    private String deviceid;
    @NotEmpty
    private String deviceserial;
    private String ro;
    @NotEmpty
    private String channel;
    @Range
    private Long dts;
    @NotEmpty
    private String contact;
    @NotEmpty
    private String content;
}
