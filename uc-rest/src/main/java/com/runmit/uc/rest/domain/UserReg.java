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
@ApiModel(value = "用户注册信息Model", description = "接收用户注册时传递过来的信息", discriminator = "")
public class UserReg {
    @ApiModelProperty(value = "注册用户账号类型", notes = "1为邮箱2为手机", required = true, dataType = "String")
    @NotEmpty(message = RestConst.rtn_UserReg_useridtype_empty)
    private String useridtype;

    @ApiModelProperty(value = "用户账号", notes = "", required = true, dataType = "String")
    @NotEmpty(message = RestConst.rtn_UserReg_account_empty)
    private String account;

    @ApiModelProperty(value = "用户密码", notes = "加密过后的值,必须为32位", required = true, dataType = "String")
    @Size(min = 32, max = 32, message = RestConst.rtn_UserReg_password_size)
    private String password;

    @ApiModelProperty(value = "客户端类型", notes = "区分不同客户端,安卓或者ios等", required = true, dataType = "String")
    @NotEmpty(message = RestConst.rtn_UserReg_clientid_empty)
    private String clientid;

    @ApiModelProperty(value = "设备硬件UUID", notes = "用户注册时使用设备的标识", required = true, dataType = "String")
    @NotEmpty(message = RestConst.rtn_UserReg_devicehwid_empty)
    private String devicehwid;

    @ApiModelProperty(value = "区域", notes = "用户注册时的地区", required = true, dataType = "String")
    @NotEmpty(message = RestConst.rtn_UserReg_location_empty)
    private String location;

    @ApiModelProperty(value = "用户昵称", notes = "", required = false, dataType = "String")
    private String nickname;

    @ApiModelProperty(value = "校验码", notes = "手机注册时必录", required = false, dataType = "String")
    private String verifycode;
}
