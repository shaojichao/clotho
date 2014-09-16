package com.runmit.uc.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author TianLiang
 */
@Data
public class UserDeviceRela implements Serializable {

    private Integer id;
    private String devicesn;
    private String devicetype;
    private String token;

}