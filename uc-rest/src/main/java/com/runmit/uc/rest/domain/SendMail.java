package com.runmit.uc.rest.domain;

import com.runmit.uc.rest.common.RestConst;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by gg on 2014/8/20.
 */
@Data
@ApiModel(value = "发送邮件", description = "", discriminator = "")
public class SendMail {
    @NotEmpty(message = RestConst.rtn_SendMail_userid_empty)
    @ApiModelProperty(value = "用户id", notes = "", required = true, dataType = "String")
    private String userid;

    @NotEmpty(message = RestConst.rtn_SendMail_mailaddress_empty)
    @Email(message = RestConst.rtn_SendMail_mailaddress_invalid)
    @ApiModelProperty(value = "接收激活邮件的邮箱", notes = "", required = true, dataType = "String")
    private String mailaddress;
}
