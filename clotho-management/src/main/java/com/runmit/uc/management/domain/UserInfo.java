package com.runmit.uc.management.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author TianLiang
 */
@Data
public class UserInfo implements Serializable {

    private Integer id;
    private String userid;
    private String password;

}