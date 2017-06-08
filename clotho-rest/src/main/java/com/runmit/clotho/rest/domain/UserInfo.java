package com.runmit.clotho.rest.domain;

import lombok.Data;

/**
 * 用户实体对象
 */
@Data
public class UserInfo {
    private Integer userid;
    private String account;
    private String name;
    private String nickname;
    private String gender;
    private String mobile;
    private String mail;
    private String occupation;
    private String address;
    private Integer viplevel;
    private String location;
    private String headposter;
    private String birthDay;
}
