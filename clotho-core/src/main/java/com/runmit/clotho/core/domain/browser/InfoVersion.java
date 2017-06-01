package com.runmit.clotho.core.domain.browser;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 版本信息实体对象
 * @author lgz
 * @version 1.0
 * @date 2017-06-01
 */
@Data
public class InfoVersion implements Serializable{
    private Integer id;
    //版本关联key
    private String versionKey;
    //版本号
    private String versionNo;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
