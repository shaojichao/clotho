package com.runmit.uc.rest.domain;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by gg on 2014/8/26.
 */
@Data
@ApiModel(value = "接口公共返回值", description = "调用接口的返回状态,特殊状态请参考各接口中的说明,公共状态一共三个:0-成功,400-请求格式错误,500-服务器内部错误", discriminator = "")
public class CommonResp {
    @ApiModelProperty(value = "状态返回参数", notes = "调用接口的返回状态,特殊状态请参考各接口中的说明,公共状态一共三个:0-成功,400-请求格式错误,500-服务器内部错误", required = true, dataType = "String")
    private String rtn;
}
