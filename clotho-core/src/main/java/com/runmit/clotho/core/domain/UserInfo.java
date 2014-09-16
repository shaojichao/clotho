package com.runmit.uc.core.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * @author TianLiang
 */
@Setter
@Getter
public class UserInfo implements Serializable {

    private Integer id;
    private String userid;
    private String useridtype;
    private String password;
    private String name;
    private String nickname;
    private String age;
    private String gender;
    private String mobile;
    private String mail;
    private String occupation;
    private String address;
    private String viplevel;
    private String location;
    private String headposter;
    private String validata;
    private String status;
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;
}