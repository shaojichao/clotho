package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.runmit.clotho.core.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * 用户搜索引擎关系对象
 * @author lgz
 * @version 1.0
 * @date 2017-06-01
 */
@Data
public class UserEngine extends BaseDomain{
    private Integer id;
    //用户ID
    private Integer userId;
    //引擎ID
    private Integer engineId;

    private Integer createby;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    private Integer updateby;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;

}
