package com.runmit.clotho.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by XP on 2014/8/20.
 */
@Data
@ApiModel(value = "升级请求", description = "", discriminator = "")
public class Upgrade {
//    @NotEmpty(message = RestConst.rtn_DelDevice_userid_empty)
    @ApiModelProperty(value = "当前版本", notes = "", required = true, dataType = "String")
    private String version;

//    @NotEmpty(message = RestConst.rtn_DelDevice_devicehwid_empty)
    @ApiModelProperty(value = "客户端类型", notes = "", required = true, dataType = "String")
    private String clientid;
}
