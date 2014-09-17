package com.runmit.clotho.rest.domain;

import com.runmit.uc.rest.domain.CommonResp;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gg on 2014/8/20.
 */
@Data
@ApiModel(value = "升级请求接口返回", description = "", discriminator = "")
public class UpgradeResp extends CommonResp{
    @ApiModelProperty(value = "最新版本描述", notes = "", required = true, dataType = "String")
    private String introduction;

    @ApiModelProperty(value = "最新版本号", notes = "", required = true, dataType = "String")
    private String new_version;

    @ApiModelProperty(value = "弹出类型:0-不弹出提示 1-弹出提示 ", notes = "", required = true, dataType = "String")
    private String show_type;

    @ApiModelProperty(value = "升级类型:1-可选升级 2-强制升级", notes = "", required = true, dataType = "String")
    private String upgrade_type;

    @ApiModelProperty(value = "客户端类型", notes = "", required = true, dataType = "String")
    private String upgrade_url;
}
