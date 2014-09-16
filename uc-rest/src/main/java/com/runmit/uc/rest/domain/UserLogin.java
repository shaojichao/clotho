package com.runmit.uc.rest.domain;

import com.runmit.uc.rest.common.RestConst;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by gg on 2014/8/20.
 */
@Data
@ApiModel(value = "用户登陆", description = "", discriminator = "")
public class UserLogin {
    @NotEmpty(message = RestConst.rtn_UserLogin_account_empty)
    @ApiModelProperty(value = "用户账号", notes = "", required = true, dataType = "String")
    private String account;
    @Size(min = 32, max = 32, message = RestConst.rtn_UserLogin_password_size)
    @ApiModelProperty(value = "用户密码", notes = "", required = true, dataType = "String")
    private String password;
    @NotEmpty(message = RestConst.rtn_UserLogin_devicehwid_empty)
    @ApiModelProperty(value = "设备硬件ID", notes = "", required = true, dataType = "String")
    private String devicehwid;
    @ApiModelProperty(value = "手机校验码", notes = "", required = true, dataType = "String")
    private String verifycode;
    @NotEmpty(message = RestConst.rtn_UserLogin_clientid_empty)
    @ApiModelProperty(value = "终端类型", notes = "", required = true, dataType = "String")
    private String clientid;
}
