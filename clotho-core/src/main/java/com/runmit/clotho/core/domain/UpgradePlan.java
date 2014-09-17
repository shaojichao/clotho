package com.runmit.clotho.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author Scott.Xie
 */
@Data
public class UpgradePlan implements Serializable {
    private Integer id;
    private String originid;
    private String upgradeid;
    private Integer clientid;
    private String memo;
    private Integer showtype;
    private Integer upgradetype;
    private String createby;
    private String createtime;
    private String updateby;
    private String updatetime;
}