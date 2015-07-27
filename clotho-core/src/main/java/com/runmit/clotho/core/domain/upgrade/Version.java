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
	private long filesize;
    private String md5;//md5文件签名
    private Integer clientid;//客户端类型
    private Integer showtype;//是否弹出提示:1-不弹出提示,2-弹出提示
    private Integer upgradetype;//依赖升级类型:1-可选升级,2-强制升级
    private String createby;
    private Date createtime;
    private String updateby;
    private Date updatetime;
    /*OTA升级信息扩展属性*/
    private String brand;//考虑作为方案贴牌的可能
    private String model;//产品型号
    private String country;//销售地区
    private String hardwareVersion;//硬件版本号，以PCBA版本标识
    private String firmwareVersion;//固件版本号，以ROM版本标识
    private String latitude;//客户端指定校验码
    private int distributestatus;//分发状态：未分发:0 分发中:1 分发失败:2 分发成功:3

}