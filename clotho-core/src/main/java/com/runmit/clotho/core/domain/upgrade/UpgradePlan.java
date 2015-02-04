package com.runmit.clotho.core.domain.upgrade;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Scott.Xie
 */
@Data
public class UpgradePlan implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7811102660151073545L;
	private String versions;
	private Integer id;
    private String originid;
    private String upgradeid;
    private Integer clientid;
    private Integer showtype;
    private Integer upgradetype;
    private String createby;
    private String updateby;
    private Date createtime;
    private Date updatetime;
}