package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class DeviceInfo implements Serializable {

    private Integer deviceid;
    private String devicesn;
    private Integer devicetype;
    private String location;
    private String produceddate;
    private Integer userregcount;
    private Integer status;
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;

}