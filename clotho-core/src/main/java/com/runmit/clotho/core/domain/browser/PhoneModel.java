package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机型分辨率对象
 * @author sjc
 * @date 2017-05-31-15:27
 */
@Data
public class PhoneModel implements Serializable{

    private Integer id;
    /**
     *机型名称
     */
    private String model;
    /**
     * 分辨率宽度
     */
    private Integer width;
    /**
     * 分辨率高度
     */
    private Integer height;
    /**
     * 分辨率
     */
    private String resolution;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    //创建者
    private String createby;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;
    //修改者
    private String updateby;
}
