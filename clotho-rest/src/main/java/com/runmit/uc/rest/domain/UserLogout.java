package com.runmit.uc.rest.domain;

import com.runmit.uc.rest.common.RestConst;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by gg on 2014/8/20.
 */
@Data
@ApiModel(value = "用户注销", description = "", discriminator = "")
public class UserLogout {
    @NotEmpty(message = RestConst.rtn_UserLogout_userid_empty)
    @ApiModelProperty(value = "用户id", notes = "", required = true, dataType = "String")
    private String userid;
    @NotEmpty(message = RestConst.rtn_UserLogout_devicehwid_empty)
    @ApiModelProperty(value = "设备硬件ID", notes = "", required = true, dataType = "String")
    private String devicehwid;
    @NotEmpty(message = RestConst.rtn_UserLogout_token_empty)
    @ApiModelProperty(value = "登陆获取的token", notes = "", required = true, dataType = "String")
    private String token;
}
