package com.runmit.uc.rest.domain;

import com.runmit.uc.core.domain.UserInfo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author TianLiang
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@ApiModel(value = "用户信息", description = "", discriminator = "")
public class UserInfoEntity {
    public UserInfoEntity(UserInfo ui){
        this.setUserid(String.valueOf(ui.getId()));
        this.setAccount(ui.getUserid());
        this.setNickname(ui.getNickname());
        this.setName(ui.getName());
        this.setAge(ui.getAge());
        this.setGender(ui.getGender());
        this.setMobile(ui.getMobile());
        this.setMail(ui.getMail());
        this.setOccupation(ui.getOccupation());
        this.setAddress(ui.getAddress());
        this.setViplevel(ui.getViplevel());
        this.setLocation(ui.getLocation());
        this.setAvatar(ui.getHeadposter());
    }
    @ApiModelProperty(value = "用户id", notes = "", required = true, dataType = "String")
    private String userid;
    @ApiModelProperty(value = "用户账号", notes = "", required = true, dataType = "String")
    private String account;
    @ApiModelProperty(value = "用户姓名", notes = "", required = true, dataType = "String")
    private String name;
    @ApiModelProperty(value = "用户昵称", notes = "", required = true, dataType = "String")
    private String nickname;
    @ApiModelProperty(value = "用户年龄", notes = "", required = true, dataType = "String")
    private String age;
    @ApiModelProperty(value = "用户性别", notes = "", required = true, dataType = "String")
    private String gender;
    @ApiModelProperty(value = "手机号", notes = "", required = true, dataType = "String")
    private String mobile;
    @ApiModelProperty(value = "邮箱", notes = "", required = true, dataType = "String")
    private String mail;
    @ApiModelProperty(value = "职业", notes = "", required = true, dataType = "String")
    private String occupation;
    @ApiModelProperty(value = "地址", notes = "", required = true, dataType = "String")
    private String address;
    @ApiModelProperty(value = "用户等级", notes = "", required = true, dataType = "String")
    private String viplevel;
    @ApiModelProperty(value = "区域码", notes = "", required = true, dataType = "String")
    private String location;
    @ApiModelProperty(value = "头像", notes = "", required = true, dataType = "String")
    private String avatar;
}
