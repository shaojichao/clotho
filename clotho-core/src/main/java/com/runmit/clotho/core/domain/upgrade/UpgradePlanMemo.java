package com.runmit.clotho.core.domain.upgrade;

import lombok.Data;

import com.runmit.clotho.core.domain.BaseDomain;

/**
 * @author zhipeng.tian
 * 
 * @date 2015年2月4日
 */
@Data
public class UpgradePlanMemo extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2965465910366459102L;
	private String memo;
	private int planid;
	private String language;
}
