package com.runmit.uc.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gg on 2014/8/20.
 */
@Data
@ApiModel(value = "用户鉴权返回", description = "", discriminator = "")
public class UserCheckResp extends CommonResp{
    @ApiModelProperty(value = "用户信息", notes = "", required = true, dataType = "String")
    private UserInfoEntity userInfo;
}
