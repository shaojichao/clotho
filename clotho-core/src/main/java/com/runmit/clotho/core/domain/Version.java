package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class Version implements Serializable {

    private Integer id;
    private String version;
    private String serialno;
    private String memo;
    private String pkgurl;
    private Integer clientid;//客户端类型
    private Integer showtype;//是否弹出提示:1-不弹出提示,2-弹出提示
    private Integer upgradetype;//依赖升级类型:1-可选升级,2-强制升级
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;

}