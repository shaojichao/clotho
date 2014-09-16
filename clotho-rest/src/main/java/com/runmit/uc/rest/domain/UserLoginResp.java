package com.runmit.uc.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gg on 2014/8/21.
 */
@Data
@ApiModel(value = "用户登陆返回值", description = "", discriminator = "")
public class UserLoginResp  extends CommonResp{
    @ApiModelProperty(value = "登陆token", notes = "", required = true, dataType = "String")
    private String token;
    @ApiModelProperty(value = "用户信息", notes = "", required = true, dataType = "String")
    private UserInfoEntity userInfo;
}
