package com.runmit.clotho.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gg on 2014/8/26.
 */
@Data
@ApiModel(value = "接口公共返回值", description = "调用接口的返回状态,特殊状态请参考各接口中的说明,公共状态:0-成功", discriminator = "")
public class CommonResp {
    public String getRtn() {
		return rtn;
	}

	public void setRtn(String rtn) {
		this.rtn = rtn;
	}

	@ApiModelProperty(value = "状态返回参数", notes = "调用接口的返回状态,特殊状态请参考各接口中的说明,公共状态:0-成功", required = true, dataType = "String")
    private String rtn;
	
	private String rtmsg;

	public String getRtmsg() {
		return rtmsg;
	}

	public void setRtmsg(String rtmsg) {
		this.rtmsg = rtmsg;
	}
}
