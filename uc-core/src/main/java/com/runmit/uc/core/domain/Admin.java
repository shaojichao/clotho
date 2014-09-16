package com.runmit.uc.core.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;

/**
 *
 * @author TianLiang
 */
@Data
@Getter
public class Admin implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String email;
    private String phone;
    private String qq;
    private String type;
    private String auth;
    private String status;
    private String loginIp;


}
