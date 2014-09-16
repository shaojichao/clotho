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
@ApiModel(value = "手机校验", description = "", discriminator = "")
public class VerifyMobile {
    @NotEmpty(message = RestConst.rtn_VerifyMobile_phonenumber_empty)
    @ApiModelProperty(value = "手机号码", notes = "", required = true, dataType = "String")
    private String phonenumber ;
//    @ApiModelProperty(value = "终端类型", notes = "", required = true, dataType = "String")
//    private String clientid;
}
