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
@ApiModel(value = "用户鉴权", description = "", discriminator = "")
public class UserCheck {
    @NotEmpty(message = RestConst.rtn_UserCheck_userid_empty)
    @ApiModelProperty(value = "用户id", notes = "", required = true, dataType = "String")
    private String userid;
    @NotEmpty(message = RestConst.rtn_UserCheck_devicehwid_empty)
    @ApiModelProperty(value = "设备硬件ID", notes = "", required = true, dataType = "String")
    private String devicehwid;
    @NotEmpty(message = RestConst.rtn_UserCheck_token_empty)
    @ApiModelProperty(value = "验证token", notes = "", required = true, dataType = "String")
    private String token;
}
