package com.runmit.clotho.management.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author TianLiang
 */
@Data
public class DeviceInfo implements Serializable {
    private String devicesn;
    private String devicetype;
    private String location;
    private String produceddate;
}