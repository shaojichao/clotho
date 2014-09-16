package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class Upgrade implements Serializable {

    private Integer id;
    private String version;
    private String memo;
    private String pkgurl;
    private Integer status;//当前版本状态:1-有效,2-逻辑删除
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;

}