package com.runmit.clotho.rest.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户验证 DTO
 */
@Data
public class UserCheckedDTO implements Serializable{
    private int rtn = 0;
    private String errMsg = "OK";
    private String token;
    private UserInfo userInfo;
    private int expireTime;
}
