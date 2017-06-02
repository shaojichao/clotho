package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.runmit.clotho.core.domain.BaseDomain;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 搜索引擎实体对象
 * @author lgz
 * @version 1.0
 * @date 2017-06-01
 */
@Data
public class SearchEngine implements Serializable{
    private Integer id;
    //引擎名称
    private String name;
    //引擎URL
    private String engineURL;
    //渠道号
    private String channelNO;
    //引擎图标
    private String icon;
    //默认状态:1 默认搜索 0非默认搜索
    private int status;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    //默认状态名
    private String statusName;
}
