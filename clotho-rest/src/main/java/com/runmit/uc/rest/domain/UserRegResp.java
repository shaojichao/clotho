package com.runmit.uc.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gg on 2014/8/21.
 */
@Data
@ApiModel(value = "注册返回", description = "", discriminator = "")
public class UserRegResp  extends CommonResp{
    @ApiModelProperty(value = "注册用户的id号", notes = "", required = true, dataType = "String")
    private String userid;
}
