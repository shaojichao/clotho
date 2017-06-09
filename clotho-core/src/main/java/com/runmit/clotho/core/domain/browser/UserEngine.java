package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.runmit.clotho.core.domain.BaseDomain;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户搜索引擎关系对象
 * @author lgz
 * @version 1.0
 * @date 2017-06-01
 */
@Data
public class UserEngine implements Serializable{
    private Integer id;

    //用户ID
    private Integer userId;

    //用户账号
    private String account;

    //引擎ID
    private Integer engineId;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
