package com.runmit.uc.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author TianLiang
 */
@Data
public class UserDeviceRelaDel implements Serializable {

    private Integer delid;
    private Integer id;
    private String devicesn;
    private String status;

}