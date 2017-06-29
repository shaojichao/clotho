package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 开屏广告对象
 *
 * @author sjc
 * @date 2017-06-01-15:27
 */
@Data
public class Advertisement implements Serializable {
    private Integer id;

    /**
     * 机型id
     */
    private String modeId;

    /**
     * 机型名称
     */
    private String model;

    /**
     * 广告图
     */
    private String adURL;

    /**
     * 链接地址
     */
    private String adLink;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 版本号
     */
    private String version;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改者
     */
    private String updateBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 上架时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date upShelvesTm;

    /**
     * 下架时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date downShelvesTm;

    /**
     * 图片分辨率
     */
    private String resolution;

    /**
     * 图片域地址
     */
    private String imgUploadUrl;
}
