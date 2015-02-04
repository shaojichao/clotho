package com.runmit.clotho.core.domain.upgrade;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Scott.Xie
 */
@Data
public class Version implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8311217979995977208L;
	private Integer id;
    private String version;
    private String serialno;
    private String clientname;
	private String pkgurl;
    private Integer clientid;//客户端类型
    private Integer showtype;//是否弹出提示:1-不弹出提示,2-弹出提示
    private Integer upgradetype;//依赖升级类型:1-可选升级,2-强制升级
    private String createby;
    private Date createtime;
    private String updateby;
    private Date updatetime;

}