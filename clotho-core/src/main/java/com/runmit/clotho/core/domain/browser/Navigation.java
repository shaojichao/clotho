package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 导航栏实体对象
 * @author lgz
 * @version 1.0
 * @date 2017-06-01
 */
@Data
public class Navigation implements Serializable{
    private Integer id;
    //导航栏名称
    private String name;
    //类型：0 url 1 app
    private int type;
    //小程序
    private String appId;
    //URL
    private String appUrl;
    //名称或小程序URL
    private String appIdOrUrl;
    //导航栏图标
    private String icon;
    //导航栏所处位置
    private int position;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 图片域地址
     */
    private String imgUploadUrl;
}
