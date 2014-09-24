package com.runmit.clotho.rest.domain;


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

    public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getNew_version() {
		return new_version;
	}

	public void setNew_version(String new_version) {
		this.new_version = new_version;
	}

	public String getShow_type() {
		return show_type;
	}

	public void setShow_type(String show_type) {
		this.show_type = show_type;
	}

	public String getUpgrade_type() {
		return upgrade_type;
	}

	public void setUpgrade_type(String upgrade_type) {
		this.upgrade_type = upgrade_type;
	}

	public String getUpgrade_url() {
		return upgrade_url;
	}

	public void setUpgrade_url(String upgrade_url) {
		this.upgrade_url = upgrade_url;
	}

	@ApiModelProperty(value = "弹出类型:0-不弹出提示 1-弹出提示 ", notes = "", required = true, dataType = "String")
    private String show_type;

    @ApiModelProperty(value = "升级类型:1-可选升级 2-强制升级", notes = "", required = true, dataType = "String")
    private String upgrade_type;

    @ApiModelProperty(value = "客户端类型", notes = "", required = true, dataType = "String")
    private String upgrade_url;
}
